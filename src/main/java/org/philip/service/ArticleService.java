package org.philip.service;

import org.philip.pojo.Article;
import org.philip.pojo.PageBean;

public interface ArticleService {
    // 新增文章
    void add(Article article);

    // 條件分頁列表查詢
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
