package com.summer.control;

import com.summer.user.UserI;
import com.summer.user.UserOpe;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean;
import com.summer.videodetail.VideoDetailBean;
import com.summer.videodetail.VideoDetailI;
import com.summer.videodetail.VideoDetailOpe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 2017-11-06.
 */
@Controller
@RequestMapping("/videodetail")
public class VideoDetailController {

    VideoDetailOpe videoDetailI = new VideoDetailOpe();

    UserOpe userI = new UserOpe();

    @RequestMapping(value = "/insertvideo" ,method = RequestMethod.POST)
    public void insertvideo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoDetailBean v = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoDetailBean.class);
        VideoControl.printOut(rep,videoDetailI.insertVideo(v));
    }

    @RequestMapping(value = "/updateUpload" ,method = RequestMethod.POST)
    public void updateUpload(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoDetailBean v = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoDetailBean.class);
        VideoControl.printOut(rep,videoDetailI.updateUpload(v));
    }

    @RequestMapping(value = "/getCommentToType" ,method = RequestMethod.POST)
    public void getCommentToType(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoDetailBean v = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoDetailBean.class);
        int id = (Integer) videoDetailI.getCommentOtherId(v).getData();
        UserBean u = new UserBean();
        u.setId(id);
        VideoControl.printOut(rep,userI.getUserTypeInfoById(u));
    }

}
