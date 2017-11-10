package com.summer.contact;

import com.summer.base.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-26.
 */
public class ContactBean extends BaseBean {

    private int id;

    private int fromid;

    private int toid;

    private String txt;

    private ArrayList<String> type;


    private int pagesize;

    private int pagestart;

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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }
}
