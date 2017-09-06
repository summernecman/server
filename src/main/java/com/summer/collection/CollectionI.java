package com.summer.collection;

import com.summer.base.bean.BaseResBean;
import com.summer.collection.bean.CollectionBean;
import com.summer.user.bean.UserBean;
import com.summer.video.bean.VideoBean;

import javax.sql.rowset.BaseRowSet;

/**
 * Created by SWSD on 17-09-04.
 */
public interface CollectionI {

    public BaseResBean getCollectionsByUserId(UserBean userBean);

    public BaseResBean getCollectionNumByUserId(UserBean userBean);

    public BaseResBean getCollectionVideosByUserId(UserBean userBean);

    public BaseResBean isCollectedByVideoIdAndUserId(CollectionBean collectionBean);


    public BaseResBean collect(VideoBean videoBean);

}
