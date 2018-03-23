package com.summer.user;

import com.summer.area.AreaBean;
import com.summer.base.OnFinishListener;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentI;
import com.summer.comment.CommentOpe;
import com.summer.contact.ContactI;
import com.summer.contact.ContactOpe;
import com.summer.unit.*;
import com.summer.user.bean.AllUserBean;
import com.summer.user.bean.UserBaseResBean;
import com.summer.user.bean.UserBean;
import com.summer.userarea.UserAreaI;
import com.summer.userarea.UserAreaOpe;
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

    ContactI contactI;

    UserAreaI userAreaI = new UserAreaOpe();

    public BaseResBean getUserNum() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select COUNT(id) from user";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection =null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
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
        baseResBean.setData(num);
        return baseResBean;
    }

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

    public BaseResBean getShortUserList() {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select id,phone,name,usertype from user";
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
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setName(set.getString(set.findColumn("name")));
                users.add(userBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
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

    public BaseResBean getUnTypeUserShortList(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select id,name,phone from user WHERE usertype <> ? ";
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
                userBean.setName(set.getString(set.findColumn("name")));
                users.add(userBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getUnTypeUserShortListWithOutMe(UserBean userBean) {
        ArrayList<UserBean> users = (ArrayList<UserBean>) getUnTypeUserShortList(userBean).getData();
        for(int i=0;users!=null && i<users.size();i++){
            if(users.get(i).getPhone().equals(userBean.getPhone())){
                users.remove(i);
                break;
            }
        }
        BaseResBean baseResBean = new BaseResBean();
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

    public BaseResBean resetPwd(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user set pwd = ? WHERE phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPwd());
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
                user.setUuuid(set.getString(set.findColumn("uuuid")));
                user.setPwd(set.getString(set.findColumn("pwd")));
                user.setName(set.getString(set.findColumn("name")));
                user.setBelong(set.getString(set.findColumn("belong")));
                user.setState(set.getInt(set.findColumn("state")));
                user.setUnitid(set.getInt(set.findColumn("unitid")));
                user.setHeadurl(set.getString(set.findColumn("headurl")));
                user.setRemark(set.getString(set.findColumn("remark")));
                user.setUsertype(set.getInt(set.findColumn("usertype")));
                user.setRate(set.getFloat(set.findColumn("rate")));
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

    public BaseResBean getUserShortInfoByPhone(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select id,phone,usertype,name from user where phone = ?";
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
                user.setUsertype(set.getInt(set.findColumn("usertype")));
                user.setName(set.getString(set.findColumn("name")));
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

    public BaseResBean getUserTypeInfoByPhone(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select  usertype  from user where phone = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getPhone());
            set  = ps.executeQuery();
            while (set.next()){
                user.setUsertype(set.getInt(set.findColumn("usertype")));
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

    public BaseResBean getUserTypeInfoById(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select  usertype  from user where id = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getId());
            set  = ps.executeQuery();
            while (set.next()){
                user.setUsertype(set.getInt(set.findColumn("usertype")));
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

    public BaseResBean getOtherUsersShortInfoByPhone(AllUserBean allUserBean) {
        if(commentI==null){
            commentI = new CommentOpe();
        }
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<UserBean> list = allUserBean.getOther();
        for(int i=0;list!=null && i<list.size();i++){
            UserBean userBean = (UserBean) getUserTypeInfoByPhone(list.get(i)).getData();
            if(allUserBean.getMe().getPhone()==userBean.getPhone()){
                list.remove(i);
                i--;
            }
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

        UserBean userBean = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getId());
            set  = ps.executeQuery();
            while (set.next()){
                userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setUuuid(set.getString(set.findColumn("uuuid")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setState(set.getInt(set.findColumn("state")));
                userBean.setUnitid(set.getInt(set.findColumn("unitid")));
                userBean.setHeadurl(set.getString(set.findColumn("headurl")));
                userBean.setRemark(set.getString(set.findColumn("remark")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setRate(set.getFloat(set.findColumn("rate")));
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
        if(userAreaI==null){
            userAreaI = new UserAreaOpe();
        }
        if(userBean!=null){
            UnitBean u = new UnitBean(userBean.getUnitid());
            userBean.setUnit((UnitBean) unitI.getUnitById(u).getData());
            userBean.setArea((ArrayList<AreaBean>) userAreaI.getUserAreaById(userBean).getData());
        }
        baseResBean.setData(userBean);
        return baseResBean;
    }

    public BaseResBean getUserShortInfoById(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select phone,name from user where id = ?";
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
                user.setName(set.getString(set.findColumn("name")));
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
        UserBaseResBean baseResBean = new UserBaseResBean();
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
        UserBaseResBean baseResBean = new UserBaseResBean();
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
                userBean.setRemark(set.getString(set.findColumn("remark")));
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

        if(commentI==null){
            commentI = new CommentOpe();

        }
        if(contactI==null){
            contactI=new ContactOpe();
        }

        if(userAreaI==null){
            userAreaI = new UserAreaOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());
            VideoTimeBean videoTimeBean = (VideoTimeBean) videoI.getUserCallInfo(users.get(i)).getData();
            users.get(i).setCallinfo(videoTimeBean);
            users.get(i).setAvg((Float) commentI.getVideoRateCommentByUseId(users.get(i)).getData());
            users.get(i).setContacts((ArrayList<UserBean>) contactI.getContactsByUserIdWithOutAgree(users.get(i)).getData());
            users.get(i).setArea((ArrayList<AreaBean>) userAreaI.getUserAreaById(users.get(i)).getData());
        }

        baseResBean.setData(users);
        return baseResBean;
    }


    public BaseResBean getServerAndEngneerInfo() {
        UserBaseResBean baseResBean = new UserBaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user WHERE usertype = ? or usertype = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,UserBean.USER_TYPE_ENGINEER);
            ps.setInt(2,UserBean.USER_TYPE_SERVER);
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setRemark(set.getString(set.findColumn("remark")));
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

        if(commentI==null){
            commentI = new CommentOpe();

        }
        if(contactI==null){
            contactI=new ContactOpe();
        }

        if(userAreaI==null){
            userAreaI = new UserAreaOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());
            VideoTimeBean videoTimeBean = (VideoTimeBean) videoI.getUserCallInfo(users.get(i)).getData();
            users.get(i).setCallinfo(videoTimeBean);
            users.get(i).setAvg((Float) commentI.getVideoRateCommentByUseId(users.get(i)).getData());
            users.get(i).setContacts((ArrayList<UserBean>) contactI.getContactsByUserIdWithOutAgree(users.get(i)).getData());
            users.get(i).setArea((ArrayList<AreaBean>) userAreaI.getUserAreaById(users.get(i)).getData());
        }

        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getServerAndEngneershortInfo() {
        UserBaseResBean baseResBean = new UserBaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user WHERE usertype = ? or usertype = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,UserBean.USER_TYPE_ENGINEER);
            ps.setInt(2,UserBean.USER_TYPE_SERVER);
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setBelong(set.getString(set.findColumn("belong")));
                userBean.setName(set.getString(set.findColumn("name")));
                userBean.setRemark(set.getString(set.findColumn("remark")));
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

        baseResBean.setData(users);
        return baseResBean;
    }



    public BaseResBean getUserListWithTypeAndLimit(UserBean user) {
        UserBaseResBean baseResBean = new UserBaseResBean();
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        String str = "select * from user WHERE usertype = ? ORDER BY id limit ?,?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getUsertype());
            ps.setInt(2,user.getPagestart()*user.getPagesize());
            ps.setInt(3,user.getPagesize());
            set  = ps.executeQuery();
            while (set.next()){
                UserBean userBean = new UserBean();
                userBean.setId(set.getInt(set.findColumn("id")));
                userBean.setPhone(set.getString(set.findColumn("phone")));
                userBean.setPwd(set.getString(set.findColumn("pwd")));
                userBean.setUsertype(set.getInt(set.findColumn("usertype")));
                userBean.setRemark(set.getString(set.findColumn("remark")));
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

        if(commentI==null){
            commentI = new CommentOpe();

        }
        if(contactI==null){
            contactI=new ContactOpe();
        }

        if(userAreaI==null){
            userAreaI = new UserAreaOpe();
        }
        for(int i=0;i<users.size();i++){
            UnitBean u = new UnitBean(users.get(i).getUnitid());
            users.get(i).setUnit((UnitBean) unitI.getUnitById(u).getData());
            VideoTimeBean videoTimeBean = (VideoTimeBean) videoI.getUserCallInfo(users.get(i)).getData();
            users.get(i).setCallinfo(videoTimeBean);
            users.get(i).setAvg((Float) commentI.getVideoRateCommentByUseId(users.get(i)).getData());
            users.get(i).setContacts((ArrayList<UserBean>) contactI.getContactsByUserIdWithOutAgree(users.get(i)).getData());
            users.get(i).setArea((ArrayList<AreaBean>) userAreaI.getUserAreaById(users.get(i)).getData());
        }

        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getUserNumWithType(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from user WHERE usertype = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,user.getUsertype());
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


    public BaseResBean getServerAndEngneerNum() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(id) from user WHERE usertype = ? or usertype  = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,UserBean.USER_TYPE_SERVER);
            ps.setInt(2,UserBean.USER_TYPE_ENGINEER);
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


    public BaseResBean addUser(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into user(phone,pwd,usertype,name) VALUES (?,?,?,?)";
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
            if(user.getName()==null || "".equals(user.getName())){
                ps.setString(4,user.getPhone());
            }else{
                ps.setString(4,user.getName());
            }
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

    public BaseResBean addUser2(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into user(phone,pwd,usertype,name,remark) VALUES (?,?,?,?,?)";
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
            if(user.getName()==null || "".equals(user.getName())){
                ps.setString(4,user.getPhone());
            }else{
                ps.setString(4,user.getName());
            }
            ps.setString(5,user.getRemark());
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


    public BaseResBean updateRemark(UserBean user) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "UPDATE user set remark = ? WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,user.getRemark());
            ps.setInt(2,user.getId());
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

    public BaseResBean getUserNums(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select count(*) from user where usertype = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        long num = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getUsertype());
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

    public BaseResBean getHeadUrlById(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select headurl from user where id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        String url = "";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,userBean.getId());
            set = ps.executeQuery();
            set.next();
            url = set.getString(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(url);
        return baseResBean;
    }

    public BaseResBean getUserIdByPhone(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select id from user where phone = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        int id = -1;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,userBean.getPhone());
            set = ps.executeQuery();
            set.next();
            id = set.getInt(1);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(id);
        return baseResBean;
    }

    public BaseResBean updateUserChatTimeById(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user set chattime = ? WHERE id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setLong(1,userBean.getChattime());
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

    public BaseResBean updateRateByUserId(UserBean userBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "update user set rate = ? WHERE id = ? ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setFloat(1,userBean.getRate());
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

    public BaseResBean getRates() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select id,name,phone,rate from user ORDER BY  rate DESC ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            set = ps.executeQuery();
            while (set.next()){
                UserBean u = new UserBean();
                u.setId(set.getInt(set.findColumn("id")));
                u.setName(set.getString(set.findColumn("name")));
                u.setPhone(set.getString(set.findColumn("phone")));
                u.setRate(set.getFloat(set.findColumn("rate")));
                users.add(u);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getChatTimes() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select id,name,phone,chattime from user ORDER BY  chattime DESC ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<UserBean> users = new ArrayList<UserBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            set = ps.executeQuery();
            while (set.next()){
                UserBean u = new UserBean();
                u.setId(set.getInt(set.findColumn("id")));
                u.setName(set.getString(set.findColumn("name")));
                u.setPhone(set.getString(set.findColumn("phone")));
                u.setChattime(set.getLong(set.findColumn("chattime")));
                users.add(u);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(users);
        return baseResBean;
    }

    public BaseResBean getUserAreaUser(UserBean u) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        BaseResBean baseResBean = new BaseResBean();
        String str = "select distinct a.id from user as a,userarea as b,userarea as c where a.usertype <> ? and a.id = c.userid and c.areaid = b.areaid and b.userid = ?";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,UserBean.USER_TYPE_CUSTOMER);
            ps.setInt(2,u.getId());
            set = ps.executeQuery();
            while (set.next()){
               ids.add(set.getInt(set.findColumn("id")));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }

        ArrayList<UserBean> users = new ArrayList<UserBean>();
        for(int i=0;i<ids.size();i++){
            UserBean uu = new UserBean();
            uu.setId(ids.get(i));
            users.add((UserBean) getUserInfoById(uu).getData());
        }
        baseResBean.setData(users);
        return baseResBean;
    }
}
