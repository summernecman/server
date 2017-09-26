package com.summer.comment.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-26.
 */
public class RateLevelBean extends BaseBean{

    private int rate;

    private int num;

    private float ratef;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getRatef() {
        return ratef;
    }

    public void setRatef(float ratef) {
        this.ratef = ratef;
    }
}
