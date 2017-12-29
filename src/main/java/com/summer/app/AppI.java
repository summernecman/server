package com.summer.app;

import com.summer.base.bean.BaseResBean;

/**
 * Created by SWSD on 2017-12-29.
 */
public interface AppI {

    public BaseResBean updateVersion(AppBean appBean);

    public BaseResBean CheckVersion();


}
