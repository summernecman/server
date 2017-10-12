package com.summer.comment;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.LimitBean;
import com.summer.video.bean.VideoBean;

/**
 * Created by SWSD on 17-08-29.
 */
public interface CommentI {

    public BaseResBean getCommentsWithLimit(LimitBean limitBean);

    public BaseResBean getCommentsNum();

    public BaseResBean getCommentByUserPhone(UserBean userBean);

    public BaseResBean getCommentByUserIdWithLimit(UserBean userBean);

    public BaseResBean getOtherCommentByUserIdWithLimit(UserBean userBean);

    public BaseResBean getCommentByUserId(UserBean userBean);

    public BaseResBean getShortCommentByUserIdWithLimit(UserBean userBean);


    public BaseResBean getShortCommentByUserId(UserBean userBean);

    public BaseResBean getCommentByUserIdWithMyOption(CommentBean commentBean);

    public BaseResBean getCommentByUserIdWithMyOptionWithLimit(CommentBean commentBean);

    public BaseResBean getCommentNumByUserName(UserBean userBean);

    public BaseResBean getCommentNumByUserId(UserBean userBean);

    public BaseResBean getTips(UserBean userBean);

    public BaseResBean getTipsByUserId(UserBean userBean);

    public BaseResBean getVideoCommentByVideoName(VideoBean videoBean);

    public BaseResBean getVideoCommentByVideoNameAndFrom(VideoBean videoBean);

    public BaseResBean getVideoCommentByVideoIdAndFrom(VideoBean videoBean);

    public BaseResBean getVideoCommentsByVideoId(VideoBean videoBean);

    public BaseResBean getVideoRateCommentByUserPhone(UserBean userBean);

    public BaseResBean getVideoRateCommentByUseId(UserBean userBean);

    public BaseResBean getVideoRateCommentByUseIdWithTimeLimit(UserBean userBean);

    public BaseResBean getVideoRateCommentByVideoid(VideoBean videoBean);

    public BaseResBean getVideoCommentRateLevelByuserId(UserBean userBean);

    public BaseResBean getToVideoCommentNumByUserId(UserBean userBean);
}
