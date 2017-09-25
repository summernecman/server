package com.summer.main;

import com.summer.base.bean.BaseResBean;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
import com.summer.video.bean.VideoBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SWSD on 2017-07-19.
 */
@Controller
@RequestMapping("/server")
public class VideoMapping {

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
//        UserBean userBean = new UserBean();
//        userBean.setPhone("18711111111");
//        BaseResBean baseResBean = new BaseResBean();
//        baseResBean.setData(userBean);
//        String sr= null;
//         HttpRequest.postJson("http://192.168.20.175:8079/server/user/getUserInfoByPhone", GsonUtil.getInstance().toJson(userBean));
//        System.out.println(sr);






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


    @RequestMapping(value = "/getVideosByContacts2",method = RequestMethod.POST)
    public void getVideosByContacts2(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getVideosByContacts2(userBean)));
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


    @RequestMapping(value = "/getAllVideos",method = RequestMethod.POST)
    public void getAllVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getAllVideos()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getAllVideosByGet",method = RequestMethod.GET)
    public void getAllVideosByGet(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getAllVideos()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/isVideoUploaded",method = RequestMethod.GET)
    public void isVideoUploaded(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.isVideoUploaded(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/setVideoUploaded",method = RequestMethod.POST)
    public void setVideoUploaded(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.setVideoUploaded(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    @RequestMapping(value = "/uploadvideo",method = RequestMethod.POST)
    public void addFiles(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File parent = new File("c://files");
        if(!parent.exists()){
            parent.mkdirs();
        }

        factory.setRepository(new File("c://temp"));
        factory.setSizeThreshold(1024*1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<String> files = new ArrayList<String>();
        try {
            ArrayList<FileItem> list = (ArrayList<FileItem>) upload.parseRequest(req);
            for(int i=0;i<list.size();i++){
                list.get(i).getInputStream();
                String[] ss = list.get(i).getName().split("_");
                File typeFile = new File(parent, ss[1]);
                if(!typeFile.exists()){
                    typeFile.mkdirs();
                }
                File file = new File(typeFile,list.get(i).getName());

                System.out.println(file.getPath());
                files.add(ss[1]+"/"+list.get(i).getName());
                if(!file.exists()){
                    //file.createNewFile();
                    list.get(i).write(file);
                    list.get(i).delete();

//                    InputStream is=list.get(i).getInputStream();
//                    FileOutputStream fos=new FileOutputStream(file);
//                    byte[] buff=new byte[1024];
//                    int len=0;
//                    while((len=is.read(buff))>0){
//                        fos.write(buff);
//                    }
//                    is.close();
//                    fos.close();
//                    list.get(i).delete();
                }
            }
            baseResBean.setData(files);

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getMaxVideoId",method = RequestMethod.POST)
    public void getMaxVideoId(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getMaxVideoId()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/insert_and_getid_fromvieo",method = RequestMethod.POST)
    public void insert_and_getid_fromvieo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.insert_and_getid_fromvieo(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/updateVideoById",method = RequestMethod.POST)
    public void updateVideoById(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.updateVideoById(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static  void init(HttpServletRequest req, HttpServletResponse rep){
        try {
            req.setCharacterEncoding("UTF-8");
            rep.setHeader("Access-Control-Allow-Origin", "*");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rep.setCharacterEncoding("UTF-8");
        rep.setContentType("application/json;charset=UTF-8");
    }


}