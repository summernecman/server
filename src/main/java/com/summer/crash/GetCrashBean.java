package com.summer.crash;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 2017-12-25.
 */
public class GetCrashBean extends BaseBean {

    private int start;

    private int num;

    public GetCrashBean() {
    }

    public GetCrashBean(int start, int num) {
        this.start = start;
        this.num = num;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
