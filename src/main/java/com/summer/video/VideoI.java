package com.summer.video;

import com.summer.base.bean.BaseResBean;
import com.summer.contact.ContactBean;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.LimitBean;
import com.summer.video.bean.VideoBean;

/**
 * Created by SWSD on 17-08-24.
 */
public interface VideoI {

    public BaseResBean getVideos(UserBean userBean);

    public BaseResBean getVideoByName(VideoBean videoBean);

    public BaseResBean getAllVideos();

    public BaseResBean getAllVideosCount();

    public BaseResBean getAllVideosWithLimit(LimitBean limitBean);

    public BaseResBean getVideoByVideoId(VideoBean videoBean);

    public BaseResBean getVideosByUserPhone(UserBean userBean);

    public BaseResBean getVideosByBothUserId(ContactBean contactBean);

    public BaseResBean getVideosByBothUserIdWithLimit(ContactBean contactBean);

    public BaseResBean getVideosByBothUserIdWithLimitAndSeach(ContactBean contactBean);

    public BaseResBean addVideo(VideoBean videoBean);

    public BaseResBean insert_and_getid_fromvieo(VideoBean videoBean);

    public BaseResBean updateVideoById(VideoBean videoBean);

    public BaseResBean getVideosByContacts(UserBean userBean);

    public BaseResBean getVideosByContacts2(UserBean userBean);

    public BaseResBean getByContacts(UserBean userBean);

    public BaseResBean commentVideos(CommentBean commentBean);

    public BaseResBean getUserCallInfo(UserBean user);

    public BaseResBean getUserCallInInfo(UserBean user);

    public BaseResBean getUserCallOutInfo(UserBean user);

    public BaseResBean getUserCallTimeInfo(UserBean user);

    public BaseResBean getUserCallTimeInfoById(UserBean user);


    public BaseResBean getUserCallTimeInfoByIdWithTimeLimit(UserBean user);

    public BaseResBean getCallTimeInfo();

    public BaseResBean isVideoUploaded(VideoBean videoBean);

    public BaseResBean getVideoUploadedNum(UserBean userBean);

    public BaseResBean setVideoUploaded(VideoBean videoBean);

    public BaseResBean getMaxVideoId();

    public BaseResBean getVideoNum();

    public BaseResBean getCallNum();

    public BaseResBean getUnUploadVideoNum(UserBean u);

    public BaseResBean getOutCallTimeDistribution();

    public BaseResBean getInCallTimeDistribution();

    public BaseResBean getVideoNameById(VideoBean videoBean);

    public BaseResBean updateCallState(VideoBean videoBean);

    public BaseResBean updateVideoCallTimeNum(VideoBean videoBean);

}
