package com.summer.main;

import com.summer.crash.CrashBean;
import com.summer.feedback.FeedBackBean;
import com.summer.feedback.FeedBackI;
import com.summer.feedback.FeedBackOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-09-27.
 */
@Controller
@RequestMapping(value = "/feedback")
public class FeedBackMapping {

    FeedBackOpe feedBackI = new FeedBackOpe();


    @RequestMapping(value = "/sendfeedback",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        FeedBackBean crashBean  = GsonUtil.getInstance().fromJson(str,FeedBackBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(feedBackI.sendFeedBack(crashBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
