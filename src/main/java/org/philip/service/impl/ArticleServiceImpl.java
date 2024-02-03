package org.philip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.philip.mapper.ArticleMapper;
import org.philip.pojo.Article;
import org.philip.pojo.PageBean;
import org.philip.service.ArticleService;
import org.philip.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // 補充屬性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 創建 PageBean 對象
        PageBean<Article> pb = new PageBean<>();

        // 2. 開啟分頁查詢 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // 3. 調用 mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId, categoryId, state);
        // Page 中提供了方法，可以獲取 PageHelper 分頁查詢後，得到的總紀錄條數和當前頁數據
        Page<Article> p = (Page<Article>) as;

        // 把數據填充到 PageBean對象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
