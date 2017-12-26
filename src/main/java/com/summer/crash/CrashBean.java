package com.summer.crash;

import com.summer.base.bean.BaseBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-08.
 */
public class CrashBean extends BaseBean {

    private int id;

    private String error;

    private String createdtime;

    private UserBean userBean;

    private String platform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
