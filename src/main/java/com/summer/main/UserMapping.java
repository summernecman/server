package com.summer.main;

import com.google.gson.reflect.TypeToken;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentI;
import com.summer.comment.CommentOpe;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.UserBean;
import com.summer.util.DateFormatUtil;
import com.summer.util.GsonUtil;
import com.summer.video.VideoI;
import com.summer.video.VideoOpe;
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
import java.util.ArrayList;

/**
 * Created by SWSD on 17-08-30.
 */
@Controller
@RequestMapping("/user")
public class UserMapping {

    UserI userI = new UserOpe();

    VideoI videoI = new VideoOpe();

    @RequestMapping(value = "/setHeadurl",method = RequestMethod.POST)
    public void setHeadUrl(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.setHeadUrl(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public void getUserList(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
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

    @RequestMapping(value = "/getUsersInfoByPhone",method = RequestMethod.POST)
    public void getUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        ArrayList<UserBean> data  = GsonUtil.getInstance().fromJson(str,new TypeToken<ArrayList<UserBean>>(){}.getType());
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUsersInfoByPhone(data)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getArrayUsersInfoByPhone",method = RequestMethod.POST)
    public void getArrayUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        ArrayList<ArrayList<UserBean>> data  = GsonUtil.getInstance().fromJson(str,new TypeToken<ArrayList<ArrayList<UserBean>>>(){}.getType());
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getArrayUsersInfoByPhone(data)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/getusercallinfo",method = RequestMethod.POST)
    public void getUserCallInfo(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(videoI.getUserCallInfo(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/setUserName",method = RequestMethod.POST)
    public void setUserName(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.setUserName(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/addfiles",method = RequestMethod.POST)
    public void addFiles(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File parent = new File("c://files");
        if(!parent.exists()){
            parent.mkdirs();
        }
        File typeFile = new File(parent, DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD));
        if(!typeFile.exists()){
            typeFile.mkdirs();
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

                File file = new File(typeFile,list.get(i).getName());
                System.out.println(file.getPath());
                files.add(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD)+"/"+list.get(i).getName());
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


    @RequestMapping(value = "/addheadurl",method = RequestMethod.POST)
    public void addFile(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File parent = new File("c://files");
        if(!parent.exists()){
            parent.mkdirs();
        }
        File typeFile = new File(parent, DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD));
        if(!typeFile.exists()){
            typeFile.mkdirs();
        }
        factory.setRepository(new File("c://temp"));
        factory.setSizeThreshold(1024*1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        BaseResBean baseResBean = new BaseResBean();
        ArrayList<String> files = new ArrayList<String>();
        try {
            ArrayList<FileItem> list = (ArrayList<FileItem>) upload.parseRequest(req);
            list.get(0).getInputStream();
            File file = new File(typeFile,list.get(0).getName());
            System.out.println(file.getPath());
            files.add(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD)+"/"+list.get(0).getName());
            if(!file.exists()){
                //file.createNewFile();
                list.get(0).write(file);
                list.get(0).delete();
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


}
