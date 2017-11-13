package com.summer.control;

import com.summer.base.bean.BaseResBean;
import com.summer.share.ShareBean;
import com.summer.share.ShareOpe;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean;
import com.summer.videocomment.VideoCommentBean;
import com.summer.videocomment.VideoCommentOpe;
import com.summer.videotip.VideoTipBean;
import com.summer.videotip.VideoTipOpe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SWSD on 17-09-04.
 */
@Controller
@RequestMapping("/share")
public class ShareControl {

    ShareOpe shareI = new ShareOpe();

    VideoCommentOpe videoCommentI = new VideoCommentOpe();

    VideoTipOpe videoTipI = new VideoTipOpe();

    @RequestMapping(value = "/getSharesByReceipt",method = RequestMethod.POST)
    public void getSharesByReceipt(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ShareBean shareBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),ShareBean.class);
        VideoControl.printOut(rep,shareI.getSharesByReceipt(shareBean));
    }


    @RequestMapping(value = "/getSharesByReceiptWithLimit",method = RequestMethod.POST)
    public void getSharesByReceiptWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ShareBean shareBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),ShareBean.class);
        HashMap<Integer,VideoTipBean> data  = (HashMap<Integer, VideoTipBean>) videoTipI.getAllVideoTipsMap().getData();
        ArrayList<VideoBean> videos = (ArrayList<VideoBean>) shareI.getSharesByReceiptWithLimit(shareBean).getData();
        for(int i=0;videos!=null && i<videos.size();i++){
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
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getShareNumByUserPhone",method = RequestMethod.POST)
    public void getShareNumByUserPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,shareI.getShareNumByUserPhone(userBean));
    }




    @RequestMapping(value = "/share",method = RequestMethod.POST)
    public void share(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ShareBean shareBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),ShareBean.class);
        VideoControl.printOut(rep,shareI.share(shareBean));
    }
}
