package com.summer.tip;

import com.summer.base.bean.BaseResBean;
import com.summer.comment.bean.TipBean;
import com.summer.main.DBUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SWSD on 17-10-12.
 */
public class TipOpe implements TipI {

    public BaseResBean getTips() {
        BaseResBean baseResBean = new BaseResBean();
        String str = "select * from tip WHERE enable > ? ORDER BY id ";
        PreparedStatement ps = null;
        ResultSet set = null;
        Connection connection = null;
        ArrayList<TipBean> tipBeen = new ArrayList<TipBean>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(str);
            ps.setInt(1,0);
            set = ps.executeQuery();
            while (set.next()){
                TipBean tipBean = new TipBean();
                tipBean.setPosition(set.getInt(set.findColumn("position")));
                tipBean.setTip(set.getString(set.findColumn("tip")));
                tipBean.setEnable(set.getInt(set.findColumn("enable")));
                tipBeen.add(tipBean);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,set);
        }
        baseResBean.setData(tipBeen);
        return baseResBean;
    }

    public BaseResBean getMapTips() {
        ArrayList<TipBean> tipBeen = (ArrayList<TipBean>) getTips().getData();
        HashMap<Integer,String> map = new HashMap<Integer, String>();
        for(int i=0;i<tipBeen.size();i++){
            map.put(tipBeen.get(i).getPosition(),tipBeen.get(i).getTip());
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(map);
        return baseResBean;
    }
}
