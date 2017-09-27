package com.summer.user.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-27.
 */
public class WebIndexInfo extends BaseBean {

    private int serveronline;

    private int serverall;

    private int engineerline;

    private int engineerall;

    private int chatingnum;

    private int userall;

    private int videonum;

    private long chattimes;

    public int getServeronline() {
        return serveronline;
    }

    public void setServeronline(int serveronline) {
        this.serveronline = serveronline;
    }

    public int getServerall() {
        return serverall;
    }

    public void setServerall(int serverall) {
        this.serverall = serverall;
    }

    public int getEngineerline() {
        return engineerline;
    }

    public void setEngineerline(int engineerline) {
        this.engineerline = engineerline;
    }

    public int getEngineerall() {
        return engineerall;
    }

    public void setEngineerall(int engineerall) {
        this.engineerall = engineerall;
    }

    public int getChatingnum() {
        return chatingnum;
    }

    public void setChatingnum(int chatingnum) {
        this.chatingnum = chatingnum;
    }

    public int getUserall() {
        return userall;
    }

    public void setUserall(int userall) {
        this.userall = userall;
    }

    public int getVideonum() {
        return videonum;
    }

    public void setVideonum(int videonum) {
        this.videonum = videonum;
    }

    public long getChattimes() {
        return chattimes;
    }

    public void setChattimes(long chattimes) {
        this.chattimes = chattimes;
    }
}
