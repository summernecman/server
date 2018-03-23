package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Agree;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface AgreeMapper {
    @Delete({
        "delete from agree",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into agree (commentid, agreeid)",
        "values (#{commentid,jdbcType=INTEGER}, #{agreeid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Agree record);

    @Select({
        "select",
        "id, commentid, agreeid",
        "from agree",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="commentid", property="commentid", jdbcType=JdbcType.INTEGER),
        @Result(column="agreeid", property="agreeid", jdbcType=JdbcType.INTEGER)
    })
    Agree selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, commentid, agreeid",
        "from agree"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="commentid", property="commentid", jdbcType=JdbcType.INTEGER),
        @Result(column="agreeid", property="agreeid", jdbcType=JdbcType.INTEGER)
    })
    List<Agree> selectAll();

    @Update({
        "update agree",
        "set commentid = #{commentid,jdbcType=INTEGER},",
          "agreeid = #{agreeid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Agree record);
}