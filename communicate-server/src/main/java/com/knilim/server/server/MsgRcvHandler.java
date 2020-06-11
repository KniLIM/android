package com.knilim.server.server;

public interface MsgRcvHandler {

    /**
     * 当socket server 监听到新消息的时候,应该回调该方法
     */
    void onMessageRcv();
}
