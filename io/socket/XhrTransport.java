package io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

class XhrTransport implements IOTransport {
    public static final String TRANSPORT_NAME = "xhr-polling";
    private boolean blocked;
    private boolean connect;
    private IOConnection connection;
    PollThread pollThread;
    ConcurrentLinkedQueue<String> queue;
    private URL url;
    HttpURLConnection urlConnection;

    private class PollThread extends Thread {
        private static final String CHARSET = "UTF-8";

        public PollThread() {
            super(XhrTransport.TRANSPORT_NAME);
        }

        public void run() {
            XhrTransport.this.connection.transportConnected();
            while (XhrTransport.this.isConnect()) {
                try {
                    URL url = new URL(new StringBuilder(String.valueOf(XhrTransport.this.url.toString())).append("?t=").append(System.currentTimeMillis()).toString());
                    XhrTransport.this.urlConnection = (HttpURLConnection) url.openConnection();
                    SSLContext sslContext = IOConnection.getSslContext();
                    if ((XhrTransport.this.urlConnection instanceof HttpsURLConnection) && sslContext != null) {
                        ((HttpsURLConnection) XhrTransport.this.urlConnection).setSSLSocketFactory(sslContext.getSocketFactory());
                    }
                    String readLine;
                    if (XhrTransport.this.queue.isEmpty()) {
                        XhrTransport.this.setBlocked(true);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(XhrTransport.this.urlConnection.getInputStream(), CHARSET));
                        while (true) {
                            readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else if (XhrTransport.this.connection != null) {
                                XhrTransport.this.connection.transportData(readLine);
                            }
                        }
                        XhrTransport.this.setBlocked(false);
                        sleep(100);
                    } else {
                        XhrTransport.this.urlConnection.setDoOutput(true);
                        OutputStream outputStream = XhrTransport.this.urlConnection.getOutputStream();
                        if (XhrTransport.this.queue.size() == 1) {
                            outputStream.write(((String) XhrTransport.this.queue.poll()).getBytes(CHARSET));
                        } else {
                            Iterator it = XhrTransport.this.queue.iterator();
                            while (it.hasNext()) {
                                readLine = (String) it.next();
                                outputStream.write(new StringBuilder(IOConnection.FRAME_DELIMITER).append(readLine.length()).append(IOConnection.FRAME_DELIMITER).append(readLine).toString().getBytes(CHARSET));
                                it.remove();
                            }
                        }
                        outputStream.close();
                        InputStream inputStream = XhrTransport.this.urlConnection.getInputStream();
                        do {
                        } while (inputStream.read(new byte[1024]) > 0);
                        inputStream.close();
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (!(XhrTransport.this.connection == null || interrupted())) {
                        XhrTransport.this.connection.transportError(e2);
                        return;
                    }
                }
            }
            XhrTransport.this.connection.transportDisconnected();
        }
    }

    public XhrTransport(URL url, IOConnection iOConnection) {
        this.queue = new ConcurrentLinkedQueue();
        this.pollThread = null;
        this.connection = iOConnection;
        this.url = url;
    }

    public static IOTransport create(URL url, IOConnection iOConnection) {
        try {
            return new XhrTransport(new URL(url.toString() + IOConnection.SOCKET_IO_1 + TRANSPORT_NAME + "/" + iOConnection.getSessionId()), iOConnection);
        } catch (Throwable e) {
            throw new RuntimeException("Malformed Internal url. This should never happen. Please report a bug.", e);
        }
    }

    private synchronized boolean isBlocked() {
        return this.blocked;
    }

    private synchronized boolean isConnect() {
        return this.connect;
    }

    private synchronized void setBlocked(boolean z) {
        this.blocked = z;
    }

    private synchronized void setConnect(boolean z) {
        this.connect = z;
    }

    public boolean canSendBulk() {
        return true;
    }

    public void connect() {
        setConnect(true);
        this.pollThread = new PollThread();
        this.pollThread.start();
    }

    public void disconnect() {
        setConnect(false);
        this.pollThread.interrupt();
    }

    public String getName() {
        return TRANSPORT_NAME;
    }

    public void invalidate() {
        this.connection = null;
    }

    public void send(String str) throws IOException {
        sendBulk(new String[]{str});
    }

    public void sendBulk(String[] strArr) throws IOException {
        this.queue.addAll(Arrays.asList(strArr));
        if (isBlocked()) {
            this.pollThread.interrupt();
            this.urlConnection.disconnect();
        }
    }
}
