package com.summer.comment;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.VideoBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-29.
 */
public class CommentOpe implements CommentI {


    public BaseResBean getCommentByUserName(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<CommentBean> comments = new ArrayList<CommentBean>();
        String str = "select * from comment WHERE  touser = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getName());
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
}
