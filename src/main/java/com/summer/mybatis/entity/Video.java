package com.summer.mybatis.entity;

import java.util.Date;

public class Video {
    private Integer id;

    private String file;

    private Date created;

    private Integer fromid;

    private Integer toid;

    private String fromphone;

    private String tophone;

    private Long timenum;

    private Integer uploaded;

    private Integer callstate;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }

    public String getFromphone() {
        return fromphone;
    }

    public void setFromphone(String fromphone) {
        this.fromphone = fromphone == null ? null : fromphone.trim();
    }

    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone == null ? null : tophone.trim();
    }

    public Long getTimenum() {
        return timenum;
    }

    public void setTimenum(Long timenum) {
        this.timenum = timenum;
    }

    public Integer getUploaded() {
        return uploaded;
    }

    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    public Integer getCallstate() {
        return callstate;
    }

    public void setCallstate(Integer callstate) {
        this.callstate = callstate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}