package com.summer.videodetail;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBI;
import com.summer.video.bean.VideoBean;
import com.summer.video.bean.VideoBean1;

import java.util.Date;

/**
 * Created by SWSD on 2017-11-08.
 */
public class VideoDetailOpe implements VideoDetailI {

    public BaseResBean getVideoDetail(VideoBean videoBean) {
        return DBI.executeQuery(VideoDetailBean.class,"select * from videodetail where callid = ? ",videoBean.getId());
    }

    public BaseResBean getVideoDetail(VideoBean1 videoBean) {
        return DBI.executeQuery(VideoDetailBean.class,"select * from videodetail where callid = ? ",videoBean.getId());
    }

    public BaseResBean insertVideo(VideoDetailBean v) {
        return DBI.execute("insert into videodetail(ctime,utime,callid,url,uploaded,userid,time) values(?,?,?,?,?,?,?)",
                new Date(),new Date(),v.getCallid(),v.getUrl(),v.getUploaded(),v.getUserid(),v.getTime());
    }

    public BaseResBean updateUpload(VideoDetailBean v) {
        return DBI.execute("update videodetail set uploaded = ? where url = ?",1,v.getUrl());
    }

}
