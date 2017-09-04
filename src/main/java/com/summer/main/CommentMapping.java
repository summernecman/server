package com.summer.main;

import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentI;
import com.summer.comment.CommentOpe;
import com.summer.comment.bean.TipBean;
import com.summer.comment.bean.TipsBean;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.VideoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SWSD on 17-08-29.
 */
@Controller
@RequestMapping("/comment")
public class CommentMapping {

    CommentI commentI = new CommentOpe();

    @RequestMapping(value = "/getCommentByUserName",method = RequestMethod.POST)
    public void getCommentByUserName(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getCommentByUserName(userBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getVideoCommentByVideoName",method = RequestMethod.POST)
    public void getVideoCommentByVideoName(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        VideoBean videoBean = GsonUtil.getInstance().fromJson(str,VideoBean.class);
        System.out.println(str);
        try {
            PrintWriter printWriter = rep.getWriter();
            printWriter.println(GsonUtil.getInstance().toJson(commentI.getVideoCommentByVideoName(videoBean)));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getUserTips",method = RequestMethod.POST)
    public void getUserTips(HttpServletRequest req, HttpServletResponse rep){
        Main.init(req,rep);
        String  str = req.getParameter("data");
        UserBean userBean = GsonUtil.getInstance().fromJson(str,UserBean.class);
        System.out.println(str);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getTips(userBean).getData();
        HashMap<Integer,TipBean> tipBeanHashMap = new HashMap<Integer, TipBean>();
        for(int i=0;i<comments.size();i++){
            TipsBean tipBean = GsonUtil.getInstance().fromJson(comments.get(i).getTips(),TipsBean.class);
            for(int j = 0; j<tipBean.getTipBeen().size(); j++){
                if(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition())==null){
                    TipBean tipBean1 = new TipBean(tipBean.getTipBeen().get(j).getPosition(),tipBean.getTipBeen().get(j).getTip(),0,false);
                    tipBeanHashMap.put(tipBean.getTipBeen().get(j).getPosition(),tipBean1);
                }
                if(tipBean.getTipBeen().get(j).isSelect()){
                    tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).setNum(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).getNum()+1);
                }
            }
        }

        try {
            PrintWriter printWriter = rep.getWriter();
            BaseResBean baseResBean = new BaseResBean();
            baseResBean.setData(tipBeanHashMap);
            printWriter.println(GsonUtil.getInstance().toJson(baseResBean));
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
