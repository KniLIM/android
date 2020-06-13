package com.knilim.knilim.data.model.message;

public class Message {
    private String id;
    private Integer messageType;
    private Integer contentType;
    private String sender;
    private String receiver;
    private Long createdAt;
    private String content;

    public Message(String id, Integer messageType, Integer contentType, String sender, String receiver, Long createdAt, String content) {
        this.id = id;
        this.messageType = messageType;
        this.contentType = contentType;
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = createdAt;
        this.content = content;
    }

    public Message() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}