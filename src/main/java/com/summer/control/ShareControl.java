package com.summer.control;

import com.summer.share.ShareBean;
import com.summer.share.ShareOpe;
import com.summer.user.bean.UserBean;
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
@RequestMapping("/share")
public class ShareControl {

    ShareOpe shareI = new ShareOpe();

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
        VideoControl.printOut(rep,shareI.getSharesByReceiptWithLimit(shareBean));
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
