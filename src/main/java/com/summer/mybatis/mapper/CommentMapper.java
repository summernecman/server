package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CommentMapper {
    @Delete({
        "delete from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into comment (rate, remark, ",
        "created, videoname, ",
        "fromuser, touser, ",
        "fromid, toid, videoid, ",
        "tips)",
        "values (#{rate,jdbcType=REAL}, #{remark,jdbcType=VARCHAR}, ",
        "#{created,jdbcType=VARCHAR}, #{videoname,jdbcType=VARCHAR}, ",
        "#{fromuser,jdbcType=VARCHAR}, #{touser,jdbcType=VARCHAR}, ",
        "#{fromid,jdbcType=INTEGER}, #{toid,jdbcType=INTEGER}, #{videoid,jdbcType=INTEGER}, ",
        "#{tips,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Comment record);

    @Select({
        "select",
        "id, rate, remark, created, videoname, fromuser, touser, fromid, toid, videoid, ",
        "tips",
        "from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.VARCHAR),
        @Result(column="videoname", property="videoname", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromuser", property="fromuser", jdbcType=JdbcType.VARCHAR),
        @Result(column="touser", property="touser", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="tips", property="tips", jdbcType=JdbcType.LONGVARCHAR)
    })
    Comment selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, rate, remark, created, videoname, fromuser, touser, fromid, toid, videoid, ",
        "tips",
        "from comment"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rate", property="rate", jdbcType=JdbcType.REAL),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.VARCHAR),
        @Result(column="videoname", property="videoname", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromuser", property="fromuser", jdbcType=JdbcType.VARCHAR),
        @Result(column="touser", property="touser", jdbcType=JdbcType.VARCHAR),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="tips", property="tips", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Comment> selectAll();

    @Update({
        "update comment",
        "set rate = #{rate,jdbcType=REAL},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=VARCHAR},",
          "videoname = #{videoname,jdbcType=VARCHAR},",
          "fromuser = #{fromuser,jdbcType=VARCHAR},",
          "touser = #{touser,jdbcType=VARCHAR},",
          "fromid = #{fromid,jdbcType=INTEGER},",
          "toid = #{toid,jdbcType=INTEGER},",
          "videoid = #{videoid,jdbcType=INTEGER},",
          "tips = #{tips,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
}