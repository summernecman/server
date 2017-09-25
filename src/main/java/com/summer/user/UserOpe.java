package com.summer.user;

import com.summer.base.OnFinishListener;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentI;
import com.summer.comment.CommentOpe;
import com.summer.main.DBUtil;
import com.summer.unit.UnitBean;
import com.summer.unit.UnitI;
import com.summer.unit.UnitOpe;
import com.summer.user.bean.AllUserBean;
import com.summer.user.bean.UserBean;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
import com.summer.video.bean.VideoTimeBean;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-23.
 */
public class UserOpe  implements UserI{

    CommentI commentI ;

    UnitI unitI ;

    VideoI videoI;

    public BaseResBean getUserState(UserBean bean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from user where phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection =null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,bean.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                bean.setId(set.getInt(set.findColumn("id")));
                bean.setPhone(set.getString(set.findColumn("phone")));
                bean.setPwd(set.getString(set.findColumn("pwd")));
                bean.setUsertype(set.getInt(set.findColumn("usertype")));
                bean.setBelong(set.getString(set.findColumn("belong")));
                bean.setName(set.getString(set.findColumn("name")));
                bean.setState(set.getInt(set.findColumn("state")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(bean);
        return baseResBean;
    }

    public BaseResBean setUserState(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user SET state = ? WHERE phone = ? and pwd = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getState());
            ps.setString(2,userBean.getPhone());
            ps.setString(3,userBean.getPwd());
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

    public BaseResBean getUserList() {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setState(set.getInt(set.findColumn("state")));
                userBean.setHeadurl(set.getString(set.findColumn("headurl")));
                userBean.setUuuid(set.getString(set.findColumn("uuuid")));
                userBean.setUnitid(set.getInt(set.findColumn("unitid")));
                users.add(userBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(unitI==null){
            unitI = new UnitOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());
        }
        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getUnTypeUserList(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user WHERE usertype <> ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getUsertype());
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setState(set.getInt(set.findColumn("state")));
                userBean.setHeadurl(set.getString(set.findColumn("headurl")));
                userBean.setUuuid(set.getString(set.findColumn("uuuid")));
                userBean.setUnitid(set.getInt(set.findColumn("unitid")));
                users.add(userBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(unitI==null){
            unitI = new UnitOpe();
        }
        if(commentI  == null){
             commentI = new CommentOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());

            users.get(i).setAvg((Float) commentI.getVideoRateCommentByUserPhone(users.get(i)).getData());

        }
        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getUserListWithOutMe(UserBean userBean) {
        ArrayList<UserBean> users  = (ArrayList<UserBean>) getUserList().getData();
        for(int i=0;users!=null && i<users.size();i++){
            if(users.get(i).getPhone().equals(userBean.getPhone())){
                users.remove(i);
                break;
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(users);
        return  baseResBean;
    }

    public BaseResBean registed(String username) {
        BaseResBean baseResBean = new BaseResBean();
        return null;
    }

    public BaseResBean registOnEM(UserBean userBean, OnFinishListener onFinishListener) {

        return null;
    }

    public BaseResBean regist(UserBean user) {
        return null;
    }

    public BaseResBean login(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from user WHERE  phone = ? and pwd = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        UserBean user = new UserBean();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            ps.setString(2,userBean.getPwd());
            set  = ps.executeQuery();
            while (set.next()){
                user.setId(set.getInt(set.findColumn("id")));
                user.setPhone(set.getString(set.findColumn("phone")));
                user.setPwd(set.getString(set.findColumn("pwd")));
                user.setUsertype(set.getInt(set.findColumn("usertype")));
                user.setBelong(set.getString(set.findColumn("belong")));
                user.setName(set.getString(set.findColumn("name")));
                user.setHeadurl(set.getString(set.findColumn("headurl")));
                user.setUnitid(set.getInt(set.findColumn("unitid")));
                user.setState(set.getInt(set.findColumn("state")));
                user.setUuuid(set.getString(set.findColumn("uuuid")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(user.getPhone()!=null){
            baseResBean.setData(user);
            setLoginInfo(userBean);

            if(unitI == null){
                unitI = new UnitOpe();
            }

            UnitBean unitBean1 = (UnitBean) unitI.getUnitById(new UnitBean(user.getUnitid())).getData();
            if(unitBean1==null){
                unitBean1 = new UnitBean();
                unitBean1.setId(0);
            }
            user.setUnit(unitBean1);

        }else{
            baseResBean.setException(true);
            baseResBean.setErrorMessage("用户名或密码不正确");
        }
        return baseResBean;
    }

    public BaseResBean logout(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        user.setState(UserBean.STATE_OFFLINE);
        setUserState(user);
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean getUserInfoByPhone(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from user where phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                user.setId(set.getInt(set.findColumn("id")));
                user.setPhone(set.getString(set.findColumn("phone")));
                user.setPwd(set.getString(set.findColumn("pwd")));
                user.setUsertype(set.getInt(set.findColumn("usertype")));
                user.setBelong(set.getString(set.findColumn("belong")));
                user.setName(set.getString(set.findColumn("name")));
                user.setHeadurl(set.getString(set.findColumn("headurl")));
                user.setUnitid(set.getInt(set.findColumn("unitid")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(unitI==null){
            unitI = new UnitOpe();
        }
        UnitBean u = new UnitBean(user.getUnitid());
        user.setUnit((UnitBean) unitI.getUnitById(u).getData());
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean getLoginInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select uuuid from user where phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                user.setUuuid(set.getString(set.findColumn("uuuid")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean setLoginInfo(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user SET uuuid = ? WHERE phone = ? and pwd = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getUuuid());
            ps.setString(2,user.getPhone());
            ps.setString(3,user.getPwd());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean setHeadUrl(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user SET headurl = ? WHERE phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getHeadurl());
            ps.setString(2,user.getPhone());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean setUserName(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user SET name = ? WHERE phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPhone());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean getUsersInfoByPhone(ArrayList<UserBean> list) {
        if(commentI==null){
            commentI = new CommentOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        for(int i=0;list!=null && i<list.size();i++){
            UserBean userBean = (UserBean) getUserInfoByPhone(list.get(i)).getData();
            list.set(i,userBean);
        }
        for(int i=0;list!=null&&i<list.size();i++){
            float rate =  (Float) (commentI.getVideoRateCommentByUserPhone(list.get(i)).getData());
            list.get(i).setAvg(rate);

        }
        baseResBean.setData(list);
        return baseResBean;
    }

    public BaseResBean getOtherUsersInfoByPhone(AllUserBean allUserBean) {
        if(commentI==null){
            commentI = new CommentOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> list = allUserBean.getOther();
        for(int i=0;list!=null && i<list.size();i++){
            UserBean userBean = (UserBean) getUserInfoByPhone(list.get(i)).getData();
            if(allUserBean.getMe().getUsertype()==userBean.getUsertype()){
                list.remove(i);
                i--;
            }
            //list.set(i,userBean);
        }
        for(int i=0;list!=null&&i<list.size();i++){
            float rate =  (Float) (commentI.getVideoRateCommentByUserPhone(list.get(i)).getData());
            list.get(i).setAvg(rate);

        }
        baseResBean.setData(list);
        return baseResBean;
    }




    public BaseResBean getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> list) {
        BaseResBean baseResBean = new BaseResBean();
        for(int i=0;list!=null && i<list.size();i++){
            for(int j=0;j<list.get(i).size();j++){
                UserBean userBean = (UserBean) getUserInfoByPhone(list.get(i).get(j)).getData();
                list.get(i).set(j,userBean);
            }
        }
        baseResBean.setData(list);
        return baseResBean;
    }

    public BaseResBean getUserInfoById(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from user where id = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getId());
            set  = ps.executeQuery();
            while (set.next()){
                user.setPhone(set.getString(set.findColumn("phone")));
                user.setUuuid(set.getString(set.findColumn("uuuid")));
                user.setPwd(set.getString(set.findColumn("pwd")));
                user.setName(set.getString(set.findColumn("name")));
                user.setBelong(set.getString(set.findColumn("belong")));
                user.setState(set.getInt(set.findColumn("state")));
                user.setUnitid(set.getInt(set.findColumn("unitid")));
                user.setHeadurl(set.getString(set.findColumn("headurl")));
                break;
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(unitI==null){
            unitI = new UnitOpe();
        }
        UnitBean u = new UnitBean(user.getUnitid());
        user.setUnit((UnitBean) unitI.getUnitById(u).getData());

        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean updateUnitInfo(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user set unitid = ? where id = ?;";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getUnitid());
            ps.setInt(2,userBean.getId());
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

    public BaseResBean updateUUUid(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user set uuuid = ? where id = ?;";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getUuuid());
            ps.setInt(2,userBean.getId());
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

    public BaseResBean getUserListWithType(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user WHERE usertype = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getUsertype());
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setState(set.getInt(set.findColumn("state")));
                userBean.setHeadurl(set.getString(set.findColumn("headurl")));
                userBean.setUuuid(set.getString(set.findColumn("uuuid")));
                userBean.setUnitid(set.getInt(set.findColumn("unitid")));
                users.add(userBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        if(unitI==null){
            unitI = new UnitOpe();
        }
        if(videoI ==null){
            videoI = new VideoOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());
            VideoTimeBean videoTimeBean = (VideoTimeBean) videoI.getUserCallInfo(users.get(i)).getData();
            users.get(i).setCallinfo(videoTimeBean);
        }
        baseResBean.setData(users);
        return baseResBean;
    }


    public BaseResBean addUser(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into user(phone,pwd,usertype,name,belong) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            ps.setString(2,user.getPwd());
            ps.setInt(3,user.getUsertype());
            ps.setString(4,user.getName());
            ps.setString(5,user.getBelong());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(user);
        return baseResBean;
    }

    public BaseResBean isUserExist(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select COUNT(id)from user WHERE  phone = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
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
        baseResBean.setData(num==0?false:true);
        return baseResBean;
    }
}
