package com.summer.share;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBUtil;
import com.summer.user.bean.UserBean;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
import com.summer.video.bean.VideoBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-06.
 */
public class ShareOpe implements ShareI {


    VideoI videoI = new VideoOpe();

    public BaseResBean share(ShareBean shareBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into share(videoid,sendid,receiptid) VALUES (?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,shareBean.getVideoid());
            ps.setInt(2,shareBean.getSendid());
            ps.setInt(3,shareBean.getReceiptid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(shareBean);
        return baseResBean;
    }

    public BaseResBean getShareNumByUserPhone(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "SELECT count(id) from share WHERE receiptid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num  = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set = ps.executeQuery();
            set.next();
            num =  set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(num);
        return baseResBean;
    }

    public BaseResBean getSharesByReceipt(ShareBean shareBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from share WHERE receiptid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<ShareBean> list = new ArrayList<ShareBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,shareBean.getReceiptid());
            set = ps.executeQuery();
            while (set.next()){
                ShareBean shareBean1 = new ShareBean();
                shareBean1.setId(set.getInt(set.findColumn("id")));
                shareBean1.setSendid(set.getInt(set.findColumn("sendid")));
                shareBean1.setReceiptid(set.getInt(set.findColumn("receiptid")));
                shareBean1.setVideoid(set.getInt(set.findColumn("videoid")));
                list.add(shareBean1);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        for(int i=0;i<list.size();i++){
            VideoBean videoBean = new VideoBean();
            videoBean.setId(list.get(i).getVideoid());
            ArrayList<VideoBean> v  = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(v!=null && v.size()>0){
                videos.add(v.get(0));
            }
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getSharesByReceiptWithLimit(ShareBean shareBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from share WHERE receiptid = ? limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<ShareBean> list = new ArrayList<ShareBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,shareBean.getReceiptid());
            ps.setInt(2,shareBean.getPagestart()*shareBean.getPagesize());
            ps.setInt(3,shareBean.getPagesize());
            set = ps.executeQuery();
            while (set.next()){
                ShareBean shareBean1 = new ShareBean();
                shareBean1.setId(set.getInt(set.findColumn("id")));
                shareBean1.setSendid(set.getInt(set.findColumn("sendid")));
                shareBean1.setReceiptid(set.getInt(set.findColumn("receiptid")));
                shareBean1.setVideoid(set.getInt(set.findColumn("videoid")));
                list.add(shareBean1);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        for(int i=0;i<list.size();i++){
            VideoBean videoBean = new VideoBean();
            videoBean.setId(list.get(i).getVideoid());
            ArrayList<VideoBean> v  = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(v!=null && v.size()>0){
                videos.add(v.get(0));
            }
        }
        baseResBean.setData(videos);
        return baseResBean;
    }
}
