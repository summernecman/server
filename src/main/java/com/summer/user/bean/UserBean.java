package com.summer.user.bean;

import com.summer.base.bean.BaseBean;
import com.summer.unit.UnitBean;
import com.summer.video.bean.VideoTimeBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-23.
 */
public class UserBean extends BaseBean {

    private String chatid;

    private int id;

    private String phone;

    private String pwd;

    private int usertype;

    private String belong;

    private String name;

    private int state;

    private String uuuid;

    private String headurl;

    private float avg;

    private int unitid;

    private String area;

    private UnitBean unit;

    private ArrayList<UserBean> contacts;

    private int pagesize;

    private int pagestart;

    private VideoTimeBean callinfo;

    private String remark;





    public static final int STATE_OFFLINE  = 0;

    public static final int STATE_ONLINE  = 1;

    public static final int STATE_INVIDEO  = 2;


    public static final int USER_TYPE_SERVER  = 0;

    public static final int USER_TYPE_ENGINEER  = 1;

    public static final int USER_TYPE_CUSTOMER  = 2;



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

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public UnitBean getUnit() {
        return unit;
    }

    public void setUnit(UnitBean unit) {
        this.unit = unit;
    }

    public VideoTimeBean getCallinfo() {
        return callinfo;
    }

    public void setCallinfo(VideoTimeBean callinfo) {
        this.callinfo = callinfo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ArrayList<UserBean> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<UserBean> contacts) {
        this.contacts = contacts;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagestart() {
        return pagestart;
    }

    public void setPagestart(int pagestart) {
        this.pagestart = pagestart;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "chatid='" + chatid + '\'' +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", usertype=" + usertype +
                ", belong='" + belong + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", uuuid='" + uuuid + '\'' +
                ", headurl='" + headurl + '\'' +
                ", avg=" + avg +
                ", unitid=" + unitid +
                ", area='" + area + '\'' +
                ", unit=" + unit +
                ", contacts=" + contacts +
                ", callinfo=" + callinfo +
                '}';
    }
}
