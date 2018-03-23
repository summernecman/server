package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Videodetail;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface VideodetailMapper {
    @Delete({
        "delete from videodetail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into videodetail (ctime, utime, ",
        "callid, url, uploaded, ",
        "userid, time)",
        "values (#{ctime,jdbcType=TIMESTAMP}, #{utime,jdbcType=TIMESTAMP}, ",
        "#{callid,jdbcType=INTEGER}, #{url,jdbcType=VARCHAR}, #{uploaded,jdbcType=INTEGER}, ",
        "#{userid,jdbcType=INTEGER}, #{time,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Videodetail record);

    @Select({
        "select",
        "id, ctime, utime, callid, url, uploaded, userid, time",
        "from videodetail",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="callid", property="callid", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="uploaded", property="uploaded", jdbcType=JdbcType.INTEGER),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER)
    })
    Videodetail selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, ctime, utime, callid, url, uploaded, userid, time",
        "from videodetail"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="callid", property="callid", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="uploaded", property="uploaded", jdbcType=JdbcType.INTEGER),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER)
    })
    List<Videodetail> selectAll();

    @Update({
        "update videodetail",
        "set ctime = #{ctime,jdbcType=TIMESTAMP},",
          "utime = #{utime,jdbcType=TIMESTAMP},",
          "callid = #{callid,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR},",
          "uploaded = #{uploaded,jdbcType=INTEGER},",
          "userid = #{userid,jdbcType=INTEGER},",
          "time = #{time,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Videodetail record);
}