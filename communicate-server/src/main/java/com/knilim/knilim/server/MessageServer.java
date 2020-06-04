package com.knilim.knilim.server;

import android.util.Log;

import java.util.Locale;

public class MessageServer {

    private static String TAG = "socket-io";

    /**
     * 单例实现
     */
    private static volatile MessageServer INSTANCE = null;
    public MessageServer getINSTANCE(String host, Integer port, MsgRcvHandler msgRcvHandler) {
        if(INSTANCE == null) {
            synchronized (MessageServer.class) {
                if(INSTANCE == null) {
                    INSTANCE = new MessageServer(host, port, msgRcvHandler);
                }
            }
        }
        return INSTANCE;
    }


    private String host;
    private Integer port;
    private MsgRcvHandler msgRcvHandler;

    private MessageServer(String host, Integer port, MsgRcvHandler msgRcvHandler) {
        this.host = host;
        this.port = port;
        this.msgRcvHandler = msgRcvHandler;
    }

    public boolean connect() {
        String url = String.format(Locale.US,"http://%s:%d/sockets", host, port);
        try {
           return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean sendMessage() {
        try {
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }
}
