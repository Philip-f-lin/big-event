package org.philip.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.philip.pojo.Article;

@Mapper
public interface ArticleMapper {
    // 新增
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
            " values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);
}