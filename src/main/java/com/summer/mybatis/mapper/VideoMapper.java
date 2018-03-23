package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Video;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface VideoMapper {
    @Delete({
        "delete from video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into video (file, created, ",
        "fromid, toid, fromphone, ",
        "tophone, timenum, ",
        "uploaded, callstate, ",
        "type)",
        "values (#{file,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP}, ",
        "#{fromid,jdbcType=INTEGER}, #{toid,jdbcType=INTEGER}, #{fromphone,jdbcType=VARCHAR}, ",
        "#{tophone,jdbcType=VARCHAR}, #{timenum,jdbcType=BIGINT}, ",
        "#{uploaded,jdbcType=INTEGER}, #{callstate,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Video record);

    @Select({
        "select",
        "id, file, created, fromid, toid, fromphone, tophone, timenum, uploaded, callstate, ",
        "type",
        "from video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER),
        @Result(column="fromphone", property="fromphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="tophone", property="tophone", jdbcType=JdbcType.VARCHAR),
        @Result(column="timenum", property="timenum", jdbcType=JdbcType.BIGINT),
        @Result(column="uploaded", property="uploaded", jdbcType=JdbcType.INTEGER),
        @Result(column="callstate", property="callstate", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    Video selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, file, created, fromid, toid, fromphone, tophone, timenum, uploaded, callstate, ",
        "type",
        "from video"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER),
        @Result(column="fromphone", property="fromphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="tophone", property="tophone", jdbcType=JdbcType.VARCHAR),
        @Result(column="timenum", property="timenum", jdbcType=JdbcType.BIGINT),
        @Result(column="uploaded", property="uploaded", jdbcType=JdbcType.INTEGER),
        @Result(column="callstate", property="callstate", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<Video> selectAll();

    @Update({
        "update video",
        "set file = #{file,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "fromid = #{fromid,jdbcType=INTEGER},",
          "toid = #{toid,jdbcType=INTEGER},",
          "fromphone = #{fromphone,jdbcType=VARCHAR},",
          "tophone = #{tophone,jdbcType=VARCHAR},",
          "timenum = #{timenum,jdbcType=BIGINT},",
          "uploaded = #{uploaded,jdbcType=INTEGER},",
          "callstate = #{callstate,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Video record);
}