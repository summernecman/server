package com.summer.agree;

import com.summer.base.bean.BaseResBean;

/**
 * Created by SWSD on 17-09-12.
 */
public interface AgreeI  {

    public BaseResBean addAgree(AgreeBean agree);

    public BaseResBean cancleAgree(AgreeBean agree);

    public BaseResBean getAgreeNum(AgreeBean agree);

    public BaseResBean isAgreeNum(AgreeBean agree);

}
