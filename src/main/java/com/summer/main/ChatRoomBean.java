package com.summer.main;

import com.summer.base.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-22.
 */
public class ChatRoomBean extends BaseBean {

    private ArrayList<Object> entities;

    private ArrayList<Object> data;

    private int count;

    public ArrayList<Object> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Object> entities) {
        this.entities = entities;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
