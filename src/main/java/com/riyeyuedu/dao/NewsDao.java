package com.riyeyuedu.dao;

import com.riyeyuedu.entity.NewsEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDao {
    public List<NewsEntity> getNews(SqlSession sqlSession, Integer flag) {
        return sqlSession.selectList("news.getNews", flag);
    }
}
