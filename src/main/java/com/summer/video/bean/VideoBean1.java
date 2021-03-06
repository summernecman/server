package com.summer.video.bean;

import com.summer.base.bean.BaseBean;
import com.summer.user.bean.UserBean;
import com.summer.videocomment.VideoCommentBean;
import com.summer.videodetail.VideoDetailBean;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoBean1 extends BaseBean {

    private int id;

    private String file;

    private Timestamp created;

    private int  fromid;

    private int toid;

    private String fromphone;

    private String tophone;

    private long timenum;

    private UserBean fromUser;

    private UserBean toUser;

    private long uploaded;

    private ArrayList<VideoDetailBean> videodetail;

    private ArrayList<VideoCommentBean> videoCommentBeans;

    private String videotips ="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getFromid() {
        return fromid;
    }

    public void setFromid(int fromid) {
        this.fromid = fromid;
    }

    public int getToid() {
        return toid;
    }

    public void setToid(int toid) {
        this.toid = toid;
    }

    public String getFromphone() {
        return fromphone;
    }

    public void setFromphone(String fromphone) {
        this.fromphone = fromphone;
    }

    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone;
    }

    public long getTimenum() {
        return timenum;
    }

    public void setTimenum(long timenum) {
        this.timenum = timenum;
    }

    public UserBean getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserBean fromUser) {
        this.fromUser = fromUser;
    }

    public UserBean getToUser() {
        return toUser;
    }

    public void setToUser(UserBean toUser) {
        this.toUser = toUser;
    }

    public long getUploaded() {
        return uploaded;
    }

    public void setUploaded(long uploaded) {
        this.uploaded = uploaded;
    }

    public ArrayList<VideoDetailBean> getVideodetail() {
        return videodetail;
    }

    public void setVideodetail(ArrayList<VideoDetailBean> videodetail) {
        this.videodetail = videodetail;
    }

    public ArrayList<VideoCommentBean> getVideoCommentBeans() {
        return videoCommentBeans;
    }

    public void setVideoCommentBeans(ArrayList<VideoCommentBean> videoCommentBeans) {
        this.videoCommentBeans = videoCommentBeans;
    }

    public String getVideotips() {
        return videotips;
    }

    public void setVideotips(String videotips) {
        this.videotips = videotips;
    }
}
