package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.App;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface AppMapper {
    @Delete({
        "delete from app",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into app (versioncode, versionname, ",
        "url)",
        "values (#{versioncode,jdbcType=INTEGER}, #{versionname,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(App record);

    @Select({
        "select",
        "id, versioncode, versionname, url",
        "from app",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="versioncode", property="versioncode", jdbcType=JdbcType.INTEGER),
        @Result(column="versionname", property="versionname", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    App selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, versioncode, versionname, url",
        "from app"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="versioncode", property="versioncode", jdbcType=JdbcType.INTEGER),
        @Result(column="versionname", property="versionname", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR)
    })
    List<App> selectAll();

    @Update({
        "update app",
        "set versioncode = #{versioncode,jdbcType=INTEGER},",
          "versionname = #{versionname,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(App record);
}