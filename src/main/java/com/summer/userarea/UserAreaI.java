package com.summer.userarea;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-29.
 */
public interface UserAreaI {

    public BaseResBean getUserAreaById(UserBean userBean);

    public BaseResBean addUserArea(UserAreaBean userAreaBean);

    public BaseResBean isUserHaveArea(UserAreaBean userAreaBean);

    public BaseResBean delete(UserBean userBean);

}
