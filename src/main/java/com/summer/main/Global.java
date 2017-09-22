package com.summer.main;

import com.summer.base.bean.BaseResBean;
import com.summer.em.bean.EMTokenBean;
import com.summer.em.bean.EMUserBean;
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
        //判断admin用户是否存在

        BaseResBean b = HttpRequest.sendGet(Global.URL+"/users/admin",null, Global.emTokenBean.getAccess_token());
        if(b.isException()){
            try {
                EMUserBean emUserBean = new EMUserBean("admin","admin");
                HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/users", GsonUtil.getInstance().toJson(emUserBean),Global.emTokenBean.getAccess_token());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        BaseResBean rb = HttpRequest.sendGet(Global.URL+"/chatrooms",null, Global.emTokenBean.getAccess_token());
        System.out.println(rb);
        ChatRoomBean chatRoomBean = GsonUtil.getInstance().fromJson(rb.getData().toString(),ChatRoomBean.class);
        if(chatRoomBean.getCount()==0){
            RoomBean roomBean = new RoomBean();
            HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/chatrooms", GsonUtil.getInstance().toJson(roomBean),Global.emTokenBean.getAccess_token());
        }


    }

    @PreDestroy
    public void onStop(){

    }
}
