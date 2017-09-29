package com.summer.contact;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.UserBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by SWSD on 17-09-26.
 */
public class ContactOpe  implements ContactI{

    UserI userI;


    public BaseResBean getContactsByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select toid from contact WHERE  fromid =  ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> userBeen = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean1 = new UserBean();
                userBean1.setId(set.getInt(1));
                userBeen.add(userBean1);
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
        for(int i=0;i<userBeen.size();i++){
            UserBean u = (UserBean) userI.getUserShortInfoById(userBeen.get(i)).getData();
            userBeen.set(i,u);

        }
        baseResBean.setData(userBeen);
        return baseResBean;
    }

    public BaseResBean getContactsByUserId2(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select toid from contact WHERE  fromid =  ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> userBeen = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean1 = new UserBean();
                userBean1.setId(set.getInt(1));
                userBeen.add(userBean1);
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
        for(int i=0;i<userBeen.size();i++){
            UserBean u = (UserBean) userI.getUserInfoById(userBeen.get(i)).getData();
            userBeen.set(i,u);

        }
        baseResBean.setData(userBeen);
        return baseResBean;
    }

    public BaseResBean getContactsByUserIdWithOutAgree(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from contact WHERE  fromid =  ? or toid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> userBeen = new ArrayList<UserBean>();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            ps.setInt(2,userBean.getId());
            set  = ps.executeQuery();
            while (set.next()){
                int fromid = set.getInt(set.findColumn("fromid"));
                int toid = set.getInt(set.findColumn("toid"));
                if(userBean.getId()==fromid){
                    map.put(toid,toid);
                }else{
                    map.put(fromid,fromid);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        Iterator<Integer> t = map.keySet().iterator();
        while(t.hasNext()){
            UserBean u = new UserBean();
            u.setId(t.next());
            userBeen.add(u);
        }

        if(userI == null){
            userI = new UserOpe();
        }
        for(int i=0;i<userBeen.size();i++){
            UserBean u = (UserBean) userI.getUserInfoById(userBeen.get(i)).getData();
            userBeen.set(i,u);

        }
        baseResBean.setData(userBeen);
        return baseResBean;
    }

    public BaseResBean addContactsByUserid(ContactBean bean) {
        if((Boolean) isContactsExits(bean).getData()){
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setErrorMessage("联系人已经添加");
            baseResBean.setException(false);
            baseResBean.setData(bean);
            return baseResBean;
        }
        BaseResBean baseResBean = new BaseResBean();
        String str = "INSERT INTO contact(fromid,toid) VALUES (?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> userBeen = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,bean.getFromid());
            ps.setInt(2,bean.getToid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(userBeen);
        return baseResBean;
    }

    public BaseResBean isContactsExits(ContactBean bean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "SELECT count(id) from contact WHERE fromid = ? and toid = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,bean.getFromid());
            ps.setInt(2,bean.getToid());
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
        baseResBean.setData(num>0?true:false);
        return baseResBean;
    }
}
