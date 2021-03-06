package com.summer.video;

import com.summer.base.OnFinishListener;
import com.summer.base.bean.BaseResBean;
import com.summer.contact.ContactBean;
import com.summer.contact.HistoryBean;
import com.summer.unit.DBI;
import com.summer.unit.DBUtil;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.DateFormatUtil;
import com.summer.video.bean.*;
import com.summer.videodetail.VideoDetailBean;
import com.summer.videodetail.VideoDetailOpe;

import javax.naming.NamingException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by SWSD on 17-08-24.
 */
public class VideoOpe implements VideoI {

    UserOpe userI = new UserOpe();

    VideoDetailOpe  videoDetailI;

    public void getVideoDetail(VideoBean videoBean){
        if(videoDetailI==null){
            videoDetailI = new VideoDetailOpe();
        }
        videoBean.setVideodetail((ArrayList<VideoDetailBean>) videoDetailI.getVideoDetail(videoBean).getData());
    }

    public void getVideoDetail(VideoBean1 videoBean){
        if(videoDetailI==null){
            videoDetailI = new VideoDetailOpe();
        }
        videoBean.setVideodetail((ArrayList<VideoDetailBean>) videoDetailI.getVideoDetail(videoBean).getData());
    }

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
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        for(int i=0;i<videos.size();i++){
            UserBean from = new UserBean();
            from.setId(videos.get(i).getFromid());

            UserBean to = new UserBean();
            to.setId(videos.get(i).getToid());
            videos.get(i).setFromUser((UserBean) userI.getUserInfoById(from).getData());
            videos.get(i).setToUser((UserBean) userI.getUserInfoById(to).getData());
            getVideoDetail(videos.get(i));

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
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        UserBean from = new UserBean();
        from.setId(videoBean.getFromid());

        UserBean to = new UserBean();
        to.setId(videoBean.getToid());
        videoBean.setFromUser((UserBean) userI.getUserInfoById(from).getData());
        videoBean.setToUser((UserBean) userI.getUserInfoById(to).getData());
        getVideoDetail(videoBean);
        baseResBean.setData(videoBean);
        return baseResBean;
    }

    public BaseResBean getAllVideos() {
        System.out.println(System.currentTimeMillis());
        BaseResBean baseResBean = DBI.executeQuery(VideoBean1.class,"select * from video where callstate = ? ",1);
        ArrayList<VideoBean1> videos  = (ArrayList<VideoBean1>) baseResBean.getData();
        System.out.println(System.currentTimeMillis());
        if(videos!=null){
            for(int i=0;i<videos.size();i++){
                UserBean userBean = new UserBean();
                userBean.setId(videos.get(i).getFromid());
                UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean).getData();
                videos.get(i).setFromUser(userBean1);

                UserBean userBean2 = new UserBean();
                userBean2.setId(videos.get(i).getToid());
                UserBean userBean3 = (UserBean) userI.getUserInfoById(userBean2).getData();
                videos.get(i).setToUser(userBean3);

            }
        }
        System.out.println(System.currentTimeMillis());
        return baseResBean;
    }

    public BaseResBean getAllVideosCount() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id)  from video WHERE callstate =  ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            set = ps.executeQuery();
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

    public BaseResBean getAllVideosWithLimit(LimitBean limitBean) {
        VideoBeseResBean baseResBean = new VideoBeseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video where callstate = ? limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            ps.setInt(2,limitBean.getPagestart()*limitBean.getPagesize());
            ps.setInt(3,limitBean.getPagesize());
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
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<videos.size();i++){
            UserBean userBean = new UserBean();
            userBean.setPhone(videos.get(i).getFromphone());
            UserBean userBean1 = (UserBean) userI.getUserInfoByPhone(userBean).getData();
            videos.get(i).setFromUser(userBean1);

            UserBean userBean2 = new UserBean();
            userBean2.setPhone(videos.get(i).getTophone());
            UserBean userBean3 = (UserBean) userI.getUserInfoByPhone(userBean2).getData();
            videos.get(i).setToUser(userBean3);
            getVideoDetail(videos.get(i));
        }
        baseResBean.setTotal(videos.size());
        baseResBean.setData(videos);
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
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<videos.size();i++){
            UserBean userBean = new UserBean();
            userBean.setId(videos.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean).getData();
            videos.get(i).setFromUser(userBean1);

            UserBean userBean2 = new UserBean();
            userBean2.setId(videos.get(i).getToid());
            UserBean userBean3 = (UserBean) userI.getUserInfoById(userBean2).getData();
            videos.get(i).setToUser(userBean3);
            getVideoDetail(videos.get(i));
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
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<videos.size();i++){
            UserBean from = new UserBean();
            from.setId(videos.get(i).getFromid());

            UserBean to = new UserBean();
            to.setId(videos.get(i).getToid());
            videos.get(i).setFromUser((UserBean) userI.getUserInfoById(from).getData());
            videos.get(i).setToUser((UserBean) userI.getUserInfoById(to).getData());
            getVideoDetail(videos.get(i));
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getVideosByBothUserId(ContactBean contactBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE callstate =  ? and  ((fromid = ? and toid = ?) or  (fromid = ? and toid = ?)) ORDER  BY  id DESC limit ?,? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            ps.setInt(2,contactBean.getFromid());
            ps.setInt(3,contactBean.getToid());
            ps.setInt(4,contactBean.getToid());
            ps.setInt(5,contactBean.getFromid());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                String s = set.getString(set.findColumn("created"));
                videoBean.setCreated(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS,DateFormatUtil.MM_DD_HH_MM,s.substring(0,s.length()-2)));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<videos.size();i++){
            UserBean from = new UserBean();
            from.setId(videos.get(i).getFromid());

            UserBean to = new UserBean();
            to.setId(videos.get(i).getToid());
            videos.get(i).setFromUser((UserBean) userI.getUserInfoById(from).getData());
            videos.get(i).setToUser((UserBean) userI.getUserInfoById(to).getData());
            getVideoDetail(videos.get(i));
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getVideosByBothUserIdWithLimit(ContactBean contactBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select * from video WHERE callstate =  ? and  ((fromid = ? and toid = ?) or  (fromid = ? and toid = ?)) and type = 1 ORDER  BY  id DESC limit ?,? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            ps.setInt(2,contactBean.getFromid());
            ps.setInt(3,contactBean.getToid());
            ps.setInt(4,contactBean.getToid());
            ps.setInt(5,contactBean.getFromid());
            ps.setInt(6,contactBean.getPagestart()*contactBean.getPagesize());
            ps.setInt(7,contactBean.getPagesize());
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                videoBean.setId(set.getInt(set.findColumn("id")));
                videoBean.setFile(set.getString(set.findColumn("file")));
                String s = set.getString(set.findColumn("created"));
                videoBean.setCreated(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS,DateFormatUtil.MM_DD_HH_MM,s.substring(0,s.length()-2)));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<videos.size();i++){
            UserBean from = new UserBean();
            from.setId(videos.get(i).getFromid());

            UserBean to = new UserBean();
            to.setId(videos.get(i).getToid());
            videos.get(i).setFromUser((UserBean) userI.getUserInfoById(from).getData());
            videos.get(i).setToUser((UserBean) userI.getUserInfoById(to).getData());
            getVideoDetail(videos.get(i));
        }
        baseResBean.setData(videos);
        return baseResBean;
    }

    public BaseResBean getVideosByBothUserIdWithLimitAndSeach(ContactBean contactBean) {
        BaseResBean baseResBean = new BaseResBean();

        final ArrayList<Integer> videoids = new ArrayList<Integer>();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();

        String tt = "";
        if(contactBean.getType().size()>0){
            tt+="and ";
        }
        for(int i=0;i<contactBean.getType().size();i++){
            tt+="videocomment.type = "+ contactBean.getType().get(i)+" or ";
        }

        if(contactBean.getType().size()>0){
            tt = tt.substring(0,tt.length()-" or ".length());
        }
        String sql = "select distinct video.id " +
                " from video , videocomment " +
                " where video.id = videocomment.callid " +
                " and videocomment.txt like ? " +
                tt+
                " and callstate =  ? "+
                " and ((video.fromid = ? and video.toid = ?) " +
                " or  (video.fromid = ? and video.toid = ?)) " +
                " ORDER  BY  video.id DESC limit ?,? ";



        DBI.executeQuerySet(new OnFinishListener() {
                                public void onFinish(Object o) {
                                    ResultSet set = (ResultSet) o;
                                    try {
                                        while (set.next()){
                                            videoids.add(set.getInt(set.findColumn("id")));
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                sql,
                "%"+contactBean.getTxt()+"%", 1, contactBean.getFromid(), contactBean.getToid(), contactBean.getToid(), contactBean.getFromid(), contactBean.getPagestart() * contactBean.getPagesize(), contactBean.getPagesize());

        for(int i=0;i<videoids.size();i++){
            VideoBean vv = new VideoBean();
            vv.setId(videoids.get(i));
            videos.addAll((Collection<? extends VideoBean>) getVideoByVideoId(vv).getData());
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

    public BaseResBean insert_and_getid_fromvieo(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "{call insert_and_getid_fromvieo(?,?,?,?,?,?,?,?,?)}";
        CallableStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareCall(str);
           ps.setString(1,videoBean.getFile());
            ps.setString(2,videoBean.getCreated());
            ps.setInt(3,videoBean.getFromUser().getId());
            ps.setInt(4,videoBean.getToUser().getId());
            ps.setString(5,videoBean.getFromphone());
            ps.setString(6,videoBean.getTophone());
            ps.setLong(7,videoBean.getTimenum());
            ps.setLong(8,videoBean.getType());
            ps.registerOutParameter(9,Types.INTEGER);
            ps.execute();
            videoBean.setId(ps.getInt(9));
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

    public BaseResBean updateVideoById(VideoBean videoBean) {

        BaseResBean baseResBean = new BaseResBean();
        String str = "update video set file = ? ,timenum = ?  WHERE  id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            ps.setLong(2,videoBean.getTimenum());
            ps.setInt(3,videoBean.getId());
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
                String s = set.getString(set.findColumn("created"));
                videoBean.setCreated(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS,DateFormatUtil.MM_DD_HH_MM_SS,s.substring(0,s.length()-2)));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videoBean.setFromphone(set.getString(set.findColumn("fromphone")));
                videoBean.setTophone(set.getString(set.findColumn("tophone")));
                videoBean.setTimenum(set.getLong(set.findColumn("timenum")));
                videoBean.setUploaded(set.getInt(set.findColumn("uploaded")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

                getVideoDetail(videos.get(i));
            }
        }
        baseResBean.setData(data);
        return baseResBean;
    }

    public BaseResBean getVideosByContacts2(UserBean userBean) {
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

                getVideoDetail(videos.get(i));
            }
        }
        baseResBean.setData(data);
        return baseResBean;
    }

    public BaseResBean getByContacts(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<VideoBean> videos = new ArrayList<VideoBean>();
        String str = "select created,fromid,toid from video WHERE (fromid = ? or toid = ?) and callstate =  ? ORDER BY id";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            ps.setInt(2,userBean.getId());
            ps.setInt(3,1);
            set  = ps.executeQuery();
            while (set.next()){
                VideoBean videoBean = new VideoBean();
                String s = set.getString(set.findColumn("created"));
                videoBean.setCreated(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS,DateFormatUtil.MM_DD_HH_MM,s.substring(0,s.length()-2)));
                videoBean.setFromid(set.getInt(set.findColumn("fromid")));
                videoBean.setToid(set.getInt(set.findColumn("toid")));
                videos.add(videoBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        ArrayList<ArrayList<VideoBean>> data = new ArrayList<ArrayList<VideoBean>>();
        HashMap<Integer,ArrayList<VideoBean>> map = new HashMap<Integer, ArrayList<VideoBean>>();
        for(int i=0;i<videos.size();i++){
            if(videos.get(i).getFromid()==userBean.getId()){
                if(map.get(videos.get(i).getToid())==null){
                    map.put(videos.get(i).getToid(),new ArrayList<VideoBean>());
                }
                map.get(videos.get(i).getToid()).add(videos.get(i));
            }else{
                if(map.get(videos.get(i).getFromid())==null){
                    map.put(videos.get(i).getFromid(),new ArrayList<VideoBean>());
                }
                map.get(videos.get(i).getFromid()).add(videos.get(i));
            }
        }

        Iterator<Integer> key = map.keySet().iterator();
        ArrayList<HistoryBean> historyBeen = new ArrayList<HistoryBean>();
        while (key.hasNext()){
            int k = key.next();
            HistoryBean historyBean = new HistoryBean();
            historyBean.setId(k);
            historyBean.setDate(map.get(k).get(map.get(k).size()-1).getCreated());
            historyBean.setNum(map.get(k).size());
            ArrayList<VideoBean> videoBeen = new ArrayList<VideoBean>();
            UserBean userBean1 = new UserBean();
            userBean1.setId(k);
            historyBean.setUserBean((UserBean) userI.getUserInfoById(userBean1).getData());
            historyBeen.add(historyBean);
        }
        baseResBean.setData(historyBeen);
        return baseResBean;
    }

    public BaseResBean commentVideos(CommentBean commentBean) {
        BaseResBean baseResBean = new BaseResBean();

        String str = "insert into comment(rate,tips,remark,created,videoname,fromuser,touser,fromid,toid,videoid) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            ps.setInt(8,commentBean.getFromid());
            ps.setInt(9,commentBean.getToid());
            ps.setInt(10,commentBean.getVideoid());
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

    public BaseResBean getUserCallTimeInfoById(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select sum(timenum) from video WHERE fromid = ? or toid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getId());
            ps.setInt(2,user.getId());
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

    public BaseResBean getUserCallTimeInfoByIdWithTimeLimit(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select sum(timenum) from video WHERE (fromid = ? or toid = ?) and (created > ? and created < ? )";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getId());
            ps.setInt(2,user.getId());
            ps.setString(3,user.getStart());
            ps.setString(4,user.getEnd());
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

    public BaseResBean getCallTimeInfo() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select sum(timenum) from video";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
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

    public BaseResBean isVideoUploaded(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select uploaded from video WHERE file = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int uploaded = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            set  = ps.executeQuery();
            set.next();
            uploaded = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(uploaded==0?false:true);
        return baseResBean;
    }

    public BaseResBean getVideoUploadedNum(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from video WHERE callstate =  ? and (fromid = ? or toid = ? ) and uploaded = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int uploaded = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            ps.setInt(2,userBean.getId());
            ps.setInt(3,userBean.getId());
            ps.setInt(4,0);
            set  = ps.executeQuery();
            set.next();
            uploaded = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(uploaded);
        return baseResBean;
    }

    public BaseResBean setVideoUploaded(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update video set uploaded = ? WHERE file = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int uploaded = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            ps.setString(2,videoBean.getFile());
            ps.execute();
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

    public BaseResBean getMaxVideoId() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select max(id) from video";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int max = -1;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            set = ps.executeQuery();
            set.next();
            max = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(max);
        return baseResBean;
    }

    public BaseResBean getVideoNum() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from video";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int max = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            set = ps.executeQuery();
            set.next();
            max = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(max);
        return baseResBean;
    }

    public BaseResBean getCallNum() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from video WHERE callstate =  ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long max = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            set = ps.executeQuery();
            set.next();
            max = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(max);
        return baseResBean;
    }

    public BaseResBean getUnUploadVideoNum(UserBean u) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from video WHERE uploaded = 0 and (fromid = ? or toid = ? ) and callstate =  ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long max = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,u.getId());
            ps.setInt(2,u.getId());
            ps.setInt(3,1);
            set = ps.executeQuery();
            set.next();
            max = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(max);
        return baseResBean;
    }

    public BaseResBean getOutCallTimeDistribution() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select fromid from video WHERE callstate =  ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> fromid = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            set = ps.executeQuery();
            while (set.next()){
                UserBean uu = new UserBean();
                uu.setId(set.getInt(1));
                fromid.add(uu);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(userI ==null){
            userI = new UserOpe();
        }
        for(int i=0;i<fromid.size();i++){
            fromid.get(i).setUsertype(((UserBean)userI.getUserTypeInfoById(fromid.get(i)).getData()).getUsertype());
        }
        baseResBean.setData(fromid);
        return baseResBean;
    }

    public BaseResBean getInCallTimeDistribution() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select toid from video WHERE callstate =  ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> fromid = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,1);
            set = ps.executeQuery();
            while (set.next()){
                UserBean uu = new UserBean();
                uu.setId(set.getInt(1));
                fromid.add(uu);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(userI ==null){
            userI = new UserOpe();
        }
        for(int i=0;i<fromid.size();i++){
            fromid.get(i).setUsertype(((UserBean)userI.getUserTypeInfoById(fromid.get(i)).getData()).getUsertype());
        }
        baseResBean.setData(fromid);
        return baseResBean;
    }

    public BaseResBean getVideoNameById(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select file from video where id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        String file = "";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,videoBean.getId());
            set = ps.executeQuery();
            while (set.next()){
                file = set.getString(set.findColumn("file"));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(file);
        return baseResBean;
    }

    public BaseResBean updateCallState(VideoBean videoBean) {
        return DBI.execute("update video set callstate = ? where id = ?",videoBean.getCallstate(),videoBean.getId());
    }

    public BaseResBean updateVideoCallTimeNum(VideoBean videoBean) {
        return DBI.execute("update video set timenum = ? where id = ?",videoBean.getTimenum(),videoBean.getId());
    }


}
