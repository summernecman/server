package com.summer.unit;

import com.summer.base.bean.BaseResBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by SWSD on 17-11-01.
 */
public class DBI {


    public static  BaseResBean executeQuery(Class c,String sql,Object... params) {
        BaseResBean baseResBean = new BaseResBean();
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        List list = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0;params!=null && i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
            set  = ps.executeQuery();
            list  = DBUtil.populate(set,c);
        } catch (NamingException e) {
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(baseResBean.isException()){
            return baseResBean;
        }
        baseResBean.setData(list);
        return baseResBean;
    }


    public static  BaseResBean execute(String sql,Object... params) {
        BaseResBean baseResBean = new BaseResBean();
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0;params!=null && i<params.length;i++){
                ps.setObject(i+1,params[i]);
            }
            ps.execute();
        } catch (NamingException e) {
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(e.getMessage()==null?e.toString():e.getMessage());
        } finally {
            DBUtil.close(connection,ps,set);
        }
        return baseResBean;
    }
}
