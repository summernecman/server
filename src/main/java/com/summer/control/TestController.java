package com.summer.control;

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
@RequestMapping("/test")
public class TestController{

    @RequestMapping(value = "/{path}",method = RequestMethod.POST)
    public void addAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println("12333");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public void testss(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println("fdsafdsaf");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
