package com.summer.agree;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SWSD on 17-09-12.
 */
public class AgreeOpe implements AgreeI {

    public BaseResBean addAgree(AgreeBean agree) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "INSERT  into agree(commentid,agreeid) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,agree.getCommentid());
            ps.setInt(2,agree.getAgreeid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(agree);
        return baseResBean;
    }

    public BaseResBean cancleAgree(AgreeBean agree) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "DELETE FROM agree WHERE commentid = ? and agreeid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,agree.getCommentid());
            ps.setInt(2,agree.getAgreeid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(agree);
        return baseResBean;
    }

    public BaseResBean getAgreeNum(AgreeBean agree) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) FROM agree WHERE commentid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,agree.getCommentid());
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

    public BaseResBean isAgreeNum(AgreeBean agree) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) FROM agree WHERE agreeid = ? and commentid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,agree.getAgreeid());
            ps.setInt(2,agree.getCommentid());
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
        baseResBean.setData(num>0?true:false);
        return baseResBean;
    }

}
