package com.summer.control;

import com.summer.util.GsonUtil;
import com.summer.videocomment.VideoCommentBean;
import com.summer.videocomment.VideoCommentOpe;
import com.summer.videodetail.VideoDetailBean;
import com.summer.videodetail.VideoDetailOpe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 2017-11-06.
 */
@Controller
@RequestMapping("/videocomment")
public class VideoCommentController {

    VideoCommentOpe videoCommentI = new VideoCommentOpe();

    @RequestMapping(value = "/insertvideo" ,method = RequestMethod.POST)
    public void insertvideo(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoCommentBean v = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoCommentBean.class);
        VideoControl.printOut(rep,videoCommentI.addVideoComment(v));
    }


}
