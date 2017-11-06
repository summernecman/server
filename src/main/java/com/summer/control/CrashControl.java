package com.summer.control;

import com.summer.crash.CrashBean;
import com.summer.crash.CrashOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-09-04.
 */
@Controller
@RequestMapping("/crash")
public class CrashControl {

    CrashOpe crashI  =new CrashOpe();

    @RequestMapping(value = "/sendCrash",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CrashBean crashBean  = GsonUtil.getInstance().fromJson(req.getParameter("data"),CrashBean.class);
        VideoControl.printOut(rep,crashI.sendCrash(crashBean));
    }
}
