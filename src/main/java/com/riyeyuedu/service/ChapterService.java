package com.riyeyuedu.service;

import com.riyeyuedu.dao.ChapterDao;
import com.riyeyuedu.entity.ChapterEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Service
public class ChapterService {
    private SqlSession sqlSession;

    private ChapterDao chapterDao;

    public ChapterService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setChapterDao(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    public Boolean addChapter(ChapterEntity chapter) {
        return chapterDao.addChapter(sqlSession, chapter);
    }

    public Boolean addDraft(ChapterEntity chapter) {
        return chapterDao.addDraft(sqlSession, chapter);
    }

    public List<ChapterEntity> getDirectoryByNid(Long nid) {
        return chapterDao.getDirectoryByNid(sqlSession, nid);
    }

    public Map<String, Object> getChapterByCid(Long cid) {
        return chapterDao.getChapterByCid(sqlSession, cid);
    }

    public ChapterEntity getDraftByCid(Long cid) {
        return chapterDao.getDraftByCid(sqlSession, cid);
    }

    public ChapterEntity getFirstChapter(Long nid) {
        return chapterDao.getFirstChapter(sqlSession, nid);
    }

    public ChapterEntity getLastChapter(Long nid) {
        return chapterDao.getLastChapter(sqlSession, nid);
    }

    public ChapterEntity getNewChapter(Long nid) {
        return chapterDao.getNewChapter(sqlSession, nid);
    }

    public Boolean chapterAllowed(ChapterEntity chapter) {
        return chapterDao.chapterAllowed(sqlSession, chapter);
    }

    public List<ChapterEntity> getChapterInfo(Long nid) { return chapterDao.getChapterInfo(sqlSession, nid); }

    public boolean updateDraft(ChapterEntity chapterEntity) {
        return chapterDao.updateDraft(sqlSession, chapterEntity);
    }

    public boolean updateContent(ChapterEntity chapterEntity) {
        return chapterDao.updateContent(sqlSession, chapterEntity);
    }

    public boolean deleteDraft(Long cid) {
        return chapterDao.deleteDraft(sqlSession, cid);
    }
}
