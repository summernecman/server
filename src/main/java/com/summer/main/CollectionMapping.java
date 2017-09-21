package com.summer.main;

import com.summer.collection.CollectionI;
import com.summer.collection.CollectionOpe;
import com.summer.collection.bean.CollectionBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-09-04.
 */
@Controller
@RequestMapping("/collection")
public class CollectionMapping {

    CollectionI collectionI = new CollectionOpe();

    @RequestMapping(value = "/getCollectionVideosByUserId",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(collectionI.getCollectionVideosByUserId(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/isCollectedByVideoIdAndUserId",method = RequestMethod.POST)
    public void isCollectedByVideoIdAndUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(str,CollectionBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(collectionI.isCollectedByVideoIdAndUserId(collectionBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getCollectionNumByUserId",method = RequestMethod.POST)
    public void getCollectionNumByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(collectionI.getCollectionNumByUserId(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    public void collect(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(str,CollectionBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(collectionI.collect(collectionBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/discollect",method = RequestMethod.POST)
    public void discollect(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(str,CollectionBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(collectionI.disCollect(collectionBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
