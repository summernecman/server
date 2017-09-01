package com.summer.comment.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-01.
 */
public class TipBean extends BaseBean {
    private int position;

    private String tip;

    private int num;

    private boolean select;

    public TipBean() {
    }

    public TipBean(int position, String tip, int num, boolean select) {
        this.position = position;
        this.tip = tip;
        this.num = num;
        this.select = select;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
