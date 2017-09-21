package com.summer.em.bean;

import com.summer.base.bean.BaseBean;
import com.summer.util.GsonUtil;

/**
 * Created by SWSD on 17-09-14.
 */
public class EMUserStatusBean extends BaseBean {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isOnline(){
        if(GsonUtil.getInstance().toJson(getData()).contains("online")){
            return true;
        }
        return false;
    }
}


