package com.summer.em;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-14.
 */
public interface EMI {

    public BaseResBean getEMUserStatus(UserBean userBean);
}
