package com.riyeyuedu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.riyeyuedu.entity.ResponseEntity;
import com.riyeyuedu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsController {
    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/authorNews")
    public ResponseEntity getNews(@RequestParam("page") int page) {
        Page pager = PageHelper.startPage(page, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("newsList", newsService.getNews(0));
        map.put("total", pager.getTotal());
        return new ResponseEntity(map);
    }
}
