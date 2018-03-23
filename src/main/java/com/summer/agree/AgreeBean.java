package com.summer.agree;

import com.summer.base.bean.BaseBean;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * Created by SWSD on 17-09-12.
 */

@lombok.Getter
@lombok.Setter
public class AgreeBean extends BaseBean {

    private int id;

    private int commentid;

    private int agreeid;

    public AgreeBean() {
    }

    public AgreeBean(int commentid, int agreeid) {
        this.commentid = commentid;
        this.agreeid = agreeid;
    }
}
