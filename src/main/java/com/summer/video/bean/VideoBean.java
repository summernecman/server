package com.summer.video.bean;

import com.summer.base.bean.BaseBean;
import com.summer.user.bean.UserBean;
import com.summer.videocomment.VideoCommentBean;
import com.summer.videodetail.VideoDetailBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoBean extends BaseBean {

    public static int type_video_record = 1;

    public static int type_video = 2;

    public static int type_voice = 3;

    private int id;

    private String file;

    private String created;

    private int  fromid;

    private int toid;

    private String fromphone;

    private String tophone;

    private long timenum;

    private UserBean fromUser;

    private UserBean toUser;

    private int uploaded;

    private boolean isfrom = false;

    private int callstate;

    private ArrayList<VideoDetailBean> videodetail;

    private ArrayList<VideoCommentBean> videoCommentBeans;

    private String videotips ="";

    private int commentid;

    private boolean isRecord;

    private boolean video;

    private int type;

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
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

    public int getUploaded() {
        return uploaded;
    }

    public void setUploaded(int uploaded) {
        this.uploaded = uploaded;
    }

    public boolean isfrom() {
        return isfrom;
    }

    public void setIsfrom(boolean isfrom) {
        this.isfrom = isfrom;
    }

    public int getCallstate() {
        return callstate;
    }

    public void setCallstate(int callstate) {
        this.callstate = callstate;
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

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean record) {
        isRecord = record;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
