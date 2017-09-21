package com.summer.em;

import com.summer.base.bean.BaseResBean;
import com.summer.main.Global;
import com.summer.network.HttpRequest;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;

/**
 * Created by SWSD on 17-09-14.
 */
public class EMOpe implements EMI {
    public BaseResBean getEMUserStatus(UserBean userBean) {
        BaseResBean res = HttpRequest.sendGet(Global.URL+"/users/"+userBean.getPhone()+"/status",null, Global.emTokenBean.getAccess_token());
        return res;
    }
}
