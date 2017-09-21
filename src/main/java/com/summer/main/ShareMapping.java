package com.summer.main;

import com.summer.share.ShareBean;
import com.summer.share.ShareI;
import com.summer.share.ShareOpe;
import com.summer.user.bean.UserBean;
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
@RequestMapping("/share")
public class ShareMapping {

    ShareI shareI = new ShareOpe();

    @RequestMapping(value = "/getSharesByReceipt",method = RequestMethod.POST)
    public void getSharesByReceipt(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        ShareBean shareBean = GsonUtil.getInstance().fromJson(str,ShareBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(shareI.getSharesByReceipt(shareBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getShareNumByUserPhone",method = RequestMethod.POST)
    public void getShareNumByUserPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(shareI.getShareNumByUserPhone(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @RequestMapping(value = "/share",method = RequestMethod.POST)
    public void share(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        ShareBean shareBean = GsonUtil.getInstance().fromJson(str,ShareBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(shareI.share(shareBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
