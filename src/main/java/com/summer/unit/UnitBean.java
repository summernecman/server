package com.summer.unit;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-11.
 */
public class UnitBean extends BaseBean {

    private int id;

    private int unittype;

    private String unitname;

    public UnitBean() {
    }

    public UnitBean(int id) {
        this.id = id;
    }

    public UnitBean(int id, int unittype, String unitname) {
        this.id = id;
        this.unittype = unittype;
        this.unitname = unitname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnittype() {
        return unittype;
    }

    public void setUnittype(int unittype) {
        this.unittype = unittype;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}
