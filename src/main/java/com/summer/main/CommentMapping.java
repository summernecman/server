package com.summer.main;

import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentI;
import com.summer.comment.CommentOpe;
import com.summer.comment.bean.RateLevelBean;
import com.summer.comment.bean.TipBean;
import com.summer.comment.bean.TipsBean;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SWSD on 17-08-29.
 */
@Controller
@RequestMapping("/comment")
public class CommentMapping {

    CommentOpe commentI = new CommentOpe();

    @RequestMapping(value = "/getCommentByUserPhone",method = RequestMethod.POST)
    public void getCommentByUserName(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getCommentByUserName(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getCommentByUserId",method = RequestMethod.POST)
    public void getCommentByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getCommentByUserId(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getShortCommentByUserId",method = RequestMethod.POST)
    public void getShortCommentByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getShortCommentByUserId(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getCommentByUserNameWithMyOption",method = RequestMethod.POST)
    public void getCommentByUserNameWithMyOption(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        CommentBean commentBean = GsonUtil.getInstance().fromJson(str,CommentBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getCommentByUserNameWithMyOption(commentBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getCommentNumByUserName",method = RequestMethod.POST)
    public void getCommentNumByUserName(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getCommentNumByUserName(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/getVideoRateCommentByUseId",method = RequestMethod.POST)
    public void getVideoRateCommentByUseId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getVideoRateCommentByUseId(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getVideoCommentByVideoName",method = RequestMethod.POST)
    public void getVideoCommentByVideoName(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getVideoCommentByVideoName(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getUserTips",method = RequestMethod.POST)
    public void getUserTips(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getTips(userBean).getData();
        HashMap<Integer,TipBean> tipBeanHashMap = new HashMap<Integer, TipBean>();
        for(int i=0;i<comments.size();i++){
            TipsBean tipBean = GsonUtil.getInstance().fromJson(comments.get(i).getTips(),TipsBean.class);
            for(int j = 0; j<tipBean.getTipBeen().size(); j++){
                if(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition())==null){
                    TipBean tipBean1 = new TipBean(tipBean.getTipBeen().get(j).getPosition(),tipBean.getTipBeen().get(j).getTip(),0,false);
                    tipBeanHashMap.put(tipBean.getTipBeen().get(j).getPosition(),tipBean1);
                }
                if(tipBean.getTipBeen().get(j).isSelect()){
                    tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).setNum(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).getNum()+1);
                }
            }
        }

        try {
            PrintWriter printWriter = rep.getWriter();
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setData(tipBeanHashMap);
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getUserTipsByUserId",method = RequestMethod.POST)
    public void getUserTipsByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getTipsByUserId(userBean).getData();
        HashMap<Integer,TipBean> tipBeanHashMap = new HashMap<Integer, TipBean>();
        for(int i=0;i<comments.size();i++){
            TipsBean tipBean = GsonUtil.getInstance().fromJson(comments.get(i).getTips(),TipsBean.class);
            for(int j = 0; j<tipBean.getTipBeen().size(); j++){
                if(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition())==null){
                    TipBean tipBean1 = new TipBean(tipBean.getTipBeen().get(j).getPosition(),tipBean.getTipBeen().get(j).getTip(),0,false);
                    tipBeanHashMap.put(tipBean.getTipBeen().get(j).getPosition(),tipBean1);
                }
                if(tipBean.getTipBeen().get(j).isSelect()){
                    tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).setNum(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).getNum()+1);
                }
            }
        }

        try {
            PrintWriter printWriter = rep.getWriter();
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setData(tipBeanHashMap);
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getVideoCommentByVideoNameAndFrom",method = RequestMethod.POST)
    public void getVideoCommentByVideoNameAndFrom(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getVideoCommentByVideoNameAndFrom(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getVideoCommentByVideoIdAndFrom",method = RequestMethod.POST)
    public void getVideoCommentByVideoIdAndFrom(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getVideoCommentByVideoIdAndFrom(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getVideoCommentRateLevelByuserId",method = RequestMethod.POST)
    public void getVideoCommentRateLevelByuserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        ArrayList<RateLevelBean> rateLevelBeen = (ArrayList<RateLevelBean>) commentI.getVideoCommentRateLevelByuserId(userBean).getData();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<=5;i++){
            map.put(i,0);
        }
        for(int i=0;rateLevelBeen!=null&&i<rateLevelBeen.size();i++){
            int rate = (int) rateLevelBeen.get(i).getRatef();
            if(rate>5){
                rate=5;
            }

            if(rate<0){
                rate=0;
            }
            int n = map.get(rate);
            map.put(rate,n+1);
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(map);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
