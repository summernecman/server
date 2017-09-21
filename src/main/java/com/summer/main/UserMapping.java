package com.summer.main;

import com.google.gson.reflect.TypeToken;
import com.summer.base.bean.BaseResBean;
import com.summer.em.EMI;
import com.summer.em.EMOpe;
import com.summer.em.bean.EMUserBean;
import com.summer.em.bean.EMUserStatusBean;
import com.summer.network.HttpRequest;
import com.summer.unit.UnitBean;
import com.summer.unit.UnitI;
import com.summer.unit.UnitOpe;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.AllUserBean;
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

    UnitI unitI = new UnitOpe();

    EMI emi = new EMOpe();

    @RequestMapping(value = "/setHeadurl",method = RequestMethod.POST)
    public void setHeadUrl(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
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
        VideoMapping.init(req,rep);
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


    @RequestMapping(value = "/getUserListWithOutMe",method = RequestMethod.POST)
    public void getUserListWithOutMe(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserListWithOutMe(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getUsersInfoByPhone",method = RequestMethod.POST)
    public void getUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
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


    @RequestMapping(value = "/getOtherUsersInfoByPhone",method = RequestMethod.POST)
    public void getOtherUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        AllUserBean data = GsonUtil.getInstance().fromJson(str,AllUserBean.class);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getOtherUsersInfoByPhone(data)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/registEMAccount",method = RequestMethod.POST)
    public void registEMAccount(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        UserBean data = GsonUtil.getInstance().fromJson(str,UserBean.class);
        EMUserBean emUserBean = new EMUserBean(data.getPhone(),data.getPwd());
        BaseResBean res = HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/users", GsonUtil.getInstance().toJson(emUserBean),Global.emTokenBean.getAccess_token());
        System.out.println(res.getData());

        if(!res.isException()){
            try {
                PrintWriter printWriter = rep.getWriter();
                printWriter.println(GsonUtil.getInstance().toJson(userI.addUser(data)));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                PrintWriter printWriter = rep.getWriter();
                printWriter.println(GsonUtil.getInstance().toJson(res));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }





    @RequestMapping(value = "/getUserInfoByPhone",method = RequestMethod.POST)
    public void getUserInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        UserBean  data  = GsonUtil.getInstance().fromJson(str,UserBean.class);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserInfoByPhone(data)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getArrayUsersInfoByPhone",method = RequestMethod.POST)
    public void getArrayUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
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
        VideoMapping.init(req,rep);
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
        VideoMapping.init(req,rep);
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
        VideoMapping.init(req,rep);
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
        VideoMapping.init(req,rep);
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


    @RequestMapping(value = "/updateUnit",method = RequestMethod.POST)
    public void unpdateUnit(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        System.out.println(str);
        UserBean data = GsonUtil.getInstance().fromJson(str,UserBean.class);
        UnitBean userBean = (UnitBean) unitI.getUnitByName(data.getUnit()).getData();
        if(userBean==null){
            unitI.addUnit(data.getUnit());
            userBean = (UnitBean) unitI.getUnitByName(data.getUnit()).getData();
        }
        data.setUnit(userBean);
        data.setUnitid(userBean.getId());
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.updateUnitInfo(data)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getUnTypeUserList",method = RequestMethod.POST)
    public void getUnTypeUserList(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        ArrayList<UserBean> list = (ArrayList<UserBean>) userI.getUnTypeUserList(userBean).getData();
        for(int i=0;list!=null && i<list.size();i++){
            EMUserStatusBean b = GsonUtil.getInstance().fromJson(emi.getEMUserStatus(list.get(i)).getData().toString(),EMUserStatusBean.class);
            list.get(i).setState(b.isOnline()?1:0);
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(list);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/getServerInfo",method = RequestMethod.POST)
    public void getServerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_SERVER);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserListWithType(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getCustomerInfo",method = RequestMethod.POST)
    public void getCustomerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_CUSTOMER);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserListWithType(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getEngineerInfo",method = RequestMethod.POST)
    public void getEngineerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_ENGINEER);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(userI.getUserListWithType(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void addUser(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        if((Boolean) (userI.isUserExist(userBean).getData())){
            try {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setException(true);
                baseResBean.setErrorMessage("用户已存在");
                PrintWriter printWriter = rep.getWriter();
                printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                PrintWriter printWriter = rep.getWriter();
                printWriter.println(GsonUtil.getInstance().toJson(userI.addUser(userBean)));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
