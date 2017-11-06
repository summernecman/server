package com.summer.control;

import com.summer.area.AreaOpe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SWSD on 17-09-29.
 */
@Controller
@RequestMapping(value = "/userarea")
public class UserAreaControl {

    AreaOpe areaI = new AreaOpe();

}
