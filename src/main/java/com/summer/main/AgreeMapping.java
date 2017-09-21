package com.summer.main;

import com.summer.agree.AgreeBean;
import com.summer.agree.AgreeI;
import com.summer.agree.AgreeNumBean;
import com.summer.agree.AgreeOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-09-12.
 */
@Controller
@RequestMapping("/agree")
public class AgreeMapping {

    AgreeI agreeI = new AgreeOpe();

    @RequestMapping(value = "/addAgree",method = RequestMethod.POST)
    public void addAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(str,AgreeBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(agreeI.addAgree(agreeBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/cancleAgree",method = RequestMethod.POST)
    public void cancleAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(str,AgreeBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(agreeI.cancleAgree(agreeBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getAgreeNum",method = RequestMethod.POST)
    public void getAgreeNum(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(str,AgreeBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(agreeI.getAgreeNum(agreeBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/isAgreeNum",method = RequestMethod.POST)
    public void isAgreeNum(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(str,AgreeBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(agreeI.isAgreeNum(agreeBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @RequestMapping(value = "/clickAgree",method = RequestMethod.POST)
    public void clickAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(str,AgreeBean.class);
        System.out.println(str);
        BaseResBean baseResBean = new BaseResBean();
        AgreeNumBean agreeNumBean = new AgreeNumBean();
        if((Boolean) (agreeI.isAgreeNum(agreeBean).getData())){
            agreeI.cancleAgree(agreeBean);
            baseResBean.setData(false);
            agreeNumBean.setAgree(false);
        }else{
            agreeI.addAgree(agreeBean);
            agreeNumBean.setAgree(true);
        }
        agreeNumBean.setAgreenum((Integer) (agreeI.getAgreeNum(agreeBean).getData()));
        baseResBean.setData(agreeNumBean);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
