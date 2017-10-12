package com.summer.main;

import com.summer.share.ShareBean;
import com.summer.tip.TipI;
import com.summer.tip.TipOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-10-12.
 */
@Controller
@RequestMapping(value = "/tip")
public class TipMapping {

    TipOpe tipI = new TipOpe();

    @RequestMapping(value = "/gettips",method = RequestMethod.POST)
    public void getSharesByReceipt(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(tipI.getTips()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
