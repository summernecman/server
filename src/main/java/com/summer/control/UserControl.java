package com.summer.control;

import com.google.gson.reflect.TypeToken;
import com.summer.area.AreaBean;
import com.summer.area.AreaI;
import com.summer.area.AreaOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentOpe;
import com.summer.contact.ContactBean;
import com.summer.contact.ContactOpe;
import com.summer.em.EMOpe;
import com.summer.em.bean.EMUserBean;
import com.summer.em.bean.EMUserStatusBean;
import com.summer.network.HttpRequest;
import com.summer.unit.DBUtil;
import com.summer.unit.UnitBean;
import com.summer.unit.UnitOpe;
import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.AllUserBean;
import com.summer.user.bean.UserBaseResBean;
import com.summer.user.bean.UserBean;
import com.summer.user.bean.WebIndexInfo;
import com.summer.userarea.UserAreaBean;
import com.summer.userarea.UserAreaI;
import com.summer.userarea.UserAreaOpe;
import com.summer.util.DateFormatUtil;
import com.summer.util.GsonUtil;
import com.summer.video.VideoOpe;
import com.summer.video.bean.CallDistribution;
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
import java.util.Collection;

/**
 * Created by SWSD on 17-08-30.
 */
@Controller
@RequestMapping("/user")
public class UserControl {

    UserOpe userI = new UserOpe();

    VideoOpe videoI = new VideoOpe();

    UnitOpe unitI = new UnitOpe();

    EMOpe emi = new EMOpe();

    ContactOpe contactI = new ContactOpe();

    UserAreaOpe userAreaI = new UserAreaOpe();

    CommentOpe commentI = new CommentOpe();

    AreaOpe areaI = new AreaOpe();


    @RequestMapping(value = "/setHeadurl",method = RequestMethod.POST)
    public void setHeadUrl(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.setHeadUrl(userBean));
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public void getUserList(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoControl.printOut(rep,userI.getUserList());
    }


    @RequestMapping(value = "/getUserListWithOutMe",method = RequestMethod.POST)
    public void getUserListWithOutMe(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.getUserListWithOutMe(userBean));
    }


    @RequestMapping(value = "/getUsersInfoByPhone",method = RequestMethod.POST)
    public void getUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<UserBean> data  = GsonUtil.getInstance().fromJson(req.getParameter("data"),new TypeToken<ArrayList<UserBean>>(){}.getType());
        VideoControl.printOut(rep,userI.getUsersInfoByPhone(data));
    }



    @RequestMapping(value = "/resetPwd",method = RequestMethod.POST)
    public void resetPwd(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean  = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        BaseResBean b = userI.resetPwd(userBean);
        VideoControl.printOut(rep,b);
    }


    @RequestMapping(value = "/getOtherUsersInfoByPhone",method = RequestMethod.POST)
    public void getOtherUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AllUserBean data = GsonUtil.getInstance().fromJson(req.getParameter("data"),AllUserBean.class);

//        1.除了客户不能和客户通讯，其他的都可以互相通讯。
//        2.客户好友列表显示所属区域上班区间所有人。
//        3.客服和工程师显示配置人员

        ArrayList<UserBean> users = new ArrayList<UserBean>();
        if(data.getMe().getUsertype()==2){
            users.addAll((ArrayList<UserBean>) userI.getUserAreaUser(data.getMe()).getData());
        }else{
            //我的全部联系人
            users.addAll((ArrayList<UserBean>) contactI.getContactsByUserIdWithOutAgree(data.getMe()).getData());
        }

        //环信聊天室在线人
        ArrayList<UserBean> userBeen = (ArrayList<UserBean>) userI.getOtherUsersShortInfoByPhone(data).getData();
        for(int i=0;i<users.size();i++){
            for(int j=0;j<userBeen.size();j++){
                if(userBeen.get(j).getPhone().equals(users.get(i).getPhone())){
                    users.get(i).setState(UserBean.STATE_ONLINE);
                    break;
                }else{
                    users.get(i).setState(UserBean.STATE_OFFLINE);
                }
            }
        }
        ArrayList<UserBean> a = new ArrayList<UserBean>();
        ArrayList<UserBean> b = new ArrayList<UserBean>();
        for(int i = 0;users!=null&&users.size()>0&& i<users.size();i++){
            if(users.get(i).getState()==UserBean.STATE_ONLINE){
                a.add(users.get(i));
            }else{
                b.add(users.get(i));
            }
        }
        a.addAll(b);

        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(a);
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getUserContactsByUserIdAndType",method = RequestMethod.POST)
    public void getUserContactsByUserIdAndType(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean data = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
//        1.除了客户不能和客户通讯，其他的都可以互相通讯。
//        2.客户好友列表显示所属区域所有客服和工程师。
//        3.客服和工程师显示配置人员

        ArrayList<UserBean> users = new ArrayList<UserBean>();
        if(data.getUsertype()==2){
            users.addAll((ArrayList<UserBean>) userI.getUserAreaUser(data).getData());
        }else{
            //我的全部联系人
            users.addAll((ArrayList<UserBean>) contactI.getContactsByUserIdWithOutAgree(data).getData());
        }

        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(users);
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/registEMAccount",method = RequestMethod.POST)
    public void registEMAccount(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean data = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);



        EMUserBean emUserBean = new EMUserBean(data.getPhone(),"111111");
        BaseResBean res = HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/users", GsonUtil.getInstance().toJson(emUserBean),Global.emTokenBean.getAccess_token());
        System.out.println(res.getData());

        if(!res.isException()){
            if(!(Boolean) (userI.isUserExist(data).getData())){
                VideoControl.printOut(rep,userI.addUser(data));
            }else{
                VideoControl.printOut(rep,data);
            }

        }else{
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(res.getData().toString());
            VideoControl.printOut(rep,baseResBean);
        }

    }

    @RequestMapping(value = "/AddUser",method = RequestMethod.POST)
    public void AddUser(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean data = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        //判断用户是否存在
        if((Boolean) (userI.isUserExist(data).getData())){
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setException(true);
            baseResBean.setErrorMessage("用户已存在");
            VideoControl.printOut(rep,baseResBean);
            return;
        }
        if(data.getPwd()==null || "".equals(data.getPwd())){
            data.setPwd("111111");
        }
        //注册环信账号
        EMUserBean emUserBean = new EMUserBean(data.getPhone(),"111111");
        BaseResBean res = HttpRequest.postJson("https://a1.easemob.com/1122170703115322/service/users", GsonUtil.getInstance().toJson(emUserBean),Global.emTokenBean.getAccess_token());
        System.out.println(res.getData());

        if(!res.isException()){
            //添加用户信息
            userI.addUser2(data);
            int id = (Integer) userI.getUserIdByPhone(data).getData();
            data.setId(id);

            for(int i=0;data.getArea()!=null && i<data.getArea().size();i++){
                userAreaI.addUserArea(new UserAreaBean(data.getId(),data.getArea().get(i).getId()));
            }
            for(int i=0;i<data.getContacts().size();i++){
                ContactBean contactBean = new ContactBean();
                contactBean.setFromid(data.getId());
                contactBean.setToid(data.getContacts().get(i).getId());
                contactI.addContactsByUserid(contactBean);
            }
            VideoControl.printOut(rep,new BaseResBean());
        }else{
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setException(true);
            baseResBean.setErrorMessage(res.getData()==null? "":res.getData().toString());
            VideoControl.printOut(rep,baseResBean);
        }

    }





    @RequestMapping(value = "/getUserInfoByPhone",method = RequestMethod.POST)
    public void getUserInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean  data  = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.getUserInfoByPhone(data));
    }

    @RequestMapping(value = "/getUserInfoById",method = RequestMethod.POST)
    public void getUserInfoById(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean  data  = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.getUserInfoById(data));
    }


    @RequestMapping(value = "/getArrayUsersInfoByPhone",method = RequestMethod.POST)
    public void getArrayUsersInfoByPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<ArrayList<UserBean>> data  = GsonUtil.getInstance().fromJson(req.getParameter("data"),new TypeToken<ArrayList<ArrayList<UserBean>>>(){}.getType());
        VideoControl.printOut(rep,userI.getArrayUsersInfoByPhone(data));
    }



    @RequestMapping(value = "/getusercallinfo",method = RequestMethod.POST)
    public void getUserCallInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,videoI.getUserCallInfo(userBean));
    }



    @RequestMapping(value = "/setUserName",method = RequestMethod.POST)
    public void setUserName(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.setUserName(userBean));
    }


    @RequestMapping(value = "/addfiles",method = RequestMethod.POST)
    public void addFiles(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File parent = new File(DBUtil.record);
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
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/addheadurl",method = RequestMethod.POST)
    public void addFile(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File parent = new File(DBUtil.record);
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
            String[] ss = new String[]{"!","@","#","$","%","&","^","*","(",")","/","\\","|",",","`","~"};
            String s = list.get(0).getName();
            for(int i=0;i<ss.length;i++){
                s= s.replace(ss[i],"");
            }
            if(s.length()>20 ){
                s= s.substring(s.length()-20,s.length());
            }
            File file = new File(typeFile,s);
            System.out.println(file.getPath());
            files.add(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD)+"/"+s);
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
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/updateUnit",method = RequestMethod.POST)
    public void unpdateUnit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean data = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        UnitBean userBean = (UnitBean) unitI.getUnitByName(data.getUnit()).getData();
        if(userBean==null){
            unitI.addUnit(data.getUnit());
            userBean = (UnitBean) unitI.getUnitByName(data.getUnit()).getData();
        }
        data.setUnit(userBean);
        data.setUnitid(userBean.getId());
        VideoControl.printOut(rep,userI.updateUnitInfo(data));
    }


    @RequestMapping(value = "/getUnTypeUserList",method = RequestMethod.POST)
    public void getUnTypeUserList(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        ArrayList<UserBean> list = (ArrayList<UserBean>) userI.getUnTypeUserList(userBean).getData();
        for(int i=0;list!=null && i<list.size();i++){
            EMUserStatusBean b = GsonUtil.getInstance().fromJson(emi.getEMUserStatus(list.get(i)).getData().toString(),EMUserStatusBean.class);
            list.get(i).setState(b.isOnline()?1:0);
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(list);
        VideoControl.printOut(rep,baseResBean);
    }



    @RequestMapping(value = "/getUsersInfo",method = RequestMethod.POST)
    public void getUsersInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        UserBaseResBean userBaseResBean = (UserBaseResBean) userI.getUserListWithType(userBean);
        userBaseResBean.setTotal((Integer) userI.getUserNumWithType(userBean).getData());
        VideoControl.printOut(rep,userBaseResBean);
    }


    @RequestMapping(value = "/getUsersInfoWithLimit",method = RequestMethod.POST)
    public void getUsersInfoWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        UserBaseResBean userBaseResBean = (UserBaseResBean) userI.getUserListWithTypeAndLimit(userBean);
        userBaseResBean.setTotal((Integer) userI.getUserNumWithType(userBean).getData());
        VideoControl.printOut(rep,userBaseResBean);
    }



    @RequestMapping(value = "/getServerInfo",method = RequestMethod.POST)
    public void getServerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_SERVER);
        VideoControl.printOut(rep,userI.getUserListWithType(userBean));
    }


    @RequestMapping(value = "/getServerInfoWithLimit",method = RequestMethod.POST)
    public void getServerInfoWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_SERVER);
        VideoControl.printOut(rep,userI.getUserListWithTypeAndLimit(userBean));
    }

    @RequestMapping(value = "/getCustomerInfo",method = RequestMethod.POST)
    public void getCustomerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_CUSTOMER);
        VideoControl.printOut(rep,userI.getUserListWithType(userBean));
    }


    @RequestMapping(value = "/getCustomerInfoWithLimit",method = RequestMethod.POST)
    public void getCustomerInfoWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_CUSTOMER);
        VideoControl.printOut(rep,userI.getUserListWithTypeAndLimit(userBean));
    }



    @RequestMapping(value = "/getEngineerInfo",method = RequestMethod.POST)
    public void getEngineerInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_ENGINEER);
        VideoControl.printOut(rep,userI.getUserListWithType(userBean));
    }


    @RequestMapping(value = "/getEngineerInfoWithLimit",method = RequestMethod.POST)
    public void getEngineerInfoWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userBean.setUsertype(UserBean.USER_TYPE_ENGINEER);
        VideoControl.printOut(rep,userI.getUserListWithTypeAndLimit(userBean));
    }




    @RequestMapping(value = "/getUnTypeUserShortList",method = RequestMethod.POST)
    public void getUnTypeUserShortList(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.getUnTypeUserShortList(userBean));
    }


    @RequestMapping(value = "/updateAreaByid",method = RequestMethod.POST)
    public void updateAreaByid(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<UserAreaBean> userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),new TypeToken<ArrayList<UserAreaBean>>(){}.getType());

        for(int i=0;i<userBean.size();i++){
            if((Boolean) (userAreaI.isUserHaveArea(userBean.get(i)).getData())){
                continue;
            }
            userAreaI.addUserArea(userBean.get(i));
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(userBean);
        VideoControl.printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/updateRemarkById",method = RequestMethod.POST)
    public void updateRemarkById(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,userI.updateRemark(userBean));
    }


    @RequestMapping(value = "/saveUserInfoInWeb",method = RequestMethod.POST)
    public void saveUserInfoInWeb(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        userAreaI.delete(userBean);
        for(int i=0;userBean.getArea()!=null && i<userBean.getArea().size();i++){
            userAreaI.addUserArea(new UserAreaBean(userBean.getId(),userBean.getArea().get(i).getId()));
        }
        userI.updateRemark(userBean);
        contactI.deleteContactsByUserId(userBean);
        for(int i=0;i<userBean.getContacts().size();i++){
            ContactBean contactBean = new ContactBean();
            contactBean.setFromid(userBean.getId());
            contactBean.setToid(userBean.getContacts().get(i).getId());
            contactI.addContactsByUserid(contactBean);
        }
        VideoControl.printOut(rep,new BaseResBean());
    }




    @RequestMapping(value = "/getWebIndexInfo",method = RequestMethod.POST)
    public void getWebIndexInfo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);

        WebIndexInfo webIndexInfo = new WebIndexInfo();
        UserBean u1 = new UserBean();
        u1.setUsertype(1);
        UserBean userBean = new UserBean();
        userBean.setUsertype(0);
        UserBean u2 = new UserBean();
        u2.setUsertype(2);

        int engnum = 0;
        int sernum = 0;
        BaseResBean res = HttpRequest.sendGet(Global.URL+"/chatrooms?pagenum=1&pagesize=1",null,Global.emTokenBean.getAccess_token());
        ChatRoomBean chatRoomBean = GsonUtil.getInstance().fromJson(res.getData().toString(),ChatRoomBean.class);
        if(chatRoomBean!=null && chatRoomBean.getData()!=null && chatRoomBean.getData().size()>0){
            BaseResBean r = HttpRequest.sendGet(Global.URL+"/chatrooms/"+chatRoomBean.getData().get(0).getId(), null,Global.emTokenBean.getAccess_token());
            ChatRoomBean c = GsonUtil.getInstance().fromJson(r.getData().toString(),ChatRoomBean.class);
            if(c!=null && c.getData()!=null && c.getData().size()>0){
                for(int i=0;i<c.getData().size();i++){
                    if(c.getData().get(i).getName().equals(Global.ChatRoomName) && c.getData().get(i).getAffiliations()!=null && c.getData().get(i).getAffiliations().size()>0){
                        for(int j=0;j<c.getData().get(i).getAffiliations().size();j++){
                            if(c.getData().get(i).getAffiliations().get(j).getMember()!=null &&!"".equals(c.getData().get(i).getAffiliations().get(j).getMember())){
                                UserBean u = new UserBean();
                                u.setPhone(c.getData().get(i).getAffiliations().get(j).getMember());
                                UserBean uu = (UserBean) userI.getUserShortInfoByPhone(u).getData();
                                if(uu!=null){
                                    switch (uu.getUsertype()){
                                        case UserBean.USER_TYPE_ENGINEER:
                                            engnum++;
                                            break;
                                        case UserBean.USER_TYPE_SERVER:
                                            sernum++;
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        webIndexInfo.setEngineerline(engnum);
        webIndexInfo.setServeronline(sernum);


        webIndexInfo.setCustomall((Long) userI.getUserNums(u2).getData());
        webIndexInfo.setServerall((Long) userI.getUserNums(userBean).getData());
        webIndexInfo.setEngineerall((Long) userI.getUserNums(u1).getData());
        webIndexInfo.setUserall((Long) userI.getUserNum().getData());
        webIndexInfo.setChattimes((Long) videoI.getCallTimeInfo().getData());
        webIndexInfo.setVideonum((Long) videoI.getCallNum().getData());
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(webIndexInfo);

        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/initUserChatTime",method = RequestMethod.POST)
    public void initUserChatTime(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);

        ArrayList<UserBean> users = (ArrayList<UserBean>) userI.getUserList().getData();
        for(int i=0;i<users.size();i++){
            if(users.get(i)!=null &&users.get(i).getId()!=-1 ){
                UserBean user = new UserBean();
                user.setId(users.get(i).getId());
                long t=(Long) videoI.getUserCallTimeInfoById(user).getData();
                user.setChattime(t);
                userI.updateUserChatTimeById(user);
            }
        }
        VideoControl.printOut(rep,new BaseResBean());
    }


    @RequestMapping(value = "/getUsersChatTime",method = RequestMethod.POST)
    public void getUsersChatTime(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);

        ArrayList<UserBean> users = (ArrayList<UserBean>) userI.getShortUserList().getData();
        for(int i=0;i<users.size();i++){
            if(users.get(i)!=null &&users.get(i).getId()!=-1 ){
                users.get(i).setStart(userBean.getStart());
                users.get(i).setEnd(userBean.getEnd());
                long t=(Long) videoI.getUserCallTimeInfoByIdWithTimeLimit(users.get(i)).getData();
                users.get(i).setChattime(t);
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(users);
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/initUserRate",method = RequestMethod.POST)
    public void initUserRate(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);

        ArrayList<UserBean> users = (ArrayList<UserBean>) userI.getShortUserList().getData();
        for(int i=0;i<users.size();i++){
            if(users.get(i)!=null &&users.get(i).getId()!=-1 ){
                UserBean user = new UserBean();
                user.setId(users.get(i).getId());
                float t=(Float) commentI.getVideoRateCommentByUseId(user).getData();
                user.setRate(t);
                userI.updateRateByUserId(user);
            }
        }
        VideoControl.printOut(rep,new BaseResBean());
    }



    @RequestMapping(value = "/getUsersRate",method = RequestMethod.POST)
    public void getUsersRate(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);

        ArrayList<UserBean> users = (ArrayList<UserBean>) userI.getShortUserList().getData();
        for(int i=0;i<users.size();i++){
            if(users.get(i)!=null &&users.get(i).getId()!=-1 ){
                users.get(i).setStart(userBean.getStart());
                users.get(i).setEnd(userBean.getEnd());
                float t=(Float) commentI.getVideoRateCommentByUseIdWithTimeLimit(users.get(i)).getData();
                users.get(i).setRate(t);
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(users);

        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getOutCallTimeDistribution",method = RequestMethod.POST)
    public void getOutCallTimeDistribution(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<UserBean> fromid = (ArrayList<UserBean>) videoI.getOutCallTimeDistribution().getData();
        int customer = 0;
        int server = 0;
        int engineer = 0;
        for(int i=0;i<fromid.size();i++){
            switch (fromid.get(i).getUsertype()){
                case UserBean.USER_TYPE_CUSTOMER:
                    customer++;
                    break;
                case UserBean.USER_TYPE_SERVER:
                    server++;
                    break;
                case UserBean.USER_TYPE_ENGINEER:
                    engineer++;
                    break;
            }
        }
        CallDistribution callDistribution = new CallDistribution();
        callDistribution.setName("工程师");
        callDistribution.setContent(engineer+"");

        CallDistribution callDistribution1 = new CallDistribution();
        callDistribution1.setName("客户");
        callDistribution1.setContent(customer+"");

        CallDistribution callDistribution2 = new CallDistribution();
        callDistribution2.setName("客服");
        callDistribution2.setContent(server+"");

        ArrayList<CallDistribution> data = new ArrayList<CallDistribution>();
        data.add(callDistribution);
        data.add(callDistribution1);
        data.add(callDistribution2);


        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(data);

        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getInCallTimeDistribution",method = RequestMethod.POST)
    public void getInCallTimeDistribution(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<UserBean> fromid = (ArrayList<UserBean>) videoI.getInCallTimeDistribution().getData();
        int customer = 0;
        int server = 0;
        int engineer = 0;
        for(int i=0;i<fromid.size();i++){
            switch (fromid.get(i).getUsertype()){
                case UserBean.USER_TYPE_CUSTOMER:
                    customer++;
                    break;
                case UserBean.USER_TYPE_SERVER:
                    server++;
                    break;
                case UserBean.USER_TYPE_ENGINEER:
                    engineer++;
                    break;
            }
        }
        CallDistribution callDistribution = new CallDistribution();
        callDistribution.setName("工程师");
        callDistribution.setContent(engineer+"");

        CallDistribution callDistribution1 = new CallDistribution();
        callDistribution1.setName("客户");
        callDistribution1.setContent(customer+"");

        CallDistribution callDistribution2 = new CallDistribution();
        callDistribution2.setName("客服");
        callDistribution2.setContent(server+"");

        ArrayList<CallDistribution> data = new ArrayList<CallDistribution>();
        data.add(callDistribution);
        data.add(callDistribution1);
        data.add(callDistribution2);


        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(data);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
