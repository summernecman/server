package com.summer.unit;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.bean.UserBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SWSD on 17-09-11.
 */
public class UnitOpe implements UnitI {

    public BaseResBean addUnit(UnitBean unitBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "INSERT  into unit(unittype,unitname) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,unitBean.getUnittype());
            ps.setString(2,unitBean.getUnitname());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(unitBean);
        return baseResBean;
    }

    public BaseResBean getUnitById(UnitBean unitBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "SELECT * from unit WHERE id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        UnitBean u = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,unitBean.getId());
            set = ps.executeQuery();
            while (set.next()){
                u = new UnitBean();
                u.setId(set.getInt(set.findColumn("id")));
                u.setUnitname(set.getString(set.findColumn("unitname")));
                u.setUnittype(set.getInt(set.findColumn("unittype")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(u);
        return baseResBean;
    }

    public BaseResBean unpdateUnit(UnitBean unitBean) {
        return null;
    }

    public BaseResBean getUnitByName(UnitBean unitBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "SELECT * from unit WHERE unitname = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        UnitBean u = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,unitBean.getUnitname());
            set = ps.executeQuery();
            while (set.next()){
                u = new UnitBean();
                u.setId(set.getInt(set.findColumn("id")));
                u.setUnitname(set.getString(set.findColumn("unitname")));
                u.setUnittype(set.getInt(set.findColumn("unittype")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(u);
        return baseResBean;
    }
}
