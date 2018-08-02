package com.riyeyuedu.service;

import com.riyeyuedu.dao.ScoreDao;
import com.riyeyuedu.entity.ScoreEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreService {
    private SqlSession sqlSession;

    private ScoreDao scoreDao;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setScoreDao(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public Boolean addScore(ScoreEntity scoreEntity) {
        return scoreDao.addScore(sqlSession, scoreEntity);
    }

    public Boolean updateScore(ScoreEntity scoreEntity) {
        return scoreDao.updateScore(sqlSession, scoreEntity);
    }

    public ScoreEntity getScore(ScoreEntity scoreEntity) {
        return scoreDao.getScore(sqlSession, scoreEntity);
    }

    public Integer getScoreByUid(ScoreEntity scoreEntity) { return scoreDao.getScoreByUid(sqlSession, scoreEntity); }

    public List<Map<String, Object>> getScoresByUid(int uid) {
        return scoreDao.getScoresByUid(sqlSession, uid);
    }

    public Integer getScoreNumByNid(Long nid) {
        return scoreDao.getScoreNumByNid(sqlSession, nid);
    }

    public Double getAvgScoreByNid( Long nid) {
        return scoreDao.getAvgScoreByNid(sqlSession, nid);
    }

    public List<Map<String, Object>> getScoreByNid(Long nid) { return scoreDao.getScoreByNid(sqlSession, nid); }
}
