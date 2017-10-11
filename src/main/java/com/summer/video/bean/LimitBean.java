package com.summer.video.bean;

import com.summer.base.bean.BaseBean;

import java.util.Date;

/**
 * Created by SWSD on 17-09-26.
 */
public class LimitBean  extends BaseBean {

    private int pagesize;

    private int pagestart;

    private String start;

    private String end;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagestart() {
        return pagestart;
    }

    public void setPagestart(int pagestart) {
        this.pagestart = pagestart;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
