package com.summer.share;

import com.summer.base.bean.BaseResBean;

/**
 * Created by SWSD on 17-09-06.
 */
public interface ShareI  {

    public BaseResBean share(ShareBean shareBean);

    public BaseResBean getSharesByReceipt(ShareBean shareBean);
}
