package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Tip;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface TipMapper {
    @Delete({
        "delete from tip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tip (position, tip, ",
        "enable)",
        "values (#{position,jdbcType=INTEGER}, #{tip,jdbcType=VARCHAR}, ",
        "#{enable,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Tip record);

    @Select({
        "select",
        "id, position, tip, enable",
        "from tip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="position", property="position", jdbcType=JdbcType.INTEGER),
        @Result(column="tip", property="tip", jdbcType=JdbcType.VARCHAR),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER)
    })
    Tip selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, position, tip, enable",
        "from tip"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="position", property="position", jdbcType=JdbcType.INTEGER),
        @Result(column="tip", property="tip", jdbcType=JdbcType.VARCHAR),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER)
    })
    List<Tip> selectAll();

    @Update({
        "update tip",
        "set position = #{position,jdbcType=INTEGER},",
          "tip = #{tip,jdbcType=VARCHAR},",
          "enable = #{enable,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tip record);
}