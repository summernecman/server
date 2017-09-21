package com.summer.agree;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-13.
 */
public class AgreeNumBean  extends BaseBean{

    private boolean agree;

    private int agreenum;

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public int getAgreenum() {
        return agreenum;
    }

    public void setAgreenum(int agreenum) {
        this.agreenum = agreenum;
    }
}
