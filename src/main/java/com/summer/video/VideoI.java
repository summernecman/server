package com.summer.video;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.VideoBean;

/**
 * Created by SWSD on 17-08-24.
 */
public interface VideoI {

    public BaseResBean getVideos(UserBean userBean);

    public BaseResBean getVideoByName(VideoBean videoBean);

    public BaseResBean getVideosByUserPhone(UserBean userBean);

    public BaseResBean addVideo(VideoBean videoBean);

    public BaseResBean getVideosByContacts(UserBean userBean);

    public BaseResBean commentVideos(CommentBean commentBean);
}
