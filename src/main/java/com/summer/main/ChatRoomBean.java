package com.summer.main;

import com.google.gson.annotations.SerializedName;
import com.summer.base.bean.BaseBean;

import java.util.List;

/**
 * Created by SWSD on 17-09-22.
 */
public class ChatRoomBean extends BaseBean {


    /**
     * action : get
     * application : 99e62cf0-5fce-11e7-a156-ad80b441e605
     * params : {"pagesize":["1"],"pagenum":["1"]}
     * uri : http://a1.easemob.com/1122170703115322/service/chatrooms
     * entities : []
     * data : [{"id":"27973212241921","name":"chat","owner":"admin","affiliations_count":5}]
     * timestamp : 1507686395465
     * duration : 0
     * organization : 1122170703115322
     * applicationName : service
     * count : 1
     */

    private String action;
    private String application;
    private ParamsBean params;
    private String uri;
    private long timestamp;
    private int duration;
    private String organization;
    private String applicationName;
    private int count;
    private List<?> entities;
    private List<DataBean> data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<?> getEntities() {
        return entities;
    }

    public void setEntities(List<?> entities) {
        this.entities = entities;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ParamsBean {
        private List<String> pagesize;
        private List<String> pagenum;

        public List<String> getPagesize() {
            return pagesize;
        }

        public void setPagesize(List<String> pagesize) {
            this.pagesize = pagesize;
        }

        public List<String> getPagenum() {
            return pagenum;
        }

        public void setPagenum(List<String> pagenum) {
            this.pagenum = pagenum;
        }
    }

    public static class DataBean {

        /**
         * id : 27973212241921
         * name : chat
         * description : chat
         * membersonly : false
         * allowinvites : false
         * maxusers : 1000
         * owner : admin
         * created : 1506075734570
         * custom :
         * affiliations_count : 5
         * affiliations : [{"member":"18721607438"},{"member":"18721607421"},{"member":"18721607437"},{"member":"18721607422"},{"owner":"admin"}]
         * public : true
         */

        private String id;
        private String name;
        private String description;
        private boolean membersonly;
        private boolean allowinvites;
        private int maxusers;
        private String owner;
        private long created;
        private String custom;
        private int affiliations_count;
        @SerializedName("public")
        private boolean publicX;
        private List<AffiliationsBean> affiliations;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isMembersonly() {
            return membersonly;
        }

        public void setMembersonly(boolean membersonly) {
            this.membersonly = membersonly;
        }

        public boolean isAllowinvites() {
            return allowinvites;
        }

        public void setAllowinvites(boolean allowinvites) {
            this.allowinvites = allowinvites;
        }

        public int getMaxusers() {
            return maxusers;
        }

        public void setMaxusers(int maxusers) {
            this.maxusers = maxusers;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public long getCreated() {
            return created;
        }

        public void setCreated(long created) {
            this.created = created;
        }

        public String getCustom() {
            return custom;
        }

        public void setCustom(String custom) {
            this.custom = custom;
        }

        public int getAffiliations_count() {
            return affiliations_count;
        }

        public void setAffiliations_count(int affiliations_count) {
            this.affiliations_count = affiliations_count;
        }

        public boolean isPublicX() {
            return publicX;
        }

        public void setPublicX(boolean publicX) {
            this.publicX = publicX;
        }

        public List<AffiliationsBean> getAffiliations() {
            return affiliations;
        }

        public void setAffiliations(List<AffiliationsBean> affiliations) {
            this.affiliations = affiliations;
        }

        public static class AffiliationsBean {
            /**
             * member : 18721607438
             * owner : admin
             */

            private String member;
            private String owner;

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }
        }
    }
}
