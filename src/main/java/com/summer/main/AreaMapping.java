package com.summer.main;

import com.google.gson.reflect.TypeToken;
import com.summer.agree.AgreeBean;
import com.summer.area.AreaBean;
import com.summer.area.AreaI;
import com.summer.area.AreaOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-29.
 */
@Controller
@RequestMapping(value = "/area")
public class AreaMapping {

    AreaOpe areaI = new AreaOpe();

    @RequestMapping(value = "/getareas",method = RequestMethod.POST)
    public void getareas(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(areaI.getArea()));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addArea",method = RequestMethod.POST)
    public void addArea(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        AreaBean areaBean = GsonUtil.getInstance().fromJson(str,AreaBean.class);
        System.out.println(str);
        if((Boolean) (areaI.isAreaExist(areaBean).getData())){
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setException(true);
            baseResBean.setErrorMessage("名称已经存在");
            try {
                PrintWriter printWriter = rep.getWriter();
                printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(areaI.addArea(areaBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addAreas",method = RequestMethod.POST)
    public void addAreas(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        ArrayList<AreaBean> areaBeans = GsonUtil.getInstance().fromJson(str,new TypeToken<ArrayList<AreaBean>>(){}.getType());
        System.out.println(str);
        for(int i=0;i<areaBeans.size();i++){
            if((Boolean) (areaI.isAreaExist(areaBeans.get(i)).getData())){
                continue;
            }
            areaI.addArea(areaBeans.get(i));
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(areaBeans);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
