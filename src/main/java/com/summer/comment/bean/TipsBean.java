package com.summer.comment.bean;

import com.summer.base.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-01.
 */
public class TipsBean extends BaseBean {

    ArrayList<TipBean> tipBeen = new ArrayList<TipBean>();

    public ArrayList<TipBean> getTipBeen() {
        return tipBeen;
    }

    public void setTipBeen(ArrayList<TipBean> tipBeen) {
        this.tipBeen = tipBeen;
    }
}
