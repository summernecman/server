package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Fix;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FixMapper {
    @Delete({
        "delete from fix",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into fix (ctime, utime, ",
        "version, text)",
        "values (#{ctime,jdbcType=TIMESTAMP}, #{utime,jdbcType=TIMESTAMP}, ",
        "#{version,jdbcType=REAL}, #{text,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Fix record);

    @Select({
        "select",
        "id, ctime, utime, version, text",
        "from fix",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.REAL),
        @Result(column="text", property="text", jdbcType=JdbcType.LONGVARCHAR)
    })
    Fix selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, ctime, utime, version, text",
        "from fix"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.REAL),
        @Result(column="text", property="text", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Fix> selectAll();

    @Update({
        "update fix",
        "set ctime = #{ctime,jdbcType=TIMESTAMP},",
          "utime = #{utime,jdbcType=TIMESTAMP},",
          "version = #{version,jdbcType=REAL},",
          "text = #{text,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Fix record);
}