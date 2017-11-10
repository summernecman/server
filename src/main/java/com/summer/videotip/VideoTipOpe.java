package com.summer.videotip;

import com.summer.base.bean.BaseResBean;
import com.summer.unit.DBI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by SWSD on 2017-11-09.
 */
public class VideoTipOpe implements VideoTipI {



    public BaseResBean getAllVideoTips() {
        return DBI.executeQuery(VideoTipBean.class,"select * from videotip",null);
    }

    public BaseResBean getAllVideoTipsMap() {
        ArrayList<VideoTipBean> videotips = (ArrayList<VideoTipBean>) DBI.executeQuery(VideoTipBean.class,"select * from videotip",null).getData();
        HashMap<String,VideoTipBean> data = new HashMap<String, VideoTipBean>();
        for(int i=0;videotips!=null && i<videotips.size();i++){
            data.put(videotips.get(i).getType(),videotips.get(i));
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(data);
        return baseResBean;
    }

    public BaseResBean addVideoTips(VideoTipBean v) {
        return DBI.execute("insert into videotip(ctime,utime,type,txt)",new Date(),new Date(),v.getType(),v.getTxt());
    }
}
