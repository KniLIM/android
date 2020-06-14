package com.knilim.knilim.data.model.dialog;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.knilim.knilim.data.main.MessageRepository;
import com.knilim.knilim.data.main.UserRepository;
import com.knilim.knilim.data.model.message.Message;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IUser;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Dialog",
        indices = @Index("id"))
public class Dialog implements IDialog<Message> {
    @PrimaryKey
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String id;
    @ColumnInfo(name = "dialogType", typeAffinity = ColumnInfo.INTEGER)
    private Integer dialogType;
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.TEXT)
    private String avatar;
    @ColumnInfo(name = "unread", typeAffinity = ColumnInfo.INTEGER)
    private Integer unread;
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private String name;
    @ColumnInfo(name = "createdTime", typeAffinity = ColumnInfo.INTEGER)
    private Long createdTime;

    @Ignore
    private ArrayList<IUser> users;

    @Ignore
    private Message lastMessage;

    public Dialog(String id, Integer dialogType, String avatar, Integer unread, String name, Long createdTime) {
        this.id = id;
        this.dialogType = dialogType;
        this.avatar = avatar;
        this.unread = unread;
        this.name = name;
        this.createdTime = createdTime;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return avatar;
    }

    @Override
    public String getDialogName() {
        return name;
    }

    @Override
    @Ignore
    public List<? extends IUser> getUsers() {
        if (users == null) {
            users = new ArrayList();
            users.add(UserRepository.INSTANCE.getUser());
        }
        return users;
    }

    @Override
    public Message getLastMessage() {
        setLastMessage(MessageRepository.INSTANCE.getLastMessage(id));
        return lastMessage;
    }

    @Override
    public void setLastMessage(Message message) {
        this.lastMessage = message;
    }

    @Override
    public int getUnreadCount() {
        return unread;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDialogType() {
        return dialogType;
    }

    public void setDialogType(Integer dialogType) {
        this.dialogType = dialogType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
