package com.summer.user.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-27.
 */
public class WebIndexInfo extends BaseBean {

    private long serveronline;

    private long serverall;

    private long customall;

    private long engineerline;

    private long engineerall;

    private long chatingnum;

    private long userall;

    private long videonum;

    private long chattimes;

    public long getServeronline() {
        return serveronline;
    }

    public void setServeronline(long serveronline) {
        this.serveronline = serveronline;
    }

    public long getServerall() {
        return serverall;
    }

    public void setServerall(long serverall) {
        this.serverall = serverall;
    }

    public long getCustomall() {
        return customall;
    }

    public void setCustomall(long customall) {
        this.customall = customall;
    }

    public long getEngineerline() {
        return engineerline;
    }

    public void setEngineerline(long engineerline) {
        this.engineerline = engineerline;
    }

    public long getEngineerall() {
        return engineerall;
    }

    public void setEngineerall(long engineerall) {
        this.engineerall = engineerall;
    }

    public long getChatingnum() {
        return chatingnum;
    }

    public void setChatingnum(long chatingnum) {
        this.chatingnum = chatingnum;
    }

    public long getUserall() {
        return userall;
    }

    public void setUserall(long userall) {
        this.userall = userall;
    }

    public long getVideonum() {
        return videonum;
    }

    public void setVideonum(long videonum) {
        this.videonum = videonum;
    }

    public long getChattimes() {
        return chattimes;
    }

    public void setChattimes(long chattimes) {
        this.chattimes = chattimes;
    }
}
