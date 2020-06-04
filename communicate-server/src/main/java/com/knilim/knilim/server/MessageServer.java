package com.knilim.knilim.server;

import android.util.Log;

public class MessageServer {

    private static String TAG = "socket-io";

    /**
     * 单例实现
     */
    private static volatile MessageServer INSTANCE = null;
    public MessageServer getINSTANCE(String host, Integer port, MsgRcvListener msgRcvListener) {
        if(INSTANCE == null) {
            synchronized (MessageServer.class) {
                if(INSTANCE == null) {
                    INSTANCE = new MessageServer(host, port, msgRcvListener);
                }
            }
        }
        return INSTANCE;
    }


    private String host;
    private Integer port;
    private MsgRcvListener msgRcvListener;

    private MessageServer(String host, Integer port, MsgRcvListener msgRcvListener) {
        this.host = host;
        this.port = port;
        this.msgRcvListener = msgRcvListener;
    }

    public boolean connect() {
        try {
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }
}
