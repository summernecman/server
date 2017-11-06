package com.summer.crash;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBUtil;
import com.summer.util.GsonUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
