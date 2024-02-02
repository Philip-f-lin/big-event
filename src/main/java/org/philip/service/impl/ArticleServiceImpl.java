package org.philip.service.impl;

import org.philip.mapper.ArticleMapper;
import org.philip.pojo.Article;
import org.philip.service.ArticleService;
import org.philip.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
