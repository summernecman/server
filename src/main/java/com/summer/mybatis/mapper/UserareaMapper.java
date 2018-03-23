package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Userarea;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserareaMapper {
    @Delete({
        "delete from userarea",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into userarea (userid, areaid)",
        "values (#{userid,jdbcType=INTEGER}, #{areaid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Userarea record);

    @Select({
        "select",
        "id, userid, areaid",
        "from userarea",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="areaid", property="areaid", jdbcType=JdbcType.INTEGER)
    })
    Userarea selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, userid, areaid",
        "from userarea"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="areaid", property="areaid", jdbcType=JdbcType.INTEGER)
    })
    List<Userarea> selectAll();

    @Update({
        "update userarea",
        "set userid = #{userid,jdbcType=INTEGER},",
          "areaid = #{areaid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Userarea record);
}