package com.summer.video.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoBean extends BaseBean {

    private int id;

    private String file;

    private String created;

    private int  fromid;

    private int toid;

    private String fromphone;

    private String tophone;

    private long timenum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getFromphone() {
        return fromphone;
    }

    public void setFromphone(String fromphone) {
        this.fromphone = fromphone;
    }

    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone;
    }

    public long getTimenum() {
        return timenum;
    }

    public void setTimenum(long timenum) {
        this.timenum = timenum;
    }
}
