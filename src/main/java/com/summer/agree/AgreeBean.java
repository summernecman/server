package com.summer.agree;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-12.
 */
public class AgreeBean extends BaseBean {

    private int id;

    private int commentid;

    private int agreeid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getAgreeid() {
        return agreeid;
    }

    public void setAgreeid(int agreeid) {
        this.agreeid = agreeid;
    }
}
