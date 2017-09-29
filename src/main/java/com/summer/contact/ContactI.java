package com.summer.contact;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-26.
 */
public interface ContactI  {

    public BaseResBean getContactsByUserId(UserBean userBean);

    public BaseResBean getContactsByUserId2(UserBean userBean);

    public BaseResBean getContactsByUserIdWithOutAgree(UserBean userBean);

    public BaseResBean addContactsByUserid(ContactBean bean);

    public BaseResBean isContactsExits(ContactBean bean);
}
