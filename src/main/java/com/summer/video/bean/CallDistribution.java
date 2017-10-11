package com.summer.video.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-10-11.
 */
public class CallDistribution extends BaseBean{

    private int engineer;

    private int customer;

    private int server;

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }
}
