package com.summer.mybatis.entity;

public class Unit {
    private Integer id;

    private Integer unittype;

    private String unitname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnittype() {
        return unittype;
    }

    public void setUnittype(Integer unittype) {
        this.unittype = unittype;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }
}