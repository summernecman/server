package com.summer.control;

import com.summer.contact.ContactBean;
import com.summer.contact.ContactOpe;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SWSD on 17-09-26.
 */
@Controller
@RequestMapping("/contact")
public class ContactControl {

    ContactOpe contactOpe = new ContactOpe();

    @RequestMapping(value = "/addContactsByUserid",method = RequestMethod.POST)
    public void addContactsByUserid(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        ContactBean contactBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),ContactBean.class);
        VideoControl.printOut(rep,contactOpe.addContactsByUserid(contactBean));
    }


}
