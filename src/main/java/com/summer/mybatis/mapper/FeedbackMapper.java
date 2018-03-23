package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Feedback;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FeedbackMapper {
    @Delete({
        "delete from feedback",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into feedback (rate, createdtime, ",
        "userid, remark, ",
        "pics)",
        "values (#{rate,jdbcType=REAL}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{userid,jdbcType=INTEGER}, #{remark,jdbcType=LONGVARCHAR}, ",
        "#{pics,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Feedback record);

    @Select({
        "select",
        "id, rate, createdtime, userid, remark, pics",
        "from feedback",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="createdtime", property="createdtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="pics", property="pics", jdbcType=JdbcType.LONGVARCHAR)
    })
    Feedback selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, rate, createdtime, userid, remark, pics",
        "from feedback"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="createdtime", property="createdtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="pics", property="pics", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Feedback> selectAll();

    @Update({
        "update feedback",
        "set rate = #{rate,jdbcType=REAL},",
          "createdtime = #{createdtime,jdbcType=TIMESTAMP},",
          "userid = #{userid,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=LONGVARCHAR},",
          "pics = #{pics,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Feedback record);
}