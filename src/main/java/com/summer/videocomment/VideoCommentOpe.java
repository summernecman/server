package com.summer.videocomment;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBI;
import com.summer.video.bean.VideoBean;

import java.util.Date;

/**
 * Created by SWSD on 2017-11-09.
 */
public class VideoCommentOpe implements VideoCommentI {


    public BaseResBean addVideoComment(VideoCommentBean v) {
        return DBI.execute("insert into videocomment(ctime,utime,callid,txt,type,userid) values(?,?,?,?,?,?)",
                new Date(),new Date(),v.getCallid(),v.getTxt(),v.getType(),v.getUserid());
    }

    public BaseResBean getVideoCommentByCallId(VideoBean v) {
        return DBI.executeQuery(VideoCommentBean.class,"select * from videocomment where callid = ? ",v.getId());
    }

    public BaseResBean getVideoCommentByType(VideoCommentBean v) {
        return DBI.executeQuery(VideoCommentBean.class,"select * from videocomment where type like ? ","%"+v.getType()+"%");
    }

    public BaseResBean getVideoCommentByTxt(VideoCommentBean v) {
        return DBI.executeQuery(VideoCommentBean.class,"select * from videocomment where type like ? ","%"+v.getTxt()+"%");
    }
}
