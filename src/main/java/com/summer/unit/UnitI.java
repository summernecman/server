package com.summer.unit;

import com.summer.base.bean.BaseResBean;
import com.summer.user.bean.UserBean;

import javax.sql.rowset.BaseRowSet;

/**
 * Created by SWSD on 17-09-11.
 */
public interface UnitI {

    public BaseResBean addUnit(UnitBean unitBean);

    public BaseResBean getUnitById(UnitBean unitBean);

    public BaseResBean unpdateUnit(UnitBean unitBean);

    public BaseResBean getUnitByName(UnitBean unitBean);
}
