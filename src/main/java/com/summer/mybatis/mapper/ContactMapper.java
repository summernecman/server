package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Contact;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface ContactMapper {
    @Delete({
        "delete from contact",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into contact (fromid, toid)",
        "values (#{fromid,jdbcType=INTEGER}, #{toid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Contact record);

    @Select({
        "select",
        "id, fromid, toid",
        "from contact",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER)
    })
    Contact selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, fromid, toid",
        "from contact"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fromid", property="fromid", jdbcType=JdbcType.INTEGER),
        @Result(column="toid", property="toid", jdbcType=JdbcType.INTEGER)
    })
    List<Contact> selectAll();

    @Update({
        "update contact",
        "set fromid = #{fromid,jdbcType=INTEGER},",
          "toid = #{toid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Contact record);
}