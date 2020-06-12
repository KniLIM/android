package com.knilim.knilim.data.model.user;

import androidx.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IUser;

public class Friend implements IUser {
    private String uid;
    private String friend;
    private String avatar;
    private Boolean isBlack;
    private Boolean isTop;
    private String nickname;

    public Friend() {}

    public Friend(String uid, String friend, String avatar, Boolean isBlack, Boolean isTop, String nickname) {
        this.uid = uid;
        this.friend = friend;
        this.avatar = avatar;
        this.isBlack = isBlack;
        this.isTop = isTop;
        this.nickname = nickname;
    }

    @Override
    @NonNull
    public String toString() {
        return "Friend{" +
                "uid='" + uid + '\'' +
                ", friend='" + friend + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isBlack=" + isBlack +
                ", isTop=" + isTop +
                ", nickname=" + nickname +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    @Override
    public String getId() {
        return friend;
    }

    @Override
    public String getName() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getBlack() {
        return isBlack;
    }

    public void setBlack(Boolean black) {
        isBlack = black;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
