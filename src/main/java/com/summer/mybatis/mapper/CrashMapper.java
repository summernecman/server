package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Crash;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CrashMapper {
    @Delete({
        "delete from crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into crash (createdtime, platform, ",
        "error, user)",
        "values (#{createdtime,jdbcType=TIMESTAMP}, #{platform,jdbcType=VARCHAR}, ",
        "#{error,jdbcType=LONGVARCHAR}, #{user,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Crash record);

    @Select({
        "select",
        "id, createdtime, platform, error, user",
        "from crash",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="createdtime", property="createdtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="error", property="error", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="user", property="user", jdbcType=JdbcType.LONGVARCHAR)
    })
    Crash selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, createdtime, platform, error, user",
        "from crash"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="createdtime", property="createdtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="platform", property="platform", jdbcType=JdbcType.VARCHAR),
        @Result(column="error", property="error", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="user", property="user", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Crash> selectAll();

    @Update({
        "update crash",
        "set createdtime = #{createdtime,jdbcType=TIMESTAMP},",
          "platform = #{platform,jdbcType=VARCHAR},",
          "error = #{error,jdbcType=LONGVARCHAR},",
          "user = #{user,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Crash record);
}