package com.summer.app;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 2017-12-29.
 */
public class AppBean extends BaseBean {

    private int id;

    private int versioncode;

    private String versionname;

    private String url;

    public AppBean() {
    }

    public AppBean(int versioncode, String versionname) {
        this.versioncode = versioncode;
        this.versionname = versionname;
    }

    public AppBean(int versioncode, String versionname, String url) {
        this.versioncode = versioncode;
        this.versionname = versionname;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
