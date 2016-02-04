package io.socket;

class IOMessage {
    public static final int FIELD_DATA = 3;
    public static final int FIELD_ENDPOINT = 2;
    public static final int FIELD_ID = 1;
    public static final int FIELD_TYPE = 0;
    public static final int NUM_FIELDS = 4;
    public static final int TYPE_ACK = 6;
    public static final int TYPE_CONNECT = 1;
    public static final int TYPE_DISCONNECT = 0;
    public static final int TYPE_ERROR = 7;
    public static final int TYPE_EVENT = 5;
    public static final int TYPE_HEARTBEAT = 2;
    public static final int TYPE_JSON_MESSAGE = 4;
    public static final int TYPE_MESSAGE = 3;
    public static final int TYPE_NOOP = 8;
    private final String[] fields;
    private int type;

    public IOMessage(int i, String str, String str2) {
        this(i, null, str, str2);
    }

    public IOMessage(int i, String str, String str2, String str3) {
        this.fields = new String[TYPE_JSON_MESSAGE];
        this.type = i;
        this.fields[TYPE_CONNECT] = str;
        this.fields[TYPE_DISCONNECT] = i;
        this.fields[TYPE_HEARTBEAT] = str2;
        this.fields[TYPE_MESSAGE] = str3;
    }

    public IOMessage(String str) {
        this.fields = new String[TYPE_JSON_MESSAGE];
        String[] split = str.split(":", TYPE_JSON_MESSAGE);
        for (int i = TYPE_DISCONNECT; i < split.length; i += TYPE_CONNECT) {
            this.fields[i] = split[i];
            if (i == 0) {
                this.type = Integer.parseInt(split[i]);
            }
        }
    }

    public String getData() {
        return this.fields[TYPE_MESSAGE];
    }

    public String getEndpoint() {
        return this.fields[TYPE_HEARTBEAT];
    }

    public String getId() {
        return this.fields[TYPE_CONNECT];
    }

    public int getType() {
        return this.type;
    }

    public void setId(String str) {
        this.fields[TYPE_CONNECT] = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = TYPE_DISCONNECT; i < this.fields.length; i += TYPE_CONNECT) {
            stringBuilder.append(':');
            if (this.fields[i] != null) {
                stringBuilder.append(this.fields[i]);
            }
        }
        return stringBuilder.substring(TYPE_CONNECT);
    }
}
