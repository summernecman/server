package com.summer.app;

import com.summer.base.OnFinishListener;
import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SWSD on 2017-12-29.
 */
public class AppOpe implements AppI{


    @Override
    public BaseResBean updateVersion(AppBean appBean) {
        List<AppBean> appBeanList = (List<AppBean>) DBI.executeQuery(AppBean.class,"select * from app").getData();
        if(appBeanList==null || appBeanList.size()==0){
            DBI.execute("insert into app(versioncode,versionname,url) values(?,?,?)",appBean.getVersioncode(),appBean.getVersionname(),appBean.getUrl());
        }else{
            if(appBean.getUrl()==null ||appBean.getUrl().equals("")){
                DBI.execute("update app set versioncode = ? ,versionname = ?",appBean.getVersioncode(),appBean.getVersionname());
            }else{
                DBI.execute("update app set versioncode = ? ,versionname = ? ,url = ?",appBean.getVersioncode(),appBean.getVersionname(),appBean.getUrl());
            }
        }
        return new BaseResBean();
    }

    @Override
    public BaseResBean CheckVersion() {
        ArrayList<AppBean> appBeen = (ArrayList<AppBean>) DBI.executeQuery(AppBean.class,"select * from app").getData();
        BaseResBean baseResBean = new BaseResBean();
        if(appBeen!=null && appBeen.size()>0){
            baseResBean.setData(appBeen.get(0));
        }
        return baseResBean;
    }
}
