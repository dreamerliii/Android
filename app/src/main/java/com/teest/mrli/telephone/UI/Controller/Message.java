package com.teest.mrli.telephone.UI.Controller;

/**
 * 消息类
 */
public class Message {
    private int avatar;   //图像
    private String name;  //名字
    private String c;//次数
    private String time;//时间
    private String state;//状态


    public int getAvatar() {
        return avatar;
    }
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getC() {
        return c;
    }
    public void setC(String c) {
        this.c = c;
    }

    public Message(int avatar, String name, String c , String time, String state) {
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.state = state;
        this.c = c;
    }



}
