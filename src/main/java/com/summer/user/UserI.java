package com.summer.user;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-23.
 */
public interface UserI  {

    public BaseResBean getUserState(UserBean user);

    public BaseResBean setUserState(UserBean userBean);

    public BaseResBean getUserList();

    public BaseResBean getUserListWithOutMe(UserBean userBean);

    public BaseResBean registed(String username);

    public BaseResBean regist(UserBean user);

    public BaseResBean login(UserBean user);

    public BaseResBean logout(UserBean user);

    public BaseResBean getUserInfoByPhone(UserBean user);

    public BaseResBean getLoginInfo(UserBean user);

    public BaseResBean setLoginInfo(UserBean user);

    public BaseResBean setHeadUrl(UserBean user);

    public BaseResBean setUserName(UserBean user);

    public BaseResBean getUsersInfoByPhone(ArrayList<UserBean> list);

    public BaseResBean getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> list);

}
