package com.summer.userarea;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-29.
 */
public class UserAreaBean extends BaseBean {

    private int id;

    private int userid;

    private int areaid;

    public UserAreaBean() {
    }

    public UserAreaBean(int userid, int areaid) {
        this.userid = userid;
        this.areaid = areaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
        this.areaid = areaid;
    }
}
