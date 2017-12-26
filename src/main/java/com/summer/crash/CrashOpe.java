package com.summer.crash;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBI;
import com.summer.unit.DBUtil;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean1;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-08.
 */
public class CrashOpe implements CrashI {



    public BaseResBean sendCrash(CrashBean crashBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "INSERT  into crash(error,createdtime,user) VALUES (?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,crashBean.getError());
            ps.setString(2,crashBean.getCreatedtime());
            ps.setString(3, GsonUtil.getInstance().toJson(crashBean.getUserBean()));
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(crashBean);
        return baseResBean;
    }

    public BaseResBean getCrash(GetCrashBean getCrashBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from crash order by id limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<CrashBean> crashBeanArrayList = new ArrayList<CrashBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,getCrashBean.getStart()*getCrashBean.getNum());
            ps.setInt(2,getCrashBean.getNum());
            set = ps.executeQuery();
            while (set.next()){
                CrashBean crashBean = new CrashBean();
                crashBean.setId(set.getInt(set.findColumn("id")));
                crashBean.setError(set.getString(set.findColumn("error")));
                crashBean.setCreatedtime(set.getString(set.findColumn("createdtime")));
                crashBean.setUserBean(GsonUtil.getInstance().fromJson(set.getString(set.findColumn("user")),UserBean.class));
                crashBean.setPlatform(set.getString(set.findColumn("platform"))+DBUtil.getDBStr());
                crashBeanArrayList.add(crashBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(crashBeanArrayList);
        return baseResBean;
    }
}
