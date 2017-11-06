package com.summer.control;

import com.summer.feedback.FeedBackBean;
import com.summer.feedback.FeedBackOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-09-27.
 */
@Controller
@RequestMapping(value = "/feedback")
public class FeedBackControl {

    FeedBackOpe feedBackI = new FeedBackOpe();


    @RequestMapping(value = "/sendfeedback",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        FeedBackBean crashBean  = GsonUtil.getInstance().fromJson(req.getParameter("data"),FeedBackBean.class);
        VideoControl.printOut(rep,feedBackI.sendFeedBack(crashBean));
    }

}
