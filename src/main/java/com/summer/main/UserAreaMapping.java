package com.summer.main;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-29.
 */
@Controller
@RequestMapping(value = "/userarea")
public class UserAreaMapping {

    AreaOpe areaI = new AreaOpe();

}
