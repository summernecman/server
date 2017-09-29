package com.summer.area;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-29.
 */
public class AreaBean extends BaseBean {

    private int id;

    private String name;

    public AreaBean() {
    }

    public AreaBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
