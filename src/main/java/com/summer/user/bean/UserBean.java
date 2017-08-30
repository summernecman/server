package com.summer.user.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-08-23.
 */
public class UserBean extends BaseBean {

    private int id;

    private String phone;

    private String pwd;

    private int usertype;

    private String belong;

    private String name;

    private int state;

    private String uuuid;

    private String headurl;

    public static final int STATE_OFFLINE  = 0;

    public static final int STATE_ONLINE  = 1;

    public static final int STATE_INVIDEO  = 2;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUuuid() {
        return uuuid;
    }

    public void setUuuid(String uuuid) {
        this.uuuid = uuuid;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
}
