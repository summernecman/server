package com.summer.control;

import com.summer.tip.TipOpe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-10-12.
 */
@Controller
@RequestMapping(value = "/tip")
public class TipControl {

    TipOpe tipI = new TipOpe();

    @RequestMapping(value = "/gettips",method = RequestMethod.POST)
    public void getSharesByReceipt(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoControl.printOut(rep,tipI.getTips());
    }

}
