package com.knilim.knilim.data.model.IUser;

import androidx.annotation.NonNull;

import com.stfalcon.chatkit.commons.models.IUser;

public class User implements IUser {
    private String id;
    private String nickname;
    private String email;
    private String phone;
    private String passWord;
    private String avatar;
    private String signature;
    private Boolean sex;
    private String location;
    private String birthday;

    public User() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public User(String id, String nickname, String email, String phone, String passWord, String avatar, String signature, Boolean sex, String location, String birthday) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.passWord = passWord;
        this.avatar = avatar;
        this.signature = signature;
        this.sex = sex;
        this.location = location;
        this.birthday = birthday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passWord='" + passWord + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", sex=" + sex +
                ", location='" + location + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
