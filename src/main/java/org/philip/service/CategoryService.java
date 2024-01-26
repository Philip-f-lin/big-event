package org.philip.service;

import org.philip.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增分類
    void add(Category category);

    // 列表查詢
    List<Category> list();

    // 根據 id 查詢分類訊息
    Category findById(Integer id);
}
