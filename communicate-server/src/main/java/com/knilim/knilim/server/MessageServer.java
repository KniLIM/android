package com.knilim.knilim.server;

public class MessageServer {
    private String host;
    private Integer port;
    private MsgRcvListener msgRcvListener;

    public MessageServer(String host, Integer port, MsgRcvListener msgRcvListener) {
        this.host = host;
        this.port = port;
        this.msgRcvListener = msgRcvListener;
    }
}
