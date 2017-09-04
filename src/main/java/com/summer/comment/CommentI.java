package com.summer.comment;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.VideoBean;

/**
 * Created by SWSD on 17-08-29.
 */
public interface CommentI {

    public BaseResBean getCommentByUserName(UserBean userBean);

    public BaseResBean getTips(UserBean userBean);

    public BaseResBean getVideoCommentByVideoName(VideoBean videoBean);
}
