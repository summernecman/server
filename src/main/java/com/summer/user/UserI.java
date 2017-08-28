package com.summer.user;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-08-23.
 */
public interface UserI  {

    public BaseResBean getUserState(UserBean user);

    public BaseResBean setUserState(UserBean userBean);

    public BaseResBean getUserList();

    public BaseResBean registed(String username);

    public BaseResBean regist(UserBean user);

    public BaseResBean login(UserBean user);

    public BaseResBean logout(UserBean user);

    public BaseResBean getUserInfoByPhone(UserBean user);

}
