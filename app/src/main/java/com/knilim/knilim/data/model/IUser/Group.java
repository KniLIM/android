package com.knilim.knilim.data.model.IUser;

import androidx.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IUser;

public class Group implements IUser {
    private String id;
    private String owner;
    private String name;
    private String announcement;
    private String avatar;
    private String signature;

    public Group(String id, String owner, String name, String announcement, String avatar, String signature) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.announcement = announcement;
        this.avatar = avatar;
        this.signature = signature;
    }

    public Group() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", announcement='" + announcement + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
