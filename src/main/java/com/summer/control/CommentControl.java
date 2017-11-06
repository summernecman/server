package com.summer.control;

import com.summer.base.bean.BaseResBean;
import com.summer.comment.CommentOpe;
import com.summer.comment.bean.RateLevelBean;
import com.summer.comment.bean.TipBean;
import com.summer.comment.bean.TipsBean;
import com.summer.tip.TipOpe;
import com.summer.user.bean.CommentBean;
import com.summer.user.bean.UserBean;
import com.summer.util.GsonUtil;
import com.summer.video.bean.LimitBean;
import com.summer.video.bean.VideoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by SWSD on 17-08-29.
 */
@Controller
@RequestMapping("/comment")
public class CommentControl {

    CommentOpe commentI = new CommentOpe();

    TipOpe tipI = new TipOpe();

    @RequestMapping(value = "/getCommentsWithLimit",method = RequestMethod.POST)
    public void getCommentsWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        LimitBean limitBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),LimitBean.class);
        BaseResBean baseResBean = commentI.getCommentsWithLimit(limitBean);
        baseResBean.setTotal((Integer) commentI.getCommentsNum().getData());
        VideoControl.printOut(rep,baseResBean);
    }



    @RequestMapping(value = "/getCommentByUserPhone",method = RequestMethod.POST)
    public void getCommentByUserPhone(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,commentI.getCommentByUserPhone(userBean));
    }


    @RequestMapping(value = "/getCommentByUserId",method = RequestMethod.POST)
    public void getCommentByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,commentI.getCommentByUserId(userBean));
    }


    @RequestMapping(value = "/getCommentByUserIdWithLimit",method = RequestMethod.POST)
    public void getCommentByUserIdWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        BaseResBean baseResBean = commentI.getCommentByUserIdWithLimit(userBean);
        baseResBean.setTotal((Integer) commentI.getCommentNumByUserId(userBean).getData());
        VideoControl.printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/getOtherCommentByUserIdWithLimit",method = RequestMethod.POST)
    public void getOtherCommentByUserIdWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        BaseResBean baseResBean = commentI.getCommentByUserIdWithLimit(userBean);
        baseResBean.setTotal((Integer) commentI.getCommentNumByUserId(userBean).getData());
        VideoControl.printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/getShortCommentByUserId",method = RequestMethod.POST)
    public void getShortCommentByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,commentI.getShortCommentByUserId(userBean));
    }

    @RequestMapping(value = "/getShortCommentByUserIdWithLimit",method = RequestMethod.POST)
    public void getShortCommentByUserIdWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        BaseResBean baseResBean = commentI.getShortCommentByUserIdWithLimit(userBean);
        baseResBean.setTotal((Integer) commentI.getCommentNumByUserId(userBean).getData());
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getCommentByUserNameWithMyOption",method = RequestMethod.POST)
    public void getCommentByUserNameWithMyOption(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CommentBean commentBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CommentBean.class);
        VideoControl.printOut(rep,commentI.getCommentByUserIdWithMyOption(commentBean));

    }

    @RequestMapping(value = "/getCommentByUserIdWithMyOptionWithLimit",method = RequestMethod.POST)
    public void getCommentByUserNameWithMyOptionWithLimit(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        CommentBean commentBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),CommentBean.class);
        VideoControl.printOut(rep,commentI.getCommentByUserIdWithMyOptionWithLimit(commentBean));
    }


    @RequestMapping(value = "/getCommentNumByUserName",method = RequestMethod.POST)
    public void getCommentNumByUserName(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,commentI.getCommentNumByUserName(userBean));
    }



    @RequestMapping(value = "/getVideoRateCommentByUseId",method = RequestMethod.POST)
    public void getVideoRateCommentByUseId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        VideoControl.printOut(rep,commentI.getVideoRateCommentByUseId(userBean));
    }


    @RequestMapping(value = "/getVideoRateCommentByVideoid",method = RequestMethod.POST)
    public void getVideoRateCommentByVideoid(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        VideoControl.printOut(rep,commentI.getVideoRateCommentByVideoid(videoBean));
    }


    @RequestMapping(value = "/getVideoCommentByVideoName",method = RequestMethod.POST)
    public void getVideoCommentByVideoName(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        VideoControl.printOut(rep,commentI.getVideoCommentByVideoName(videoBean));
    }


    @RequestMapping(value = "/getUserTips",method = RequestMethod.POST)
    public void getUserTips(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);

        ArrayList<TipBean> tipBeen  = (ArrayList<TipBean>) tipI.getTips().getData();
        HashMap<Integer,String> h = new HashMap<Integer, String>();
        HashMap<Integer,TipBean> hh = new HashMap<Integer, TipBean>();
        for(int i=0;i<tipBeen.size();i++){
            h.put(tipBeen.get(i).getPosition(),tipBeen.get(i).getTip());
            hh.put(tipBeen.get(i).getPosition(),tipBeen.get(i));
        }

        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getTips(userBean).getData();
        HashMap<Integer,TipBean> tipBeanHashMap = new HashMap<Integer, TipBean>();
        for(int i=0;i<comments.size();i++){
            TipsBean tipBean = GsonUtil.getInstance().fromJson(comments.get(i).getTips(),TipsBean.class);

            for(int j = 0; j<tipBean.getTipBeen().size(); j++){
                if(h.get(tipBean.getTipBeen().get(j).getPosition())!=null){
                    tipBean.getTipBeen().get(j).setTip(h.get(tipBean.getTipBeen().get(j).getPosition()));
                }
                if(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition())==null){
                    TipBean tipBean1 = new TipBean(tipBean.getTipBeen().get(j).getPosition(),tipBean.getTipBeen().get(j).getTip(),0,false);
                    tipBeanHashMap.put(tipBean.getTipBeen().get(j).getPosition(),tipBean1);
                }
                if(tipBean.getTipBeen().get(j).isSelect()){
                    tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).setNum(tipBeanHashMap.get(tipBean.getTipBeen().get(j).getPosition()).getNum()+1);
                }
            }
        }
        Iterator<Integer> i = tipBeanHashMap.keySet().iterator();
        while(i.hasNext()){
            int m = i.next();
            if(hh.get(m)==null||hh.get(m).getEnable()<1){
                tipBeanHashMap.remove(m);
                continue;
            }
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(tipBeanHashMap);
        VideoControl.printOut(rep,baseResBean);
    }


    @RequestMapping(value = "/getUserTipsByUserId",method = RequestMethod.POST)
    public void getUserTipsByUserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        ArrayList<CommentBean> comments = (ArrayList<CommentBean>) commentI.getTipsByUserId(userBean).getData();
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
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(tipBeanHashMap);
        VideoControl.printOut(rep,baseResBean);
    }

    @RequestMapping(value = "/getVideoCommentByVideoNameAndFrom",method = RequestMethod.POST)
    public void getVideoCommentByVideoNameAndFrom(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        VideoControl.printOut(rep,commentI.getVideoCommentByVideoNameAndFrom(videoBean));
    }

    @RequestMapping(value = "/getVideoCommentByVideoIdAndFrom",method = RequestMethod.POST)
    public void getVideoCommentByVideoIdAndFrom(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        VideoBean videoBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),VideoBean.class);
        VideoControl.printOut(rep,commentI.getVideoCommentByVideoIdAndFrom(videoBean));
    }

    @RequestMapping(value = "/getVideoCommentRateLevelByuserId",method = RequestMethod.POST)
    public void getVideoCommentRateLevelByuserId(HttpServletRequest req, HttpServletResponse rep){
        VideoControl.init(req,rep);
        UserBean userBean = GsonUtil.getInstance().fromJson(req.getParameter("data"),UserBean.class);
        ArrayList<RateLevelBean> rateLevelBeen = (ArrayList<RateLevelBean>) commentI.getVideoCommentRateLevelByuserId(userBean).getData();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<=5;i++){
            map.put(i,0);
        }
        for(int i=0;rateLevelBeen!=null&&i<rateLevelBeen.size();i++){
            int rate = (int) rateLevelBeen.get(i).getRatef();
            if(rate>5){
                rate=5;
            }

            if(rate<0){
                rate=0;
            }
            int n = map.get(rate);
            map.put(rate,n+1);
        }
        BaseResBean baseResBean = new BaseResBean();
        baseResBean.setData(map);
        VideoControl.printOut(rep,baseResBean);
    }


}
