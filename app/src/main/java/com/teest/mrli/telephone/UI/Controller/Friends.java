package com.teest.mrli.telephone.UI.Controller;

/**
 * Created by Mr.Li on 2018/11/13.
 */

public class Friends implements Comparable<Friends> {
    private String id;//id
    private int avatar;//头像
    private String name;//名
    private String telephone;//电话号
    private String email;//邮箱
    private String pinyin; // 姓名对应的拼音
    private String firstLetter; // 拼音的首字母
    private String everfirstLetter;//每个文字的首字母
    private String collected;//是否被收藏，1是被收藏，2是没有收藏

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollected() {
        return collected;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public Friends(String id, String name,String telephone,String email)
    {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public Friends(String name) {
        this.name = name;
        pinyin = Pinyin.getPinYin(name); // 根据姓名获取拼音
        firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
            pinyin = name.substring(0,1);
        }
        everfirstLetter = Pinyin.getPinYinHeadChar(name);
    }


    public String getPinyin() {
        return pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public String getEverfirstLetter(){
        return everfirstLetter;
    }


    public int compareTo(Friends another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")){
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }
}
