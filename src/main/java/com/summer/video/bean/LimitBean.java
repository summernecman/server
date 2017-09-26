package com.summer.video.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-26.
 */
public class LimitBean  extends BaseBean {

    private int pagesize;

    private int pagestart;

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
}
