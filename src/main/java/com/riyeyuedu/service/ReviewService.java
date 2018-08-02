package com.riyeyuedu.service;

import com.riyeyuedu.dao.ReviewDao;
import com.riyeyuedu.entity.ReviewEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewService {
    private SqlSession sqlSession;

    private ReviewDao reviewDao;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public Boolean addReview(ReviewEntity reviewEntity) {
        return reviewDao.addReview(sqlSession, reviewEntity);
    }

    public List<Map<String, Object>> getReviewByRid(int rid) {
        return reviewDao.getReviewByRid(sqlSession, rid);
    }

    public Boolean deleteReview(Long id) {
        return reviewDao.deleteReview(sqlSession, id);
    }
}
