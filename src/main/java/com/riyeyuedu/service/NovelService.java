package com.riyeyuedu.service;

import com.riyeyuedu.dao.NovelDao;
import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.NovelEntity;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Service
public class NovelService {
    private SqlSession sqlSession;

    private NovelDao novelDao;

    public NovelService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setNovelDao(NovelDao novelDao) {
        this.novelDao = novelDao;
    }

    public boolean addNovel(NovelEntity novel) {
        return novelDao.addNovel(sqlSession, novel);
    }

    public List<Map<String, Object>> getAllNovel(Map<String, Object> map) {
        return novelDao.getAllNovel(sqlSession, map);
    }

    public Map<String, Object> getNovelByNid(Long nid) {
        return novelDao.getNovelByNid(sqlSession, nid);
    }

    public Map<String, Object> getTitleByNid(Long nid) {
        return novelDao.getTitleByNid(sqlSession, nid);
    }

    public void addClickNum(Long nid) {
        novelDao.addClickNum(sqlSession, nid);
    }

    public List<Map<String, Object>> getNovelByLidL5(int lid) {
        return novelDao.getNovelByLidL5(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByRecentL23() {
        return novelDao.getNovelByRecentL23(sqlSession);
    }

    public List<Map<String, Object>> getNovelByScoreL10() {
        return novelDao.getNovelByScoreL10(sqlSession);
    }

    public List<Map<String, Object>> getNovelByScoreL17() {
        return novelDao.getNovelByScoreL17(sqlSession);
    }

    public List<Map<String, Object>> getScoreNovelByLid(int lid) {
        return novelDao.getScoreNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByNewL10() {
        return novelDao.getNovelByNewL10(sqlSession);
    }

    public List<Map<String, Object>> getNovelByNewL17() {
        return novelDao.getNovelByNewL17(sqlSession);
    }

    public List<Map<String, Object>> getNovelByClickL10() {
        return novelDao.getNovelByClickL10(sqlSession);
    }

    public NovelEntity getNovelByTitle(String title) {
        return novelDao.getNovelByTitle(sqlSession ,title);
    }

    public List<Map<String, Object>> getNovelByLidRecent(int lid) {
        return novelDao.getNovelByLidRecent(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByLidScore(int lid) {
        return novelDao.getNovelByLidScore(sqlSession, lid);
    }

    public List<Map<String, Object>> getNovelByStateL10(int state) {
        return novelDao.getNovelByStateL10(sqlSession, state);
    }

    public List<Map<String, Object>> getNovelInBookCase(BookCaseEntity bookCaseEntity) {
        return novelDao.getNovelInBookCase(sqlSession, bookCaseEntity);
    }

    public List<Map<String, Object>> searchNovelInBookCase(Map map) {
        return novelDao.getInBookCaseByTitle(sqlSession, map);
    }


    public Map<String, Object> getNovelByRecord(RecordEntity recordEntity) {
        return novelDao.getNovelByRecord(sqlSession, recordEntity);
    }

    public List<Map<String, Object>> search(Map map) {
        List<Map<String, Object>> novels = novelDao.getNovelByTitleWidly(sqlSession, map);
        if (novels.size() == 0) {
            novels = novelDao.getNovelByAuthorName(sqlSession, map.get("key").toString());
        }
        return novels;
    }

    public List<Map<String, Object>> getNovelByAuthorName(String authorName) {
        return novelDao.getNovelByAuthorName(sqlSession, authorName);
    }

    public List<Map<String, Object>> getNotAllowedNovel() {
        return novelDao.getNotAllowedNovel(sqlSession);
    }

    public List<Map<String, Object>> getNovelByRecommendL10() {
        return novelDao.getNovelByRecommendL10(sqlSession);
    }

    public List<NovelEntity> getEditNovel() {
        return novelDao.getEditNovel(sqlSession);
    }

    public List<Map<String, Object>> getNewNovelByLid(int lid) {
        return novelDao.getNewNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getNewNovelByLidL10(int lid) {
        return novelDao.getNewNovelByLidL10(sqlSession, lid);
    }

    public List<Map<String, Object>> getNewNovelByLidL23(int lid) {
        return novelDao.getNewNovelByLidL23(sqlSession, lid);
    }

    public List<Map<String, Object>> getPopularNovelByLid(int lid) {
        return novelDao.getPopularNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getPopularNovelByLidL10(int lid) {
        return novelDao.getPopularNovelByLidL10(sqlSession, lid);
    }

    public List<Map<String, Object>> getUpdateNovelByLid(int lid) {
        return novelDao.getUpdateNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getFinishNovelByLid(Map m) {
        return novelDao.getFinishNovelByLid(sqlSession, m);
    }

    public List<Map<String, Object>> getFinishNovelByLidL10(int lid) {
        return novelDao.getFinishNovelByLidL10(sqlSession, lid);
    }


    public List<Map<String, Object>> getRecommendNovelByLid(int lid) {
        return novelDao.getRecommendNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getRecommendNovelByLidL10(int lid) {
        return novelDao.getRecommendNovelByLidL10(sqlSession, lid);
    }

    public List<Map<String, Object>> getRecommendNovelByLidL15(int lid) {
        return novelDao.getRecommendNovelByLidL15(sqlSession, lid);
    }

    public List<Map<String, Object>> getCollectNovelByLid(int lid) {
        return novelDao.getCollectNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getCollectNovelByLidL10(int lid) {
        return novelDao.getCollectNovelByLidL10(sqlSession, lid);
    }

    public List<Map<String, Object>> getClickNovelByLid(int lid) {
        return novelDao.getClickNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> getClickNovelByLidL10(int lid) {
        return novelDao.getClickNovelByLidL10(sqlSession, lid);
    }

    public List<Map<String, Object>> getAllNovelByLid(int lid) {
        return novelDao.getAllNovelByLid(sqlSession, lid);
    }

    public List<Map<String, Object>> searchNovel(String bookName) {
        return novelDao.getNovelByBookName(sqlSession, bookName);
    }

    public boolean addChapterNum(Long nid) {
        return novelDao.addChapterNum(sqlSession, nid);
    }

    public Boolean updateScore(Map map) {
        return novelDao.updateScore(sqlSession, map);
    }

    public List<Map<String, Object>> getNovelByUid(int uid) {
        return novelDao.getNovelByUid(sqlSession, uid);
    }
}
