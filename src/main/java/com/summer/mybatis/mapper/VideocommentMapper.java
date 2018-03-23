package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Videocomment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface VideocommentMapper {
    @Delete({
        "delete from videocomment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into videocomment (ctime, utime, ",
        "callid, txt, type, ",
        "userid)",
        "values (#{ctime,jdbcType=TIMESTAMP}, #{utime,jdbcType=TIMESTAMP}, ",
        "#{callid,jdbcType=INTEGER}, #{txt,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{userid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Videocomment record);

    @Select({
        "select",
        "id, ctime, utime, callid, txt, type, userid",
        "from videocomment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="callid", property="callid", jdbcType=JdbcType.INTEGER),
        @Result(column="txt", property="txt", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    Videocomment selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, ctime, utime, callid, txt, type, userid",
        "from videocomment"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="callid", property="callid", jdbcType=JdbcType.INTEGER),
        @Result(column="txt", property="txt", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    List<Videocomment> selectAll();

    @Update({
        "update videocomment",
        "set ctime = #{ctime,jdbcType=TIMESTAMP},",
          "utime = #{utime,jdbcType=TIMESTAMP},",
          "callid = #{callid,jdbcType=INTEGER},",
          "txt = #{txt,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "userid = #{userid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Videocomment record);
}