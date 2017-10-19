package com.summer.share;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-06.
 */
public class ShareBean extends BaseBean {

    private int id;

    private int videoid;

    private int sendid;

    private int receiptid;

    private int pagesize;

    private int pagestart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public int getSendid() {
        return sendid;
    }

    public void setSendid(int sendid) {
        this.sendid = sendid;
    }

    public int getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(int receiptid) {
        this.receiptid = receiptid;
    }

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
