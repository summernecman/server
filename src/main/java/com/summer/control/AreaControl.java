package com.summer.control;

import com.google.gson.reflect.TypeToken;
import com.summer.area.AreaBean;
import com.summer.area.AreaOpe;
import com.summer.base.bean.BaseResBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-29.
 */
@Controller
@RequestMapping(value = "/area")
public class AreaControl {

    AreaOpe areaI = new AreaOpe();

    @RequestMapping(value = "/getareas",method = RequestMethod.POST)
    public void getareas(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoControl.printOut(rep,areaI.getArea());
    }

    @RequestMapping(value = "/addArea",method = RequestMethod.POST)
    public void addArea(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        AreaBean areaBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),AreaBean.class);
        if((Boolean) (areaI.isAreaExist(areaBean).getData())){
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setException(true);
            baseResBean.setErrorMessage("名称已经存在");
            VideoControl.printOut(rep,baseResBean);
            return;
        }
        VideoControl.printOut(rep,areaI.addArea(areaBean));
    }

    @RequestMapping(value = "/addAreas",method = RequestMethod.POST)
    public void addAreas(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ArrayList<AreaBean> areaBeans = GsonUtil.getInstance().fromJson(req.getParameter("data"),new TypeToken<ArrayList<AreaBean>>(){}.getType());
        for(int i=0;i<areaBeans.size();i++){
            if((Boolean) (areaI.isAreaExist(areaBeans.get(i)).getData())){
                continue;
            }
            areaI.addArea(areaBeans.get(i));
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(areaBeans);
        VideoControl.printOut(rep,baseResBean);
    }
}
