package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Share;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ShareMapper {
    @Delete({
        "delete from share",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into share (videoid, sendid, ",
        "receiptid)",
        "values (#{videoid,jdbcType=INTEGER}, #{sendid,jdbcType=INTEGER}, ",
        "#{receiptid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Share record);

    @Select({
        "select",
        "id, videoid, sendid, receiptid",
        "from share",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="sendid", property="sendid", jdbcType=JdbcType.INTEGER),
        @Result(column="receiptid", property="receiptid", jdbcType=JdbcType.INTEGER)
    })
    Share selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, videoid, sendid, receiptid",
        "from share"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="sendid", property="sendid", jdbcType=JdbcType.INTEGER),
        @Result(column="receiptid", property="receiptid", jdbcType=JdbcType.INTEGER)
    })
    List<Share> selectAll();

    @Update({
        "update share",
        "set videoid = #{videoid,jdbcType=INTEGER},",
          "sendid = #{sendid,jdbcType=INTEGER},",
          "receiptid = #{receiptid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Share record);
}