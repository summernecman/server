package com.summer.share;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

/**
 * Created by SWSD on 17-09-06.
 */
public interface ShareI  {

    public BaseResBean share(ShareBean shareBean);

    public BaseResBean getShareNumByUserPhone(UserBean userBean);

    public BaseResBean getSharesByReceipt(ShareBean shareBean);

    public BaseResBean getSharesByReceiptWithLimit(ShareBean shareBean);
}
