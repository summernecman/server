package com.summer.userarea;

import com.summer.area.AreaBean;
import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.bean.UserBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-29.
 */
public class UserAreaOpe implements UserAreaI {



    public BaseResBean getUserAreaById(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select area.name,area.id from area,userarea where userarea.userid = ? and userarea.areaid =  area.id";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set = ps.executeQuery();
            while(set.next()){
                areas.add(new AreaBean(set.getInt(set.findColumn("id")),set.getString(set.findColumn("name"))));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(areas);
        return baseResBean;
    }

    public BaseResBean addUserArea(UserAreaBean userAreaBean) {
        if((Boolean) (isUserHaveArea(userAreaBean).getData())){
            return new BaseResBean();
        }
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into userarea(userid,areaid) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userAreaBean.getUserid());
            ps.setInt(2,userAreaBean.getAreaid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(areas);
        return baseResBean;
    }

    public BaseResBean isUserHaveArea(UserAreaBean userAreaBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "SELECT count(id) from userarea WHERE  userid = ? and areaid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userAreaBean.getUserid());
            ps.setInt(2,userAreaBean.getAreaid());
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
        baseResBean.setData(num==0? false:true);
        return baseResBean;
    }

    public BaseResBean delete(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "delete FROM userarea WHERE userid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(userBean);
        return baseResBean;
    }


}
