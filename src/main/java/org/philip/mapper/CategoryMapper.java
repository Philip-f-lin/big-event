package org.philip.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.philip.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 新增
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})" )
    void add(Category category);

    // 查詢所有
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    // 根據 id 查詢
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);
}
