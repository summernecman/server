package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Unit;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UnitMapper {
    @Delete({
        "delete from unit",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into unit (unittype, unitname)",
        "values (#{unittype,jdbcType=INTEGER}, #{unitname,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Unit record);

    @Select({
        "select",
        "id, unittype, unitname",
        "from unit",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unittype", property="unittype", jdbcType=JdbcType.INTEGER),
        @Result(column="unitname", property="unitname", jdbcType=JdbcType.VARCHAR)
    })
    Unit selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, unittype, unitname",
        "from unit"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unittype", property="unittype", jdbcType=JdbcType.INTEGER),
        @Result(column="unitname", property="unitname", jdbcType=JdbcType.VARCHAR)
    })
    List<Unit> selectAll();

    @Update({
        "update unit",
        "set unittype = #{unittype,jdbcType=INTEGER},",
          "unitname = #{unitname,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Unit record);
}