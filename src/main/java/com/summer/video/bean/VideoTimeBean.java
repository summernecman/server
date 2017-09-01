package com.summer.video.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-09-01.
 */
public class VideoTimeBean extends BaseBean{

    int timeout;

    int timein;

    long timehours;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimein() {
        return timein;
    }

    public void setTimein(int timein) {
        this.timein = timein;
    }

    public long getTimehours() {
        return timehours;
    }

    public void setTimehours(long timehours) {
        this.timehours = timehours;
    }
}
