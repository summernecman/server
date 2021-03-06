package com.summer.user;

import com.summer.base.OnFinishListener;
import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.AllUserBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.LimitBean;

import javax.sql.rowset.BaseRowSet;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-23.
 */
public interface UserI  {


    public BaseResBean getUserNum();

    public BaseResBean getUserState(UserBean user);

    public BaseResBean setUserState(UserBean userBean);

    public BaseResBean getUserList();


    public BaseResBean getShortUserList();

    public BaseResBean getUnTypeUserList(UserBean userBean);

    public BaseResBean getUnTypeUserShortList(UserBean userBean);

    public BaseResBean getUnTypeUserShortListWithOutMe(UserBean userBean);

    public BaseResBean getUserListWithOutMe(UserBean userBean);

    public BaseResBean registed(String username);

    public BaseResBean registOnEM(UserBean userBean, OnFinishListener onFinishListener);

    public BaseResBean regist(UserBean user);

    public BaseResBean resetPwd(UserBean user);

    public BaseResBean login(UserBean user);

    public BaseResBean logout(UserBean user);

    public BaseResBean getUserInfoByPhone(UserBean user);

    public BaseResBean getUserShortInfoByPhone(UserBean user);

    public BaseResBean getLoginInfo(UserBean user);

    public BaseResBean setLoginInfo(UserBean user);

    public BaseResBean setHeadUrl(UserBean user);

    public BaseResBean setUserName(UserBean user);

    public BaseResBean getUsersInfoByPhone(ArrayList<UserBean> list);

    public BaseResBean getOtherUsersInfoByPhone(AllUserBean allUserBean);

    public BaseResBean getOtherUsersShortInfoByPhone(AllUserBean allUserBean);

    public BaseResBean getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> list);

    public BaseResBean getUserTypeInfoByPhone(UserBean user);

    public BaseResBean getUserTypeInfoById(UserBean user);

    public BaseResBean getUserInfoById(UserBean userBean);

    public BaseResBean getUserShortInfoById(UserBean userBean);

    public BaseResBean updateUnitInfo(UserBean userBean);

    public BaseResBean updateUUUid(UserBean userBean);

    public BaseResBean getUserListWithType(UserBean userBean);

    public BaseResBean getServerAndEngneerInfo();

    public BaseResBean getServerAndEngneershortInfo();

    public BaseResBean getUserListWithTypeAndLimit(UserBean userBean);

    public BaseResBean getUserNumWithType(UserBean userBean);

    public BaseResBean getServerAndEngneerNum();

    public BaseResBean addUser(UserBean userBean);

    public BaseResBean addUser2(UserBean userBean);

    public BaseResBean isUserExist(UserBean userBean);


    public BaseResBean updateRemark(UserBean userBean);

    public BaseResBean getUserNums(UserBean userBean);

    public BaseResBean getHeadUrlById(UserBean userBean);

    public BaseResBean getUserIdByPhone(UserBean userBean);

    public BaseResBean updateUserChatTimeById(UserBean userBean);

    public BaseResBean updateRateByUserId(UserBean userBean);

    public BaseResBean getRates();

    public BaseResBean getChatTimes();

    public BaseResBean getUserAreaUser(UserBean u);


}
