package com.summer.videotip;

import com.summer.base.bean.BaseBean;

import java.sql.Timestamp;

/**
 * Created by SWSD on 2017-11-09.
 */
public class VideoTipBean extends BaseBean {

    private int id;

    private Timestamp ctime;

    private Timestamp utime;

    private  String type;

    private String txt;

    private int enable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
