package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ReviewEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReviewDao {
    public Boolean addReview(SqlSession sqlSession, ReviewEntity reviewEntity) {
        int addNum = sqlSession.insert("review.insertReview", reviewEntity);
        return addNum == 1;
    }

    public List<Map<String, Object>> getReviewByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectList("review.getReviewByRid", rid);
    }

    public Boolean deleteReview(SqlSession sqlSession, Long id) {
        int deleteNum = sqlSession.delete("review.deleteReview", id);
        return deleteNum == 1;
    }
}
