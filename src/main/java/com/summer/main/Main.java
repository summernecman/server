package com.summer.main;

import com.google.gson.reflect.TypeToken;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
import com.summer.video.bean.VideoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SWSD on 2017-07-19.
 */
@Controller
@RequestMapping("/server")
public class Main {

    UserI userI = new UserOpe();

    VideoI videoI = new VideoOpe();

    @RequestMapping(value = "/userlist",method = RequestMethod.POST)
    public void hello(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserList()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.login(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public void loginOut(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.logout(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getLoginInfo",method = RequestMethod.POST)
    public void getLoginInfo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getLoginInfo(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getVideos",method = RequestMethod.POST)
    public void getVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getVideos(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getHistoryVideos",method = RequestMethod.POST)
    public void getHistoryVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getVideosByUserPhone(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addVideo",method = RequestMethod.POST)
    public void addVideo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.addVideo(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getVideoByName",method = RequestMethod.POST)
    public void getVideoByName(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getVideoByName(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @RequestMapping(value = "/getVideosByContacts",method = RequestMethod.POST)
    public void getVideosByContacts(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getVideosByContacts(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/commentVideos",method = RequestMethod.POST)
    public void commentVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        CommentBean commentBean = GsonUtil.getInstance().fromJson(str,CommentBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.commentVideos(commentBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static  void init(HttpServletRequest req, HttpServletResponse rep){
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rep.setCharacterEncoding("UTF-8");
        rep.setContentType("application/json;charset=UTF-8");
    }


}
