package com.summer.user.bean;

import com.summer.base.bean.BaseBean;

/**
 * Created by SWSD on 17-08-29.
 */
public class CommentBean extends BaseBean{

    private int id;

    private float rate;

    private String tips;

    private String remark;

    private String created;

    private String videoname;

    private String fromuser;

    private String touser;

    private UserBean fromUser;

    private UserBean toUser;

    private int fromid;

    private int toid;

    private int agreeNum;

    private boolean agree;

    private int videoid;

    private String headUrl;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
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

    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public void init(){
        setId(-1);
        setRemark("");
        setCreated("");
    }
}
