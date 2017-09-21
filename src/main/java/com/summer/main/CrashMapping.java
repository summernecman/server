package com.summer.main;

import com.summer.crash.CrashBean;
import com.summer.crash.CrashI;
import com.summer.crash.CrashOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-09-04.
 */
@Controller
@RequestMapping("/crash")
public class CrashMapping {

    CrashI crashI  =new CrashOpe();

    @RequestMapping(value = "/sendCrash",method = RequestMethod.POST)
    public void getCollectionVideosByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        CrashBean crashBean  = GsonUtil.getInstance().fromJson(str,CrashBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(crashI.sendCrash(crashBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
