package com.summer.collection;

import com.summer.base.bean.BaseResBean;
import com.summer.collection.bean.CollectionBean;
import com.summer.unit.DBUtil;
import com.summer.user.bean.UserBean;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
import com.summer.video.bean.VideoBean;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-04.
 */
public class CollectionOpe implements CollectionI {


    VideoI videoI = new VideoOpe();


    public BaseResBean getCollectionsByUserIdWithLimit(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CollectionBean> collectionBeen = new ArrayList<CollectionBean>();
        String str = "select * from collection WHERE  userid = ? limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            ps.setInt(2,userBean.getPagestart()*userBean.getPagesize());
            ps.setInt(3,userBean.getPagesize());
            set  = ps.executeQuery();
            while (set.next()){
                CollectionBean collectionBean = new CollectionBean();
                collectionBean.setId(set.getInt(set.findColumn("id")));
                collectionBean.setVideoid(set.getInt(set.findColumn("videoid")));
                collectionBean.setUserid(set.getInt(set.findColumn("userid")));
                collectionBeen.add(collectionBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(collectionBeen);
        return baseResBean;
    }

    public BaseResBean getCollectionsByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CollectionBean> collectionBeen = new ArrayList<CollectionBean>();
        String str = "select * from collection WHERE  userid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                CollectionBean collectionBean = new CollectionBean();
                collectionBean.setId(set.getInt(set.findColumn("id")));
                collectionBean.setVideoid(set.getInt(set.findColumn("videoid")));
                collectionBean.setUserid(set.getInt(set.findColumn("userid")));
                collectionBeen.add(collectionBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(collectionBeen);
        return baseResBean;
    }




    public BaseResBean getCollectionNumByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select COUNT(id) from collection WHERE  userid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num= 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
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

    public BaseResBean getCollectionVideosByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CollectionBean> collectionBeen = (ArrayList<CollectionBean>) getCollectionsByUserId(userBean).getData();
        VideoBean videoBean = new VideoBean();
        ArrayList<VideoBean> vs = new ArrayList<VideoBean>();
        for(int i=0;collectionBeen!=null && i<collectionBeen.size();i++){
            videoBean.setId(collectionBeen.get(i).getVideoid());
            ArrayList<VideoBean> videoBeen = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(videoBeen!=null && videoBeen.size()>0){
                vs.add(videoBeen.get(0));
            }
        }
        baseResBean.setData(vs);
        return baseResBean;
    }

    public BaseResBean getCollectionVideosByUserIdWithLimit(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CollectionBean> collectionBeen = (ArrayList<CollectionBean>) getCollectionsByUserIdWithLimit(userBean).getData();
        VideoBean videoBean = new VideoBean();
        ArrayList<VideoBean> vs = new ArrayList<VideoBean>();
        for(int i=0;collectionBeen!=null && i<collectionBeen.size();i++){
            videoBean.setId(collectionBeen.get(i).getVideoid());
            ArrayList<VideoBean> videoBeen = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(videoBeen!=null && videoBeen.size()>0){
                vs.add(videoBeen.get(0));
            }
        }
        baseResBean.setData(vs);
        return baseResBean;
    }

    public BaseResBean isCollectedByVideoIdAndUserId(CollectionBean collectionBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select id from collection WHERE videoid = ? and userid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = -1;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,collectionBean.getVideoid());
            ps.setInt(2,collectionBean.getUserid());
            set = ps.executeQuery();
            while (set.next()){
                num = set.getInt(1);
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        collectionBean.setId(num);
        baseResBean.setData(num==-1?null:collectionBean);
        return baseResBean;
    }


//    public BaseResBean collect(VideoBean videoBean) {
//        BaseResBean baseResBean = new BaseResBean();
//        String str = "insert into collection(videoid,userid) values(?,?)";
//        PreparedStatement ps = null;
//        ResultSet set = null;
//        Connection connection = null;
//        try {
//            connection = DBUtil.getConnection();
//            ps = connection.prepareStatement(str);
//            ps.setInt(1,videoBean.getId());
//            ps.setInt(2,videoBean.getFromid());
//             ps.execute();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBUtil.close(connection,ps,set);
//        }
//        baseResBean.setData(videoBean);
//        return baseResBean;
//    }


    public BaseResBean collect(CollectionBean collectionBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "{call get_share_id(?,?,?)}";
        CallableStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareCall(str);
            ps.setInt(1,collectionBean.getVideoid());
            ps.setInt(2,collectionBean.getUserid());
            ps.registerOutParameter(3,Types.INTEGER);
            ps.execute();
            System.out.println(ps.getInt(3));
            collectionBean.setId(ps.getInt(3));
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(collectionBean);
        return baseResBean;
    }

    public BaseResBean disCollect(CollectionBean collectionBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "DELETE from collection WHERE  id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,collectionBean.getId());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(collectionBean);
        return baseResBean;
    }
}
