package com.summer.control;

import com.summer.collection.CollectionOpe;
import com.summer.collection.bean.CollectionBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-09-04.
 */
@Controller
@RequestMapping("/collection")
public class CollectionControl {

    CollectionOpe collectionI = new CollectionOpe();

    @RequestMapping(value = "/getCollectionVideosByUserId",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,collectionI.getCollectionVideosByUserId(userBean));
    }

    @RequestMapping(value = "/getCollectionVideosByUserIdWithLimit",method = RequestMethod.POST)
    public void getCollectionVideosByUserIdWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,collectionI.getCollectionVideosByUserIdWithLimit(userBean));
    }


    @RequestMapping(value = "/isCollectedByVideoIdAndUserId",method = RequestMethod.POST)
    public void isCollectedByVideoIdAndUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CollectionBean.class);
        VideoControl.printOut(rep,collectionI.isCollectedByVideoIdAndUserId(collectionBean));
    }


    @RequestMapping(value = "/getCollectionNumByUserId",method = RequestMethod.POST)
    public void getCollectionNumByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,collectionI.getCollectionNumByUserId(userBean));
    }





    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    public void collect(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CollectionBean.class);
        VideoControl.printOut(rep,collectionI.collect(collectionBean));
    }

    @RequestMapping(value = "/discollect",method = RequestMethod.POST)
    public void discollect(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CollectionBean collectionBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CollectionBean.class);
        VideoControl.printOut(rep,collectionI.disCollect(collectionBean));
    }
}
