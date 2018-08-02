package com.riyeyuedu.service;

import com.riyeyuedu.dao.NewsDao;
import com.riyeyuedu.entity.NewsEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private NewsDao newsDao;

    private SqlSession sqlSession;

    @Autowired
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<NewsEntity> getNews(Integer flag) {
        return newsDao.getNews(sqlSession, flag);
    }
}
