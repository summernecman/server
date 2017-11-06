package com.summer.comment;

import com.summer.agree.AgreeBean;
import com.summer.agree.AgreeI;
import com.summer.agree.AgreeOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.bean.RateLevelBean;
import com.summer.comment.bean.TipsBean;
import com.summer.unit.DBUtil;
import com.summer.tip.TipI;
import com.summer.tip.TipOpe;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.VideoI;
import com.summer.video.bean.LimitBean;
import com.summer.video.bean.VideoBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SWSD on 17-08-29.
 */
public class CommentOpe implements CommentI {

    UserI userI ;

    AgreeI agreeI;

    VideoI videoI;

    TipI tipI ;


    public BaseResBean getCommentsWithLimit(LimitBean limitBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment ORDER  BY  id DESC limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,limitBean.getPagestart()*limitBean.getPagesize());
            ps.setInt(2,limitBean.getPagesize());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                //commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                //commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setVideoid(set.getInt(set.findColumn("videoid")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);


            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);




        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getCommentsNum() {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select COUNT(id) FROM comment";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
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

    public BaseResBean getCommentByUserPhone(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  touser = ?  ORDER  BY  id DESC ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);


            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);




        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getCommentByUserIdWithLimit(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  toid = ?   ORDER  BY  id DESC limit ?,?";
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
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);

            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);
        }

        if(agreeI == null){
            agreeI = new AgreeOpe();
        }
        for(int i=0;i<comments.size();i++){
            comments.get(i).setAgreeNum((Integer) agreeI.getAgreeNum(new AgreeBean(comments.get(i).getId(),0)).getData());
        }


        baseResBean.setData(comments);
        return baseResBean;
    }


    public BaseResBean getOtherCommentByUserIdWithLimit(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  fromid = ?   ORDER  BY  id DESC limit ?,?";
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
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);

            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);
        }
        baseResBean.setData(comments);
        return baseResBean;
    }



    public BaseResBean getCommentByUserId(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  toid = ?  ORDER  BY  id DESC";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);


            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);




        }
        baseResBean.setData(comments);
        return baseResBean;
    }



    public BaseResBean getShortCommentByUserIdWithLimit(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select created,fromid,fromuser,id,remark,rate from comment WHERE  toid = ? ORDER BY id DESC limit ?,? ";
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
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(userI == null){
            userI = new UserOpe();
        }
        for(int i=0;i<comments.size();i++){
            UserBean userBean1 = new UserBean();
            userBean1.setId(comments.get(i).getFromid());
            comments.get(i).setHeadUrl((String) userI.getHeadUrlById(userBean1).getData());
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getShortCommentByUserId(UserBean userBean) {
        if(userI ==null){
            userI= new UserOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select created,fromid,fromuser,id,remark,rate from comment WHERE  toid = ?  ORDER  BY  id DESC";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(userI == null){
            userI = new UserOpe();
        }
        for(int i=0;i<comments.size();i++){
            UserBean userBean1 = new UserBean();
            userBean1.setId(comments.get(i).getFromid());
            comments.get(i).setHeadUrl((String) userI.getHeadUrlById(userBean1).getData());
        }
        baseResBean.setData(comments);
        return baseResBean;
    }




    public BaseResBean getCommentByUserIdWithMyOption(CommentBean commentBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> list = (ArrayList<CommentBean>) getCommentByUserId(commentBean.getToUser()).getData();
        if(agreeI == null){
            agreeI = new AgreeOpe();
        }
        for(int i=0;i<list.size();i++){
            AgreeBean agreeBean = new AgreeBean(list.get(i).getId(),commentBean.getFromUser().getId());
            list.get(i).setAgree((Boolean) (agreeI.isAgreeNum(agreeBean).getData()));
            list.get(i).setAgreeNum((Integer) agreeI.getAgreeNum(new AgreeBean(list.get(i).getId(),0)).getData());

        }
        baseResBean.setData(list);
        return baseResBean;
    }

    public BaseResBean getCommentByUserIdWithMyOptionWithLimit(CommentBean commentBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> list = (ArrayList<CommentBean>) getCommentByUserIdWithLimit(commentBean.getToUser()).getData();
        if(agreeI == null){
            agreeI = new AgreeOpe();
        }
        for(int i=0;i<list.size();i++){
            AgreeBean agreeBean = new AgreeBean(list.get(i).getId(),commentBean.getFromUser().getId());
            list.get(i).setAgree((Boolean) (agreeI.isAgreeNum(agreeBean).getData()));
            //list.get(i).setAgreeNum((Integer) agreeI.getAgreeNum(new AgreeBean(list.get(i).getId(),0)).getData());

        }
        baseResBean.setData(list);
        return baseResBean;
    }

    public BaseResBean getCommentNumByUserName(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from comment WHERE  touser = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
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

    public BaseResBean getCommentNumByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from comment WHERE toid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
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

    public BaseResBean getTips(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select tips from comment WHERE  touser = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setTips(set.getString(set.findColumn("tips")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getTipsByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select tips from comment WHERE  toid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setTips(set.getString(set.findColumn("tips")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getVideoCommentByVideoName(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  videoname = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        for(int i=0;i<comments.size();i++){
            UserBean userBean2 = new UserBean();
            userBean2.setId(comments.get(i).getFromid());
            UserBean userBean1 = (UserBean) userI.getUserInfoById(userBean2).getData();
            comments.get(i).setFromUser(userBean1);


            UserBean userBean3 = new UserBean();
            userBean3.setId(comments.get(i).getToid());
            UserBean userBean4 = (UserBean) userI.getUserInfoById(userBean3).getData();
            comments.get(i).setToUser(userBean1);

        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getVideoCommentByVideoNameAndFrom(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  videoname = ? and touser = ?  ORDER  BY  id DESC ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,videoBean.getFile());
            ps.setString(2,videoBean.getToUser().getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getVideoCommentByVideoIdAndFrom(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  videoid = ? and touser = ?  ORDER  BY  id DESC";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,videoBean.getId());
            ps.setString(2,videoBean.getToUser().getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        if(tipI ==null){
            tipI = new TipOpe();
        }
        HashMap<Integer,String> map = (HashMap<Integer, String>) tipI.getMapTips().getData();
        for(int i=0;comments!=null&&i<comments.size();i++){
            TipsBean tipsBeen  = GsonUtil.getInstance().fromJson(comments.get(i).getTips(),TipsBean.class);
            for(int j=0;tipsBeen!=null&&tipsBeen.getTipBeen()!=null && j<tipsBeen.getTipBeen().size();j++){
                if(map.get(tipsBeen.getTipBeen().get(j).getPosition())!=null){
                    tipsBeen.getTipBeen().get(j).setTip(map.get(tipsBeen.getTipBeen().get(j).getPosition()));
                }
            }
            comments.get(i).setTips(GsonUtil.getInstance().toJson(tipsBeen));
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getVideoCommentsByVideoId(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  videoid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,videoBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                CommentBean commentBean = new CommentBean();
                commentBean.setCreated(set.getString(set.findColumn("created")));
                commentBean.setFromuser(set.getString(set.findColumn("fromuser")));
                commentBean.setId(set.getInt(set.findColumn("id")));
                commentBean.setRate(set.getFloat(set.findColumn("rate")));
                commentBean.setRemark(set.getString(set.findColumn("remark")));
                commentBean.setTips(set.getString(set.findColumn("tips")));
                commentBean.setTouser(set.getString(set.findColumn("touser")));
                commentBean.setVideoname(set.getString(set.findColumn("videoname")));
                commentBean.setFromid(set.getInt(set.findColumn("fromid")));
                commentBean.setToid(set.getInt(set.findColumn("toid")));
                comments.add(commentBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(comments);
        return baseResBean;
    }

    public BaseResBean getVideoRateCommentByUserPhone(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select avg(rate) from comment WHERE  touser = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        float num = 0f;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            set  = ps.executeQuery();
            set.next();
            num = set.getFloat(1);
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

    public BaseResBean getVideoRateCommentByUseId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select avg(rate) from comment WHERE  toid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        float num = 0f;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            set.next();
            num = set.getFloat(1);
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

    public BaseResBean getVideoRateCommentByUseIdWithTimeLimit(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select avg(rate) from comment WHERE  toid = ? and (created > ? and created < ?) ORDER BY avg(rate)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        float num = 0f;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            ps.setString(2,userBean.getStart());
            ps.setString(3,userBean.getEnd());
            set  = ps.executeQuery();
            set.next();
            num = set.getFloat(1);
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

    public BaseResBean getVideoRateCommentByVideoid(VideoBean videoBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select rate from comment WHERE  videoid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        float num = 0f;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,videoBean.getId());
            set  = ps.executeQuery();
            set.next();
            num = set.getFloat(1);
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

    public BaseResBean getVideoCommentRateLevelByuserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select rate from comment WHERE  toid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<RateLevelBean> rateLevelBeen = new ArrayList<RateLevelBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                RateLevelBean rateLevelBean = new RateLevelBean();
                rateLevelBean.setRatef(set.getFloat(1));
                rateLevelBeen.add(rateLevelBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(rateLevelBeen);
        return baseResBean;
    }

    public BaseResBean getToVideoCommentNumByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from comment WHERE  toid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
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
}
