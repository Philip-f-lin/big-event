package org.philip.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.philip.pojo.Article;
import org.philip.pojo.Result;
import org.philip.service.ArticleService;
import org.philip.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
}
