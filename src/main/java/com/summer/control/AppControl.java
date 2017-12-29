package com.summer.control;

import com.summer.agree.AgreeBean;
import com.summer.agree.AgreeNumBean;
import com.summer.agree.AgreeOpe;
import com.summer.app.AppBean;
import com.summer.app.AppOpe;
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
@RequestMapping("/app")
public class AppControl {

    AppOpe appI = new AppOpe();

    @RequestMapping(value = "/getAppVersion",method = RequestMethod.POST)
    public void getAppVersion(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoControl.printOut(rep,appI.CheckVersion());
    }


    @RequestMapping(value = "/updateVersion",method = RequestMethod.POST)
    public void updateVersion(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AppBean appBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AppBean.class);
        VideoControl.printOut(rep,appI.updateVersion(appBean));
    }

}
