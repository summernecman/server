package com.summer.mybatis.mapper;

import com.summer.mybatis.entity.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface CollectionMapper {
    @Delete({
        "delete from collection",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into collection (videoid, userid)",
        "values (#{videoid,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Collection record);

    @Select({
        "select",
        "id, videoid, userid",
        "from collection",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    Collection selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, videoid, userid",
        "from collection"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="videoid", property="videoid", jdbcType=JdbcType.INTEGER),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER)
    })
    List<Collection> selectAll();

    @Update({
        "update collection",
        "set videoid = #{videoid,jdbcType=INTEGER},",
          "userid = #{userid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Collection record);
}