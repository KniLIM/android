package com.knilim.knilim.server;

public interface MsgRcvListener {

    /**
     * 当socket server 监听到新消息的时候,应该回调该方法
     */
    void onMessageRcv();
}
