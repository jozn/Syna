package io.socket;

import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.linphone.mediastream.Version;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

class IOConnection implements IOCallback {
    public static final String FRAME_DELIMITER = "\ufffd";
    public static final String SOCKET_IO_1 = "/socket.io/1/";
    private static final int STATE_CONNECTING = 2;
    private static final int STATE_HANDSHAKE = 1;
    private static final int STATE_INIT = 0;
    private static final int STATE_INTERRUPTED = 4;
    private static final int STATE_INVALID = 6;
    private static final int STATE_READY = 3;
    private static HashMap<String, List<IOConnection>> connections;
    static final Logger logger;
    private static SSLContext sslContext;
    HashMap<Integer, IOAcknowledge> acknowledge;
    private final Timer backgroundTimer;
    private long closingTimeout;
    private int connectTimeout;
    private SocketIO firstSocket;
    private Properties headers;
    private long heartbeatTimeout;
    private HearbeatTimeoutTask heartbeatTimeoutTask;
    private boolean keepAliveInQueue;
    private Exception lastException;
    private int nextId;
    private ConcurrentLinkedQueue<String> outputBuffer;
    private List<String> protocols;
    private ReconnectTask reconnectTask;
    private String sessionId;
    private HashMap<String, SocketIO> sockets;
    private int state;
    private IOTransport transport;
    private URL url;
    private String urlStr;

    /* renamed from: io.socket.IOConnection.1 */
    class C01291 implements IOAcknowledge {
        private final /* synthetic */ String val$endPoint;
        private final /* synthetic */ String val$id;

        C01291(String str, String str2) {
            this.val$endPoint = str;
            this.val$id = str2;
        }

        public void ack(Object... objArr) {
            JSONArray jSONArray = new JSONArray();
            int length = objArr.length;
            for (int i = IOConnection.STATE_INIT; i < length; i += IOConnection.STATE_HANDSHAKE) {
                Object obj = objArr[i];
                if (obj == null) {
                    try {
                        obj = JSONObject.f4126a;
                    } catch (Exception e) {
                        IOConnection.this.error(new SocketIOException("You can only put values in IOAcknowledge.ack() which can be handled by JSONArray.put()", e));
                    }
                }
                jSONArray.m5347a(obj);
            }
            IOConnection.this.sendPlain(new IOMessage(IOConnection.STATE_INVALID, this.val$endPoint, this.val$id + jSONArray.toString()).toString());
        }
    }

    private class ConnectThread extends Thread {
        public ConnectThread() {
            super("ConnectThread");
        }

        public void run() {
            if (IOConnection.this.getState() == 0) {
                IOConnection.this.handshake();
            }
            IOConnection.this.connectTransport();
        }
    }

    private class HearbeatTimeoutTask extends TimerTask {
        private HearbeatTimeoutTask() {
        }

        public void run() {
            IOConnection.this.error(new SocketIOException("Timeout Error. No heartbeat from server within life time of the socket. closing.", IOConnection.this.lastException));
        }
    }

    private class ReconnectTask extends TimerTask {
        private ReconnectTask() {
        }

        public void run() {
            IOConnection.this.connectTransport();
            if (!IOConnection.this.keepAliveInQueue) {
                IOConnection.this.sendPlain("2::");
                IOConnection.this.keepAliveInQueue = true;
            }
        }
    }

    static {
        logger = Logger.getLogger("io.socket");
        sslContext = null;
        connections = new HashMap();
    }

    private IOConnection(String str, SocketIO socketIO) {
        this.state = STATE_INIT;
        this.connectTimeout = 15000;
        this.outputBuffer = new ConcurrentLinkedQueue();
        this.sockets = new HashMap();
        this.firstSocket = null;
        this.backgroundTimer = new Timer("backgroundTimer");
        this.nextId = STATE_HANDSHAKE;
        this.acknowledge = new HashMap();
        this.reconnectTask = null;
        try {
            this.url = new URL(str);
            this.urlStr = str;
            this.firstSocket = socketIO;
            this.headers = socketIO.getHeaders();
            this.sockets.put(socketIO.getNamespace(), socketIO);
            new ConnectThread().start();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void cleanup() {
        setState(STATE_INVALID);
        if (this.transport != null) {
            this.transport.disconnect();
        }
        this.sockets.clear();
        synchronized (connections) {
            List list = (List) connections.get(this.urlStr);
            if (list == null || list.size() <= STATE_HANDSHAKE) {
                connections.remove(this.urlStr);
            } else {
                list.remove(this);
            }
        }
        logger.info("Cleanup");
        this.backgroundTimer.cancel();
    }

    private synchronized void connectTransport() {
        if (getState() != STATE_INVALID) {
            setState(STATE_CONNECTING);
            if (this.protocols.contains(WebsocketTransport.TRANSPORT_NAME)) {
                this.transport = WebsocketTransport.create(this.url, this);
            } else if (this.protocols.contains(XhrTransport.TRANSPORT_NAME)) {
                this.transport = XhrTransport.create(this.url, this);
            } else {
                error(new SocketIOException("Server supports no available transports. You should reconfigure the server to support a available transport"));
            }
            this.transport.connect();
        }
    }

    private void error(SocketIOException socketIOException) {
        for (SocketIO callback : this.sockets.values()) {
            callback.getCallback().onError(socketIOException);
        }
        cleanup();
    }

    private IOCallback findCallback(IOMessage iOMessage) throws SocketIOException {
        if ("".equals(iOMessage.getEndpoint())) {
            return this;
        }
        SocketIO socketIO = (SocketIO) this.sockets.get(iOMessage.getEndpoint());
        if (socketIO != null) {
            return socketIO.getCallback();
        }
        throw new SocketIOException("Cannot find socket for '" + iOMessage.getEndpoint() + "'");
    }

    public static SSLContext getSslContext() {
        return sslContext;
    }

    private synchronized int getState() {
        return this.state;
    }

    private void handshake() {
        try {
            setState(STATE_HANDSHAKE);
            URLConnection openConnection = new URL(new StringBuilder(String.valueOf(this.url.toString())).append(SOCKET_IO_1).toString()).openConnection();
            if (openConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) openConnection).setSSLSocketFactory(sslContext.getSocketFactory());
            }
            openConnection.setConnectTimeout(this.connectTimeout);
            openConnection.setReadTimeout(this.connectTimeout);
            for (Entry entry : this.headers.entrySet()) {
                openConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            String[] split = new Scanner(openConnection.getInputStream()).nextLine().split(":");
            this.sessionId = split[STATE_INIT];
            this.heartbeatTimeout = Long.parseLong(split[STATE_HANDSHAKE]) * 1000;
            this.closingTimeout = Long.parseLong(split[STATE_CONNECTING]) * 1000;
            this.protocols = Arrays.asList(split[STATE_READY].split(","));
        } catch (Exception e) {
            e.printStackTrace();
            error(new SocketIOException("Error while handshaking", e));
        }
    }

    private void invalidateTransport() {
        if (this.transport != null) {
            this.transport.invalidate();
        }
        this.transport = null;
    }

    public static IOConnection register(String str, SocketIO socketIO) {
        List linkedList;
        List<IOConnection> list = (List) connections.get(str);
        if (list == null) {
            linkedList = new LinkedList();
            connections.put(str, linkedList);
        } else {
            synchronized (list) {
                for (IOConnection iOConnection : list) {
                    if (iOConnection.register(socketIO)) {
                        return iOConnection;
                    }
                }
            }
        }
        IOConnection iOConnection2 = new IOConnection(str, socketIO);
        linkedList.add(iOConnection2);
        return iOConnection2;
    }

    private IOAcknowledge remoteAcknowledge(IOMessage iOMessage) {
        String id = iOMessage.getId();
        if (id.equals("")) {
            return null;
        }
        if (!id.endsWith("+")) {
            id = new StringBuilder(String.valueOf(id)).append("+").toString();
        }
        return new C01291(iOMessage.getEndpoint(), id);
    }

    private synchronized void resetTimeout() {
        if (this.heartbeatTimeoutTask != null) {
            this.heartbeatTimeoutTask.cancel();
        }
        if (getState() != STATE_INVALID) {
            this.heartbeatTimeoutTask = new HearbeatTimeoutTask();
            this.backgroundTimer.schedule(this.heartbeatTimeoutTask, this.closingTimeout + this.heartbeatTimeout);
        }
    }

    private synchronized void sendPlain(String str) {
        if (getState() == STATE_READY) {
            try {
                logger.info("> " + str);
                this.transport.send(str);
            } catch (Exception e) {
                logger.info("IOEx: saving");
                this.outputBuffer.add(str);
            }
        } else {
            this.outputBuffer.add(str);
        }
    }

    public static void setSslContext(SSLContext sSLContext) {
        sslContext = sSLContext;
    }

    private synchronized void setState(int i) {
        if (getState() != STATE_INVALID) {
            this.state = i;
        }
    }

    private void synthesizeAck(IOMessage iOMessage, IOAcknowledge iOAcknowledge) {
        if (iOAcknowledge != null) {
            int i = this.nextId;
            this.nextId = i + STATE_HANDSHAKE;
            this.acknowledge.put(Integer.valueOf(i), iOAcknowledge);
            iOMessage.setId(new StringBuilder(String.valueOf(i)).append("+").toString());
        }
    }

    public void emit(SocketIO socketIO, String str, IOAcknowledge iOAcknowledge, Object... objArr) {
        try {
            IOMessage iOMessage = new IOMessage(5, socketIO.getNamespace(), new JSONObject().m5359a("name", (Object) str).m5359a("args", new JSONArray(Arrays.asList(objArr))).toString());
            synthesizeAck(iOMessage, iOAcknowledge);
            sendPlain(iOMessage.toString());
        } catch (JSONException e) {
            error(new SocketIOException("Error while emitting an event. Make sure you only try to send arguments, which can be serialized into JSON."));
        }
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public IOTransport getTransport() {
        return this.transport;
    }

    public boolean isConnected() {
        return getState() == STATE_READY;
    }

    public void on(String str, IOAcknowledge iOAcknowledge, Object... objArr) {
        for (SocketIO callback : this.sockets.values()) {
            callback.getCallback().on(str, iOAcknowledge, objArr);
        }
    }

    public void onConnect() {
        SocketIO socketIO = (SocketIO) this.sockets.get("");
        if (socketIO != null) {
            socketIO.getCallback().onConnect();
        }
    }

    public void onDisconnect() {
        SocketIO socketIO = (SocketIO) this.sockets.get("");
        if (socketIO != null) {
            socketIO.getCallback().onDisconnect();
        }
    }

    public void onError(SocketIOException socketIOException) {
        for (SocketIO callback : this.sockets.values()) {
            callback.getCallback().onError(socketIOException);
        }
    }

    public void onMessage(String str, IOAcknowledge iOAcknowledge) {
        for (SocketIO callback : this.sockets.values()) {
            callback.getCallback().onMessage(str, iOAcknowledge);
        }
    }

    public void onMessage(JSONObject jSONObject, IOAcknowledge iOAcknowledge) {
        for (SocketIO callback : this.sockets.values()) {
            callback.getCallback().onMessage(jSONObject, iOAcknowledge);
        }
    }

    public synchronized void reconnect() {
        if (getState() != STATE_INVALID) {
            invalidateTransport();
            setState(STATE_INTERRUPTED);
            if (this.reconnectTask != null) {
                this.reconnectTask.cancel();
            }
            this.reconnectTask = new ReconnectTask();
            this.backgroundTimer.schedule(this.reconnectTask, 1000);
        }
    }

    public synchronized boolean register(SocketIO socketIO) {
        boolean z = true;
        synchronized (this) {
            String namespace = socketIO.getNamespace();
            if (this.sockets.containsKey(namespace)) {
                z = false;
            } else {
                this.sockets.put(namespace, socketIO);
                socketIO.setHeaders(this.headers);
                sendPlain(new IOMessage(STATE_HANDSHAKE, socketIO.getNamespace(), "").toString());
            }
        }
        return z;
    }

    public void send(SocketIO socketIO, IOAcknowledge iOAcknowledge, String str) {
        IOMessage iOMessage = new IOMessage(STATE_READY, socketIO.getNamespace(), str);
        synthesizeAck(iOMessage, iOAcknowledge);
        sendPlain(iOMessage.toString());
    }

    public void send(SocketIO socketIO, IOAcknowledge iOAcknowledge, JSONObject jSONObject) {
        IOMessage iOMessage = new IOMessage(STATE_INTERRUPTED, socketIO.getNamespace(), jSONObject.toString());
        synthesizeAck(iOMessage, iOAcknowledge);
        sendPlain(iOMessage.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void transportConnected() {
        /*
        r8 = this;
        r1 = 0;
        monitor-enter(r8);
        r0 = 3;
        r8.setState(r0);	 Catch:{ all -> 0x006a }
        r0 = r8.reconnectTask;	 Catch:{ all -> 0x006a }
        if (r0 == 0) goto L_0x0012;
    L_0x000a:
        r0 = r8.reconnectTask;	 Catch:{ all -> 0x006a }
        r0.cancel();	 Catch:{ all -> 0x006a }
        r0 = 0;
        r8.reconnectTask = r0;	 Catch:{ all -> 0x006a }
    L_0x0012:
        r8.resetTimeout();	 Catch:{ all -> 0x006a }
        r0 = r8.transport;	 Catch:{ all -> 0x006a }
        r0 = r0.canSendBulk();	 Catch:{ all -> 0x006a }
        if (r0 == 0) goto L_0x0070;
    L_0x001d:
        r2 = r8.outputBuffer;	 Catch:{ all -> 0x006a }
        r0 = new java.util.concurrent.ConcurrentLinkedQueue;	 Catch:{ all -> 0x006a }
        r0.<init>();	 Catch:{ all -> 0x006a }
        r8.outputBuffer = r0;	 Catch:{ all -> 0x006a }
        r0 = r2.size();	 Catch:{ IOException -> 0x0066 }
        r0 = new java.lang.String[r0];	 Catch:{ IOException -> 0x0066 }
        r0 = r2.toArray(r0);	 Catch:{ IOException -> 0x0066 }
        r0 = (java.lang.String[]) r0;	 Catch:{ IOException -> 0x0066 }
        r3 = logger;	 Catch:{ IOException -> 0x0066 }
        r4 = "Bulk start:";
        r3.info(r4);	 Catch:{ IOException -> 0x0066 }
        r3 = r0.length;	 Catch:{ IOException -> 0x0066 }
    L_0x003a:
        if (r1 < r3) goto L_0x004d;
    L_0x003c:
        r1 = logger;	 Catch:{ IOException -> 0x0066 }
        r3 = "Bulk end";
        r1.info(r3);	 Catch:{ IOException -> 0x0066 }
        r1 = r8.transport;	 Catch:{ IOException -> 0x0066 }
        r1.sendBulk(r0);	 Catch:{ IOException -> 0x0066 }
    L_0x0048:
        r0 = 0;
        r8.keepAliveInQueue = r0;	 Catch:{ all -> 0x006a }
        monitor-exit(r8);
        return;
    L_0x004d:
        r4 = r0[r1];	 Catch:{ IOException -> 0x0066 }
        r5 = logger;	 Catch:{ IOException -> 0x0066 }
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0066 }
        r7 = "> ";
        r6.<init>(r7);	 Catch:{ IOException -> 0x0066 }
        r4 = r6.append(r4);	 Catch:{ IOException -> 0x0066 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0066 }
        r5.info(r4);	 Catch:{ IOException -> 0x0066 }
        r1 = r1 + 1;
        goto L_0x003a;
    L_0x0066:
        r0 = move-exception;
        r8.outputBuffer = r2;	 Catch:{ all -> 0x006a }
        goto L_0x0048;
    L_0x006a:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x006d:
        r8.sendPlain(r0);	 Catch:{ all -> 0x006a }
    L_0x0070:
        r0 = r8.outputBuffer;	 Catch:{ all -> 0x006a }
        r0 = r0.poll();	 Catch:{ all -> 0x006a }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x006a }
        if (r0 != 0) goto L_0x006d;
    L_0x007a:
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.socket.IOConnection.transportConnected():void");
    }

    public void transportData(String str) {
        if (str.startsWith(FRAME_DELIMITER)) {
            Iterator listIterator = Arrays.asList(str.split(FRAME_DELIMITER)).listIterator(STATE_HANDSHAKE);
            while (listIterator.hasNext()) {
                String str2 = (String) listIterator.next();
                if (Integer.parseInt((String) listIterator.next()) != str2.length()) {
                    error(new SocketIOException("Garbage from server: " + str));
                    return;
                }
                transportMessage(str2);
            }
            return;
        }
        transportMessage(str);
    }

    public void transportDisconnected() {
        this.lastException = null;
        setState(STATE_INTERRUPTED);
        reconnect();
    }

    public void transportError(Exception exception) {
        this.lastException = exception;
        setState(STATE_INTERRUPTED);
        reconnect();
    }

    public void transportMessage(String str) {
        JSONObject jSONObject = null;
        int i = STATE_INIT;
        logger.info("< " + str);
        try {
            IOMessage iOMessage = new IOMessage(str);
            resetTimeout();
            switch (iOMessage.getType()) {
                case STATE_INIT /*0*/:
                    try {
                        findCallback(iOMessage).onDisconnect();
                    } catch (Exception e) {
                        error(new SocketIOException("Exception was thrown in onDisconnect()", e));
                    }
                case STATE_HANDSHAKE /*1*/:
                    try {
                        if (this.firstSocket == null || !"".equals(iOMessage.getEndpoint())) {
                            findCallback(iOMessage).onConnect();
                        } else if (this.firstSocket.getNamespace().equals("")) {
                            this.firstSocket.getCallback().onConnect();
                        } else {
                            sendPlain(new IOMessage(STATE_HANDSHAKE, this.firstSocket.getNamespace(), "").toString());
                        }
                        this.firstSocket = null;
                    } catch (Exception e2) {
                        error(new SocketIOException("Exception was thrown in onConnect()", e2));
                    }
                case STATE_CONNECTING /*2*/:
                    sendPlain("2::");
                case STATE_READY /*3*/:
                    try {
                        findCallback(iOMessage).onMessage(iOMessage.getData(), remoteAcknowledge(iOMessage));
                    } catch (Exception e22) {
                        error(new SocketIOException("Exception was thrown in onMessage(String).\nMessage was: " + iOMessage.toString(), e22));
                    }
                case STATE_INTERRUPTED /*4*/:
                    try {
                        String data = iOMessage.getData();
                        if (!data.trim().equals("null")) {
                            jSONObject = new JSONObject(data);
                        }
                        try {
                            findCallback(iOMessage).onMessage(jSONObject, remoteAcknowledge(iOMessage));
                        } catch (Exception e222) {
                            error(new SocketIOException("Exception was thrown in onMessage(JSONObject).\nMessage was: " + iOMessage.toString(), e222));
                        }
                    } catch (JSONException e3) {
                        logger.warning("Malformated JSON received");
                    }
                case Version.API05_ECLAIR_20 /*5*/:
                    try {
                        Object[] objArr;
                        JSONObject jSONObject2 = new JSONObject(iOMessage.getData());
                        if (jSONObject2.m5366h("args")) {
                            JSONArray d = jSONObject2.m5362d("args");
                            objArr = new Object[d.m5344a()];
                            while (i < d.m5344a()) {
                                if (!d.m5350d(i)) {
                                    objArr[i] = d.m5345a(i);
                                }
                                i += STATE_HANDSHAKE;
                            }
                        } else {
                            objArr = new Object[STATE_INIT];
                        }
                        try {
                            findCallback(iOMessage).on(jSONObject2.m5365g("name"), remoteAcknowledge(iOMessage), objArr);
                        } catch (Exception e2222) {
                            error(new SocketIOException("Exception was thrown in on(String, JSONObject[]).\nMessage was: " + iOMessage.toString(), e2222));
                        }
                    } catch (JSONException e4) {
                        logger.warning("Malformated JSON received");
                    }
                case STATE_INVALID /*6*/:
                    String[] split = iOMessage.getData().split("\\+", STATE_CONNECTING);
                    if (split.length == STATE_CONNECTING) {
                        try {
                            IOAcknowledge iOAcknowledge = (IOAcknowledge) this.acknowledge.get(Integer.valueOf(Integer.parseInt(split[STATE_INIT])));
                            if (iOAcknowledge == null) {
                                logger.warning("Received unknown ack packet");
                                return;
                            }
                            JSONArray jSONArray = new JSONArray(split[STATE_HANDSHAKE]);
                            Object[] objArr2 = new Object[jSONArray.m5344a()];
                            while (i < objArr2.length) {
                                objArr2[i] = jSONArray.m5345a(i);
                                i += STATE_HANDSHAKE;
                            }
                            iOAcknowledge.ack(objArr2);
                        } catch (NumberFormatException e5) {
                            logger.warning("Received malformated Acknowledge! This is potentially filling up the acknowledges!");
                        } catch (JSONException e6) {
                            logger.warning("Received malformated Acknowledge data!");
                        }
                    } else if (split.length == STATE_HANDSHAKE) {
                        sendPlain("6:::" + split[STATE_INIT]);
                    }
                case Version.API07_ECLAIR_21 /*7*/:
                    try {
                        findCallback(iOMessage).onError(new SocketIOException(iOMessage.getData()));
                    } catch (SocketIOException e7) {
                        error(e7);
                    }
                    if (iOMessage.getData().endsWith("+0")) {
                        cleanup();
                    }
                case Version.API08_FROYO_22 /*8*/:
                default:
                    logger.warning("Unkown type received" + iOMessage.getType());
            }
        } catch (Exception e22222) {
            error(new SocketIOException("Garbage from server: " + str, e22222));
        }
    }

    public synchronized void unregister(SocketIO socketIO) {
        sendPlain("0::" + socketIO.getNamespace());
        this.sockets.remove(socketIO.getNamespace());
        socketIO.getCallback().onDisconnect();
        if (this.sockets.size() == 0) {
            cleanup();
        }
    }
}
