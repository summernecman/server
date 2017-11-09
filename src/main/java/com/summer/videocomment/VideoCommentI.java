package com.summer.videocomment;

import com.summer.base.bean.BaseResBean;
import com.summer.video.bean.VideoBean;

/**
 * Created by SWSD on 2017-11-09.
 */
public interface VideoCommentI  {

    public BaseResBean addVideoComment(VideoCommentBean v);

    public BaseResBean getVideoCommentByCallId(VideoBean v);

    public BaseResBean getVideoCommentByType(VideoCommentBean v);

    public BaseResBean getVideoCommentByTxt(VideoCommentBean v);
}
