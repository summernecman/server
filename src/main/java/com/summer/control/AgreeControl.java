package com.summer.control;

import com.summer.agree.AgreeBean;
import com.summer.agree.AgreeNumBean;
import com.summer.agree.AgreeOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-09-12.
 */
@Controller
@RequestMapping("/agree")
public class AgreeControl {

    AgreeOpe agreeI = new AgreeOpe();

    @RequestMapping(value = "/addAgree",method = RequestMethod.POST)
    public void addAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AgreeBean.class);
        VideoControl.printOut(rep,agreeI.addAgree(agreeBean));
    }

    @RequestMapping(value = "/cancleAgree",method = RequestMethod.POST)
    public void cancleAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AgreeBean.class);
        VideoControl.printOut(rep,agreeI.cancleAgree(agreeBean));
    }

    @RequestMapping(value = "/getAgreeNum",method = RequestMethod.POST)
    public void getAgreeNum(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AgreeBean.class);
        VideoControl.printOut(rep,agreeI.getAgreeNum(agreeBean));
    }

    @RequestMapping(value = "/isAgreeNum",method = RequestMethod.POST)
    public void isAgreeNum(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AgreeBean.class);
        VideoControl.printOut(rep,agreeI.isAgreeNum(agreeBean));
    }




    @RequestMapping(value = "/clickAgree",method = RequestMethod.POST)
    public void clickAgree(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AgreeBean agreeBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AgreeBean.class);
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
        VideoControl.printOut(rep,baseResBean);
    }

}
