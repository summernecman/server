package com.summer.video;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean;
import com.summer.video.bean.VideoTimeBean;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoOpe implements VideoI {

    UserI userI = new UserOpe();

    public BaseResBean getVideos(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE  id = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                videoBean.setCreated(set.getString(set.findColumn("created")));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getVideoByName(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from video WHERE  file = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            set  = ps.executeQuery();
            while (set.next()){
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                videoBean.setCreated(set.getString(set.findColumn("created")));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(videoBean);
        return baseResBean;
    }

    public BaseResBean getVideoByVideoId(VideoBean v) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE  id = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,v.getId());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                videoBean.setCreated(set.getString(set.findColumn("created")));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getVideosByUserPhone(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE  fromphone = ? or tophone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            ps.setString(2,userBean.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                videoBean.setCreated(set.getString(set.findColumn("created")));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean addVideo(VideoBean videoBean) {
        UserI userI = new UserOpe();
        UserBean fromuser  = new UserBean();
        fromuser.setPhone(videoBean.getFromphone());
        BaseResBean fromres= userI.getUserInfoByPhone(fromuser);
        UserBean fromuserbean = (UserBean) fromres.getData();

        UserBean touser  = new UserBean();
        touser.setPhone(videoBean.getTophone());
        BaseResBean tores= userI.getUserInfoByPhone(touser);
        UserBean touserbean = (UserBean) tores.getData();



        BaseResBean baseResBean = new BaseResBean();
        String str = "INSERT  into  video(file,created,fromid,toid,fromphone,tophone,timenum) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            ps.setString(2,videoBean.getCreated());
            ps.setInt(3,fromuserbean.getId());
            ps.setInt(4,touserbean.getId());
            ps.setString(5,videoBean.getFromphone());
            ps.setString(6,videoBean.getTophone());
            ps.setLong(7,videoBean.getTimenum());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        return getVideoByName(videoBean);
    }

    public BaseResBean getVideosByContacts(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE  fromphone = ? or tophone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            ps.setString(2,userBean.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                videoBean.setCreated(set.getString(set.findColumn("created")));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        ArrayList<ArrayList<VideoBean>> data = new ArrayList<ArrayList<VideoBean>>();
        HashMap<String,ArrayList<VideoBean>> map = new HashMap<String, ArrayList<VideoBean>>();
        for(int i=0;i<videos.size();i++){
            if(videos.get(i).getFromphone().equals(userBean.getPhone())){
                if(map.get(videos.get(i).getTophone())==null){
                    map.put(videos.get(i).getTophone(),new ArrayList<VideoBean>());
                }
                map.get(videos.get(i).getTophone()).add(videos.get(i));
            }else{
                if(map.get(videos.get(i).getFromphone())==null){
                    map.put(videos.get(i).getFromphone(),new ArrayList<VideoBean>());
                }
                map.get(videos.get(i).getFromphone()).add(videos.get(i));
            }
        }
        Iterator<String> key = map.keySet().iterator();
        while (key.hasNext()){
            ArrayList<VideoBean> videoBeen = new ArrayList<VideoBean>();
            String k = key.next();
            for(int i=0;i<map.get(k).size();i++){
                videoBeen.add(map.get(k).get(i));
            }
            data.add(videoBeen);
        }
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(i).size();j++){
                UserBean u  = new UserBean();
                u.setPhone(data.get(i).get(j).getFromphone());
                data.get(i).get(j).setFromUser((UserBean) userI.getUserInfoByPhone(u).getData());

                UserBean u1  = new UserBean();
                u1.setPhone(data.get(i).get(j).getTophone());
                data.get(i).get(j).setToUser((UserBean) userI.getUserInfoByPhone(u1).getData());
            }
        }
        baseResBean.setData(data);
        return baseResBean;
    }

    public BaseResBean commentVideos(CommentBean commentBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into comment(rate,tips,remark,created,videoname,fromuser,touser) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setFloat(1,commentBean.getRate());
            ps.setString(2,commentBean.getTips());
            ps.setString(3,commentBean.getRemark());
            ps.setString(4,commentBean.getCreated());
            ps.setString(5,commentBean.getVideoname());
            ps.setString(6,commentBean.getFromuser());
            ps.setString(7,commentBean.getTouser());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(commentBean);
        return baseResBean;
    }

    public BaseResBean getUserCallInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        VideoTimeBean videoTimeBean = new VideoTimeBean();
        videoTimeBean.setTimehours((Long) getUserCallTimeInfo(user).getData());
        videoTimeBean.setTimein((Integer) getUserCallInInfo(user).getData());
        videoTimeBean.setTimeout((Integer) getUserCallOutInfo(user).getData());
        baseResBean.setData(videoTimeBean);
       return baseResBean;
    }

    public BaseResBean getUserCallInInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(tophone) from video WHERE tophone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            set  = ps.executeQuery();
            set.next();
            num = set.getInt(1);
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

    public BaseResBean getUserCallOutInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(fromphone) from video WHERE fromphone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            set  = ps.executeQuery();
            set.next();
            num = set.getInt(1);
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

    public BaseResBean getUserCallTimeInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select sum(timenum) from video WHERE fromphone = ? or tophone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            ps.setString(2,user.getPhone());
            set  = ps.executeQuery();
            set.next();
            num = set.getLong(1);
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


}
