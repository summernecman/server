package com.summer.feedback;

import com.summer.base.bean.BaseResBean;
import com.summer.main.DBUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SWSD on 17-09-08.
 */
public class FeedBackOpe implements FeedBackI {


    public BaseResBean sendFeedBack(FeedBackBean feedBackBean) {
        BaseResBean baseResBean = new BaseResBean();
        String str = "insert into feedback(remark,rate,createdtime,userid) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setString(1,feedBackBean.getRemark());
            ps.setFloat(2,feedBackBean.getRate());
            ps.setString(3,feedBackBean.getCreate());
            ps.setInt(4,feedBackBean.getUserid());
            ps.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(feedBackBean);
        return baseResBean;
    }
}
