package com.summer.contact;

import com.summer.base.bean.BaseBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-27.
 */
public class HistoryBean extends BaseBean {

    private UserBean userBean;

    private int id;

    private int num;

    private String name;

    private String date;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
