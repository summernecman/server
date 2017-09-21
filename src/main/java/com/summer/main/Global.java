package com.summer.main;

import com.summer.base.bean.BaseResBean;
import com.summer.em.bean.EMTokenBean;
import com.summer.network.HttpRequest;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by SWSD on 17-09-14.
 */
@Service
public class Global {

    public static EMTokenBean emTokenBean = null;

    public static String URL = "https://a1.easemob.com/1122170703115322/service";


    @PostConstruct
    public void onStart(){
        EMTokenBean tokenBean  = new EMTokenBean();
        tokenBean.setGrant_type("client_credentials");
        tokenBean.setClient_secret("YXA6PGKo_miQlFhvwpX6iWwrU1cUSYk");
        tokenBean.setClient_id("YXA6meYs8F_OEeehVq2AtEHmBQ");
        BaseResBean res = HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/token", GsonUtil.getInstance().toJson(tokenBean));
        if(!res.isException()){
            emTokenBean= GsonUtil.getInstance().fromJson(res.getData().toString(),EMTokenBean.class);
            System.out.println(res.getData().toString());
        }
    }

    @PreDestroy
    public void onStop(){

    }
}
