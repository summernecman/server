package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Videotip;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface VideotipMapper {
    @Delete({
        "delete from videotip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into videotip (ctime, utime, ",
        "type, txt, enable)",
        "values (#{ctime,jdbcType=TIMESTAMP}, #{utime,jdbcType=TIMESTAMP}, ",
        "#{type,jdbcType=VARCHAR}, #{txt,jdbcType=VARCHAR}, #{enable,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Videotip record);

    @Select({
        "select",
        "id, ctime, utime, type, txt, enable",
        "from videotip",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="txt", property="txt", jdbcType=JdbcType.VARCHAR),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER)
    })
    Videotip selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, ctime, utime, type, txt, enable",
        "from videotip"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="txt", property="txt", jdbcType=JdbcType.VARCHAR),
        @Result(column="enable", property="enable", jdbcType=JdbcType.INTEGER)
    })
    List<Videotip> selectAll();

    @Update({
        "update videotip",
        "set ctime = #{ctime,jdbcType=TIMESTAMP},",
          "utime = #{utime,jdbcType=TIMESTAMP},",
          "type = #{type,jdbcType=VARCHAR},",
          "txt = #{txt,jdbcType=VARCHAR},",
          "enable = #{enable,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Videotip record);
}