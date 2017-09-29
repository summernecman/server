package com.summer.area;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.UserI;
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
public class AreaOpe implements AreaI {



    public BaseResBean isAreaExist(AreaBean areaBean){
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from area WHERE name = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,areaBean.getName());
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
        baseResBean.setData(num==0?false:true);
        return baseResBean;
    }

    public BaseResBean addArea(AreaBean areaBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into area(name) VALUES ( ?  )";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,areaBean.getName());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(areaBean);
        return baseResBean;
    }

    public BaseResBean getArea() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from area";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<AreaBean> areas = new ArrayList<AreaBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
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




}
