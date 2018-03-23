package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (phone, pwd, ",
        "usertype, name, ",
        "state, uuuid, headurl, ",
        "unitid, area, remark, ",
        "chattime, rate, belong)",
        "values (#{phone,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR}, ",
        "#{usertype,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=INTEGER}, #{uuuid,jdbcType=VARCHAR}, #{headurl,jdbcType=VARCHAR}, ",
        "#{unitid,jdbcType=INTEGER}, #{area,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{chattime,jdbcType=BIGINT}, #{rate,jdbcType=REAL}, #{belong,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(User record);

    @Select({
        "select",
        "id, phone, pwd, usertype, name, state, uuuid, headurl, unitid, area, remark, ",
        "chattime, rate, belong",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="usertype", property="usertype", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="uuuid", property="uuuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="headurl", property="headurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="unitid", property="unitid", jdbcType=JdbcType.INTEGER),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="chattime", property="chattime", jdbcType=JdbcType.BIGINT),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="belong", property="belong", jdbcType=JdbcType.LONGVARCHAR)
    })
    User selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, phone, pwd, usertype, name, state, uuuid, headurl, unitid, area, remark, ",
        "chattime, rate, belong",
        "from user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="usertype", property="usertype", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="uuuid", property="uuuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="headurl", property="headurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="unitid", property="unitid", jdbcType=JdbcType.INTEGER),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="chattime", property="chattime", jdbcType=JdbcType.BIGINT),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="belong", property="belong", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set phone = #{phone,jdbcType=VARCHAR},",
          "pwd = #{pwd,jdbcType=VARCHAR},",
          "usertype = #{usertype,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=INTEGER},",
          "uuuid = #{uuuid,jdbcType=VARCHAR},",
          "headurl = #{headurl,jdbcType=VARCHAR},",
          "unitid = #{unitid,jdbcType=INTEGER},",
          "area = #{area,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "chattime = #{chattime,jdbcType=BIGINT},",
          "rate = #{rate,jdbcType=REAL},",
          "belong = #{belong,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);


}