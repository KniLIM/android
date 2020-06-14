package com.knilim.knilim.data.model.message;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.knilim.knilim.data.main.FriendRepository;
import com.knilim.knilim.data.main.UserRepository;
import com.knilim.knilim.data.model.dialog.Dialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Message",
        foreignKeys = @ForeignKey(entity = Dialog.class, parentColumns = "id", childColumns = "dialogId", onDelete = CASCADE),
indices = @Index("dialogId"))
public class Message implements IMessage {
    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String id;
    @ColumnInfo(name = "messageType", typeAffinity = ColumnInfo.INTEGER)
    private Integer messageType;
    @ColumnInfo(name = "contentType", typeAffinity = ColumnInfo.INTEGER)
    private Integer contentType;
    @ColumnInfo(name = "sender", typeAffinity = ColumnInfo.TEXT)
    private String sender;
    @ColumnInfo(name = "receiver", typeAffinity = ColumnInfo.TEXT)
    private String receiver;
    @ColumnInfo(name = "createdTime", typeAffinity = ColumnInfo.INTEGER)
    private Long createdTime;
    @ColumnInfo(name = "content", typeAffinity = ColumnInfo.TEXT)
    private String content;
    @ColumnInfo(name = "dialogId", typeAffinity = ColumnInfo.TEXT)
    private String dialogId;

    @Ignore
    private IUser author;

    public Message(String id, Integer messageType, Integer contentType, String sender, String receiver, Long createdTime, String content, String dialogId) {
        this.id = id;
        this.messageType = messageType;
        this.contentType = contentType;
        this.sender = sender;
        this.receiver = receiver;
        this.createdTime = createdTime;
        this.content = content;
        this.dialogId = dialogId;
    }

    @Ignore
    public Message(String id, Integer messageType, Integer contentType, String sender, String receiver, Long createdAt, String content, IUser author) {
        this.id = id;
        this.messageType = messageType;
        this.contentType = contentType;
        this.sender = sender;
        this.receiver = receiver;
        this.createdTime = createdAt;
        this.content = content;

        this.author = author;
    }

    @Ignore
    public Message() {}

    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public IUser getUser() {
        if(author == null) {
            author = FriendRepository.INSTANCE.getFriendById(sender);
        }
        if(author == null) {
            author = UserRepository.INSTANCE.getUser();
        }
        return author;
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

    public Date getCreatedAt() {
        return new Date(createdTime);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }
}
