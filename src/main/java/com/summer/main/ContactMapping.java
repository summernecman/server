package com.summer.main;

import com.summer.contact.ContactBean;
import com.summer.contact.ContactOpe;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SWSD on 17-09-26.
 */
@Controller
@RequestMapping("/contact")
public class ContactMapping {

    ContactOpe contactOpe = new ContactOpe();

    @RequestMapping(value = "/addContactsByUserid",method = RequestMethod.POST)
    public void addContactsByUserid(HttpServletRequest req, HttpServletResponse rep){
        VideoMapping.init(req,rep);
        String  str = req.getParameter("data");
        ContactBean contactBean = GsonUtil.getInstance().fromJson(str,ContactBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(contactOpe.addContactsByUserid(contactBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
