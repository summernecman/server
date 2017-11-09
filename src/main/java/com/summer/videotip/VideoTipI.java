package com.summer.videotip;

import com.summer.base.bean.BaseResBean;

/**
 * Created by SWSD on 2017-11-09.
 */
public interface VideoTipI {


    public BaseResBean getAllVideoTips();

    public BaseResBean addVideoTips(VideoTipBean v);

}
