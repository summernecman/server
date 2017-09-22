package com.summer.main;

import com.summer.base.bean.BaseBean;

import java.util.ArrayList;

/**
 * Created by SWSD on 17-09-22.
 */
public class RoomBean extends BaseBean {

    private String owner;

    private ArrayList<String> members;

    private int maxuser;

    private String groupname;

    private String desc;

    public RoomBean() {
        setOwner("admin");
        ArrayList<String> m = new ArrayList<String>();
        m.add("admin");
        setMembers(m);
        setMaxuser(5000);
        setGroupname("chat");
        setDesc("chat");
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public int getMaxuser() {
        return maxuser;
    }

    public void setMaxuser(int maxuser) {
        this.maxuser = maxuser;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
