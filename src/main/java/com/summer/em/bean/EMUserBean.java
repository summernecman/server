package com.summer.em.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-14.
 */
public class EMUserBean extends BaseBean{

    private String username;

    private String password;

    public EMUserBean() {
    }

    public EMUserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
