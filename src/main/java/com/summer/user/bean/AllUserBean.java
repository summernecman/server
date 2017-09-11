package com.summer.user.bean;

import com.summer.base.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-11.
 */
public class AllUserBean extends BaseBean {


    private UserBean me;

    private ArrayList<UserBean> other;

    public UserBean getMe() {
        return me;
    }

    public void setMe(UserBean me) {
        this.me = me;
    }

    public ArrayList<UserBean> getOther() {
        return other;
    }

    public void setOther(ArrayList<UserBean> other) {
        this.other = other;
    }
}
