package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Unittype;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UnittypeMapper {
    @Delete({
        "delete from unittype",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into unittype (unittype, unitname)",
        "values (#{unittype,jdbcType=INTEGER}, #{unitname,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Unittype record);

    @Select({
        "select",
        "id, unittype, unitname",
        "from unittype",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unittype", property="unittype", jdbcType=JdbcType.INTEGER),
        @Result(column="unitname", property="unitname", jdbcType=JdbcType.VARCHAR)
    })
    Unittype selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, unittype, unitname",
        "from unittype"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unittype", property="unittype", jdbcType=JdbcType.INTEGER),
        @Result(column="unitname", property="unitname", jdbcType=JdbcType.VARCHAR)
    })
    List<Unittype> selectAll();

    @Update({
        "update unittype",
        "set unittype = #{unittype,jdbcType=INTEGER},",
          "unitname = #{unitname,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Unittype record);
}