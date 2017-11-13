package com.summer.control;

import com.summer.agree.AgreeBean;
import com.summer.agree.AgreeOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentOpe;
import com.summer.comment.bean.TipBean;
import com.summer.comment.bean.TipsBean;
import com.summer.contact.ContactBean;
import com.summer.tip.TipOpe;
import com.summer.user.UserOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.DateFormatUtil;
import com.summer.util.GsonUtil;
import com.summer.video.VideoOpe;
import com.summer.video.bean.LimitBean;
import com.summer.video.bean.VideoBean;
import com.summer.video.bean.VideoBeseResBean;
import com.summer.videocomment.VideoCommentBean;
import com.summer.videocomment.VideoCommentI;
import com.summer.videocomment.VideoCommentOpe;
import com.summer.videotip.VideoTipBean;
import com.summer.videotip.VideoTipOpe;
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
import java.util.HashMap;

/**
 * Created by SWSD on 2017-07-19.
 */
@Controller
@RequestMapping("/server")
public class VideoControl {

    UserOpe userI = new UserOpe();

    VideoOpe videoI = new VideoOpe();

    CommentOpe commentI = new CommentOpe();

    AgreeOpe agreeI = new AgreeOpe();

    TipOpe tipI = new TipOpe();

    VideoTipOpe videoTipI = new VideoTipOpe();

    VideoCommentOpe videoCommentI = new VideoCommentOpe();


    @RequestMapping(value = "/userlist",method = RequestMethod.POST)
    public void hello(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        String  str = req.getParameter("data");
        printOut(rep,userI.getUserList());
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        BaseResBean b = userI.login(userBean);
        b.setOther(GsonUtil.getInstance().toJson(videoTipI.getAllVideoTipsMap().getData()));
        printOut(rep,b);
    }



    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public void loginOut(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,userI.logout(userBean));
    }


    @RequestMapping(value = "/getLoginInfo",method = RequestMethod.POST)
    public void getLoginInfo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,userI.getLoginInfo(userBean));
    }


    @RequestMapping(value = "/getVideos",method = RequestMethod.POST)
    public void getVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getVideos(userBean));
    }


    @RequestMapping(value = "/getHistoryVideos",method = RequestMethod.POST)
    public void getHistoryVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getVideosByUserPhone(userBean));
    }

    @RequestMapping(value = "/addVideo",method = RequestMethod.POST)
    public void addVideo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.addVideo(videoBean));
    }



    @RequestMapping(value = "/getVideoByName",method = RequestMethod.POST)
    public void getVideoByName(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.getVideoByName(videoBean));
    }

    @RequestMapping(value = "/getMyContactsById",method = RequestMethod.POST)
    public void getMyContactsById(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean u = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getByContacts(u));
    }


    @RequestMapping(value = "/getVideosByBothUserId",method = RequestMethod.POST)
    public void getVideosByBothUserId(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        ContactBean c = GsonUtil.getInstance().fromJson(req.getParameter("data"),ContactBean.class);
        printOut(rep,videoI.getVideosByBothUserId(c));
    }

    @RequestMapping(value = "/getVideosByBothUserIdWithLimit",method = RequestMethod.POST)
    public void getVideosByBothUserIdWithLimit(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        ContactBean c = GsonUtil.getInstance().fromJson(req.getParameter("data"),ContactBean.class);
        HashMap<Integer,VideoTipBean> data  = (HashMap<Integer, VideoTipBean>) videoTipI.getAllVideoTipsMap().getData();
        ArrayList<VideoBean> videos = (ArrayList<VideoBean>) videoI.getVideosByBothUserIdWithLimit(c).getData();
        for(int i=0;i<videos.size();i++){
            ArrayList<VideoCommentBean> videoCommentBeen = (ArrayList<VideoCommentBean>) videoCommentI.getVideoCommentByCallId(videos.get(i)).getData();
            videos.get(i).setVideoCommentBeans(videoCommentBeen);
            for(int j=0;j<videoCommentBeen.size();j++){
                VideoTipBean vvvv = data.get(videoCommentBeen.get(j).getType());
                if(vvvv!=null){
                    videos.get(i).setVideotips(vvvv.getTxt()+","+videos.get(i).getVideotips());
                }
            }
            if(videos.get(i).getVideotips().length()>0){
                videos.get(i).setVideotips(videos.get(i).getVideotips().substring(0,videos.get(i).getVideotips().length()-1));
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(videos);
        printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/getVideosByBothUserIdWithLimitAndSeach",method = RequestMethod.POST)
    public void getVideosByBothUserIdWithLimitAndSeach(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        ContactBean c = GsonUtil.getInstance().fromJson(req.getParameter("data"),ContactBean.class);
        HashMap<Integer,VideoTipBean> data  = (HashMap<Integer, VideoTipBean>) videoTipI.getAllVideoTipsMap().getData();
        ArrayList<VideoBean> videos = (ArrayList<VideoBean>) videoI.getVideosByBothUserIdWithLimitAndSeach(c).getData();
        for(int i=0;i<videos.size();i++){
            ArrayList<VideoCommentBean> videoCommentBeen = (ArrayList<VideoCommentBean>) videoCommentI.getVideoCommentByCallId(videos.get(i)).getData();
            videos.get(i).setVideoCommentBeans(videoCommentBeen);
            for(int j=0;j<videoCommentBeen.size();j++){
                VideoTipBean vvvv = data.get(videoCommentBeen.get(j).getType());
                if(vvvv!=null){
                    videos.get(i).setVideotips(vvvv.getTxt()+","+videos.get(i).getVideotips());
                }
            }
            if(videos.get(i).getVideotips().length()>0){
                videos.get(i).setVideotips(videos.get(i).getVideotips().substring(0,videos.get(i).getVideotips().length()-1));
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(videos);
        printOut(rep,baseResBean);
    }




    @RequestMapping(value = "/getVideosByContacts",method = RequestMethod.POST)
    public void getVideosByContacts(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getVideosByContacts(userBean));
    }


    @RequestMapping(value = "/getVideosByContacts2",method = RequestMethod.POST)
    public void getVideosByContacts2(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getVideosByContacts2(userBean));
    }

    @RequestMapping(value = "/commentVideos",method = RequestMethod.POST)
    public void commentVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        CommentBean commentBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CommentBean.class);
        videoI.commentVideos(commentBean);
        UserBean a = new UserBean();
        a.setId(commentBean.getToid());
        UserBean u = (UserBean) userI.getUserInfoById(a).getData();
        int num = (Integer) commentI.getToVideoCommentNumByUserId(u).getData();
        u.setRate((commentBean.getRate()+u.getRate()*num)/(num+1));
        userI.updateRateByUserId(u);
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(u);

        printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getAllVideos",method = RequestMethod.POST)
    public void getAllVideos(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        printOut(rep,videoI.getAllVideos());
    }


    @RequestMapping(value = "/getAllVideosWithLimit",method = RequestMethod.POST)
    public void getAllVideosWithLimit(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        LimitBean limitBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),LimitBean.class);
        VideoBeseResBean videoBeseResBean = (VideoBeseResBean) videoI.getAllVideosWithLimit(limitBean);
        videoBeseResBean.setTotal((Integer) videoI.getAllVideosCount().getData());
        printOut(rep,videoBeseResBean);
    }



    @RequestMapping(value = "/getVideoCommentsById",method = RequestMethod.POST)
    public void getVideoCommentsById(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getVideoCommentsByVideoId(videoBean).getData();
        for(int i=0;comments!=null && i<comments.size();i++){
            AgreeBean agree = new AgreeBean();
            agree.setCommentid(comments.get(i).getId());
            comments.get(i).setAgreeNum(((Integer)agreeI.getAgreeNum(agree).getData()));
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(comments);
        printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getEachOtherVideoCommentsById",method = RequestMethod.POST)
    public void getEachOtherVideoCommentsById(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getVideoCommentsByVideoId(videoBean).getData();
        for(int i=0;comments!=null && i<comments.size();i++){
            AgreeBean agree = new AgreeBean();
            agree.setCommentid(comments.get(i).getId());
            comments.get(i).setAgreeNum(((Integer)agreeI.getAgreeNum(agree).getData()));
        }

        BaseResBean baseResBean = new BaseResBean();

        if(comments.size()==0){
            ArrayList<VideoBean> a = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(a==null || a.size()==0){
                baseResBean.setException(true);
                baseResBean.setErrorMessage("找不到视频");
                baseResBean.setData(comments);
                printOut(rep,baseResBean);
                return;
            }
            VideoBean v = a.get(0);
            CommentBean c1 = new CommentBean();
            TipsBean tipsBean = new TipsBean();
            ArrayList<TipBean> tipBeen = (ArrayList<TipBean>) tipI.getTips().getData();
            tipsBean.setTipBeen(tipBeen);
            c1.setTips(GsonUtil.getInstance().toJson(tipsBean));
            c1.init();
            c1.setFromid(v.getFromid());
            c1.setFromuser(v.getFromphone());
            c1.setToid(v.getToid());
            c1.setTouser(v.getTophone());
            CommentBean c2 = new CommentBean();
            c2.setTips(GsonUtil.getInstance().toJson(tipsBean));
            c2.init();
            c2.setFromid(v.getToid());
            c2.setFromuser(v.getTophone());
            c2.setToid(v.getFromid());
            c2.setTouser(v.getFromphone());
            comments.add(c1);
            comments.add(c2);
        }

        if(comments.size()==1){
            ArrayList<VideoBean> a = (ArrayList<VideoBean>) videoI.getVideoByVideoId(videoBean).getData();
            if(a==null || a.size()==0){
                baseResBean.setException(true);
                baseResBean.setErrorMessage("找不到视频");
                baseResBean.setData(comments);
                printOut(rep,baseResBean);
                return;
            }
            VideoBean v = a.get(0);

            if(comments.get(0).getFromid()==v.getFromid()){
                CommentBean c2 = new CommentBean();
                TipsBean tipsBean = new TipsBean();
                ArrayList<TipBean> tipBeen = (ArrayList<TipBean>) tipI.getTips().getData();
                tipsBean.setTipBeen(tipBeen);
                c2.setTips(GsonUtil.getInstance().toJson(tipsBean));
                c2.init();
                c2.setFromid(v.getToid());
                c2.setFromuser(v.getTophone());
                c2.setToid(v.getFromid());
                c2.setTouser(v.getFromphone());
                comments.add(c2);
            }else{
                CommentBean c1 = new CommentBean();
                TipsBean tipsBean = new TipsBean();
                ArrayList<TipBean> tipBeen = (ArrayList<TipBean>) tipI.getTips().getData();
                tipsBean.setTipBeen(tipBeen);
                c1.setTips(GsonUtil.getInstance().toJson(tipsBean));
                c1.init();
                c1.setFromid(v.getFromid());
                c1.setFromuser(v.getFromphone());
                c1.setToid(v.getToid());
                c1.setTouser(v.getTophone());
                comments.add(c1);
            }

        }
        baseResBean.setData(comments);
        printOut(rep,baseResBean);
    }



    @RequestMapping(value = "/getAllVideosWithLimitFrom1",method = RequestMethod.POST)
    public void getAllVideosWithLimitFrom1(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        LimitBean limitBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),LimitBean.class);
        limitBean.setPagestart(limitBean.getPagestart()-1);
        VideoBeseResBean videoBeseResBean = (VideoBeseResBean) videoI.getAllVideosWithLimit(limitBean);
        videoBeseResBean.setTotal((Integer) videoI.getAllVideosCount().getData());
        printOut(rep,videoBeseResBean);
    }

    @RequestMapping(value = "/getAllVideosByGet",method = RequestMethod.GET)
    public void getAllVideosByGet(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        printOut(rep,videoI.getAllVideos());
    }

    @RequestMapping(value = "/isVideoUploaded",method = RequestMethod.GET)
    public void isVideoUploaded(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.isVideoUploaded(videoBean));
    }

    @RequestMapping(value = "/setVideoUploaded",method = RequestMethod.POST)
    public void setVideoUploaded(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.setVideoUploaded(videoBean));
    }






    @RequestMapping(value = "/uploadvideo",method = RequestMethod.POST)
    public void addFiles(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
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

                File typeFile = new File(parent, DateFormatUtil.getNowStr(DateFormatUtil.YYYYMMDD));
                if(!typeFile.exists()){
                    typeFile.mkdirs();
                }
                File file = new File(typeFile,list.get(i).getName());

                System.out.println(file.getPath());
                files.add(file.getPath());
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
        printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/getMaxVideoId",method = RequestMethod.POST)
    public void getMaxVideoId(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        printOut(rep,videoI.getMaxVideoId());
    }

    @RequestMapping(value = "/insert_and_getid_fromvieo",method = RequestMethod.POST)
    public void insert_and_getid_fromvieo(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.insert_and_getid_fromvieo(videoBean));
    }


    @RequestMapping(value = "/updateVideoById",method = RequestMethod.POST)
    public void updateVideoById(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        if(videoBean.isfrom()){
            videoBean.setFile("from"+videoBean.getFile());
        }else{
            videoBean.setFile("to"+videoBean.getFile());
        }
        String file = (String) videoI.getVideoNameById(videoBean).getData();
        videoBean.setFile(videoBean.getFile()+"&&&"+file);
        printOut(rep,videoI.updateVideoById(videoBean));
    }

    @RequestMapping(value = "/getUnUploadVideoNum",method = RequestMethod.POST)
    public void getUnUploadVideoNum(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        UserBean u = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        printOut(rep,videoI.getUnUploadVideoNum(u));
    }

    @RequestMapping(value = "/updateCallState",method = RequestMethod.POST)
    public void updateCallState(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean u = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.updateCallState(u));
    }


    @RequestMapping(value = "/updateVideoCallTimeNum",method = RequestMethod.POST)
    public void updateVideoCallTimeNum(HttpServletRequest req, HttpServletResponse rep){
        init(req,rep);
        VideoBean u = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        printOut(rep,videoI.updateVideoCallTimeNum(u));
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

    public static void printOut(HttpServletResponse rep,Object o){
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(o));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
