package com.summer.videodetail;

import com.summer.base.bean.BaseResBean;
import com.summer.video.bean.VideoBean;
import com.summer.video.bean.VideoBean1;

/**
 * Created by SWSD on 2017-11-08.
 */
public interface VideoDetailI {

    public BaseResBean getVideoDetail(VideoBean videoBean);

    public BaseResBean getVideoDetail(VideoBean1 videoBean);

    public BaseResBean insertVideo(VideoDetailBean v);

    public BaseResBean updateUpload(VideoDetailBean v);


}
