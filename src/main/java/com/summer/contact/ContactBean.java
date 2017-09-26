package com.summer.contact;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-26.
 */
public class ContactBean extends BaseBean {

    private int id;

    private int fromid;

    private int toid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }
}
