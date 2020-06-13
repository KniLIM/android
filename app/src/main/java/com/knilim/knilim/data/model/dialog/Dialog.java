package com.knilim.knilim.data.model.dialog;


import com.knilim.knilim.data.model.message.Message;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.List;

public class Dialog implements IDialog {
    private String id;
    private List<Message> messages;
    private Integer dialogType;
    private String avatar;
    private Integer unread;

    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return null;
    }

    @Override
    public String getDialogName() {
        return null;
    }

    @Override
    public List<? extends IUser> getUsers() {
        return null;
    }

    @Override
    public IMessage getLastMessage() {
        return null;
    }

    @Override
    public void setLastMessage(IMessage message) {

    }

    @Override
    public int getUnreadCount() {
        return 0;
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
}
