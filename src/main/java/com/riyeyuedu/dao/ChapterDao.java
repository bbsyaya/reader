package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ChapterEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by 阳溢 on 2018/1/5.
 */
@Repository
public class ChapterDao {

    public Boolean addChapter(SqlSession sqlSession, ChapterEntity chapter) {
        int addNum = sqlSession.insert("chapter.insertChapter", chapter);
        return addNum == 1;
    }

    public Boolean addDraft(SqlSession sqlSession, ChapterEntity chapter) {
        int addNum = sqlSession.insert("chapter.insertDraft", chapter);
        return addNum == 1;
    }

    public List<ChapterEntity> getDirectoryByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectList("chapter.getDirectoryByNid", nid);
    }

    public Map<String, Object> getChapterByCid(SqlSession sqlSession, Long cid) {
        return sqlSession.selectOne("chapter.getChapterByCid", cid);
    }

    public ChapterEntity getDraftByCid(SqlSession sqlSession, Long cid){
        return sqlSession.selectOne("chapter.getDraftByCid", cid);
    }

    public ChapterEntity getFirstChapter(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("chapter.getFirstChapter", nid);
    }

    public ChapterEntity getLastChapter(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("chapter.getLastChapter", nid);
    }

    public ChapterEntity getNewChapter(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("chapter.getNewChapter", nid);
    }

    public Boolean chapterAllowed(SqlSession sqlSession, ChapterEntity chapter) {
        int updateNum = sqlSession.update("chapter.chapterAllowed", chapter);
        return updateNum == 1;
    }

    public List<ChapterEntity> getChapterInfo(SqlSession sqlSession, Long nid) {
        return sqlSession.selectList("chapter.getChapterInfo", nid);
    }

    public boolean updateDraft(SqlSession sqlSession, ChapterEntity chapterEntity) {
        int updateNum = sqlSession.update("chapter.updateDraft", chapterEntity);
        return updateNum == 1;
    }

    public boolean updateContent(SqlSession sqlSession, ChapterEntity chapterEntity) {
        int updateNum = sqlSession.update("chapter.updateContent", chapterEntity);
        return updateNum == 1;
    }

    public boolean deleteDraft(SqlSession sqlSession, Long cid) {
        int updateNum = sqlSession.update("chapter.deleteDraft", cid);
        return updateNum == 1;
    }
}
