package io.socket;

public class SocketIOException extends Exception {
    private static final long serialVersionUID = 4965561569568761814L;

    public SocketIOException(String str) {
        super(str);
    }

    public SocketIOException(String str, Exception exception) {
        super(str, exception);
    }
}
