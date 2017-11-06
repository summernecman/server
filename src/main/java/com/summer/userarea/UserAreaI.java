package com.summer.userarea;

import com.summer.area.AreaBean;
import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;
import com.sun.javafx.geom.Area;

/**
 * Created by SWSD on 17-09-29.
 */
public interface UserAreaI {

    public BaseResBean getUserAreaById(UserBean userBean);

    public BaseResBean addUserArea(UserAreaBean userAreaBean);

    public BaseResBean isUserHaveArea(UserAreaBean userAreaBean);

    public BaseResBean delete(UserBean userBean);

    public BaseResBean getAreaUsesByAreaId(AreaBean areaBean);

}
