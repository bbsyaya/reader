package com.riyeyuedu.dao;

import com.riyeyuedu.entity.BookCaseEntity;
import com.riyeyuedu.entity.NovelEntity;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阳溢 on 2018/1/5.
 */
@Repository
public class NovelDao {

    public boolean addNovel(SqlSession sqlSession, NovelEntity novel) {
        int insertNum = sqlSession.insert("novel.insertNovel", novel);
        return insertNum == 1;
    }

    public Map<String, Object> getNovelByNid(SqlSession sqlSession, long nid) {
        return sqlSession.selectOne("novel.getNovelByNid", nid);
    }

    public Map<String, Object> getTitleByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("novel.getTitleByNid", nid);
    }

    public List<Map<String, Object>> getNovelByStateL10(SqlSession sqlSession, int state) {
        return sqlSession.selectList("novel.getNovelByStateL10", state);
    }

    public List<Map<String, Object>> getAllNovel(SqlSession sqlSession, Map<String, Object> map) {
        return sqlSession.selectList("novel.getAllNovel", map);
    }

    public void addClickNum (SqlSession sqlSession, long nid) {
        sqlSession.update("novel.addClickNum", nid);
    }

    public List<Map<String, Object>> getNovelByLidL5(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLidL5", lid);
    }

    public List<Map<String, Object>> getNovelByRecentL23(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByRecentL23");
    }

    public List<Map<String, Object>> getNovelByScoreL10(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByScoreL10");
    }

    public List<Map<String, Object>> getNovelByScoreL17(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByScoreL17");
    }

    public List<Map<String, Object>> getScoreNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getScoreNovel");
        } else {
            return sqlSession.selectList("novel.getScoreNovelByLid", lid);
        }

    }

    public List<Map<String, Object>> getNovelByNewL10(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByNewL10");
    }

    public List<Map<String, Object>> getNovelByNewL17(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByNewL17");
    }

    public List<Map<String, Object>> getNovelByClickL10(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByClickL10");
    }

    public NovelEntity getNovelByTitle(SqlSession sqlSession, String title) {
        return sqlSession.selectOne("novel.getNovelByTitle", title);
    }

    public List<Map<String, Object>> getNovelByLidRecent(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLidRecent", lid);
    }

    public List<Map<String, Object>> getNovelByLidScore(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getNovelByLidScore", lid);
    }

    public List<Map<String, Object>> getNovelInBookCase(SqlSession sqlSession, BookCaseEntity bookCaseEntity) {
        return sqlSession.selectList("novel.getNovelInBookCase", bookCaseEntity);
    }

    public List<Map<String, Object>> getInBookCaseByTitle(SqlSession sqlSession, Map map) {
        return sqlSession.selectList("novel.getInBookcaseByTitle", map);
    }

    public Map<String, Object> getNovelByRecord(SqlSession sqlSession, RecordEntity recordEntity) {
        return sqlSession.selectOne("novel.getNovelByRecord", recordEntity);
    }

    public List<Map<String, Object>> getNovelByTitleWidly(SqlSession sqlSession, Map map) {
        return sqlSession.selectList("novel.getNovelByTitleWidly", map);
    }

    public List<Map<String, Object>> getNovelByAuthorName(SqlSession sqlSession, String authorName) {
        return sqlSession.selectList("novel.getNovelByAuthor", authorName);
    }

    public NovelEntity getOneInBookCase(SqlSession sqlSession, BookCaseEntity bookCase) {
        return sqlSession.selectOne("novel.getOneInBookCase", bookCase);
    }

    public List<Map<String, Object>> getNotAllowedNovel(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNotAllowedNovel");
    }

    public List<Map<String, Object>> getNovelByRecommendL10(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getNovelByRecommendL10");
    }

    public List<Map<String, Object>> getNewNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getNewNovel");
        } else {
            return sqlSession.selectList("novel.getNewNovelByLid", lid);
        }
    }

    public List<Map<String, Object>> getNewNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getNewNovelL10");
        } else {
            return sqlSession.selectList("novel.getNewNovelByLidL10", lid);
        }
    }

    public List<Map<String, Object>> getNewNovelByLidL23(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getNewNovelL23");
        } else {
            return sqlSession.selectList("novel.getNewNovelByLidL23", lid);
        }
    }

    public List<Map<String, Object>> getPopularNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getPopularNovel");
        } else {
            return sqlSession.selectList("novel.getPopularNovelByLid", lid);
        }
    }

    public List<Map<String, Object>> getPopularNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getPopularNovelL10");
        } else {
            return sqlSession.selectList("novel.getPopularNovelByLidL10", lid);
        }
    }

    public List<Map<String, Object>> getUpdateNovelByLid(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getUpdateNovelByLid", lid);
    }

    public List<Map<String, Object>> getFinishNovelByLid(SqlSession sqlSession, Map m) {
        if ((int) m.get("lid") == 0) {
            return sqlSession.selectList("novel.getFinishNovel", m);
        } else {
            return sqlSession.selectList("novel.getFinishNovelByLid", m);
        }
    }

    public List<Map<String, Object>> getFinishNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getFinishNovelL10");
        } else {
            return sqlSession.selectList("novel.getFinishNovelByLidL10", lid);
        }
    }

    public List<Map<String, Object>> getRecommendNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getRecommendNovel");
        } else {
            return sqlSession.selectList("novel.getRecommendNovelByLid", lid);
        }
    }

    public List<Map<String, Object>> getRecommendNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getRecommendNovelL10");
        } else {
            return sqlSession.selectList("novel.getRecommendNovelByLidL10", lid);
        }
    }

    public List<Map<String, Object>> getRecommendNovelByLidL15(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getRecommendNovelL15");
        } else {
            return sqlSession.selectList("novel.getRecommendNovelByLidL15", lid);
        }
    }

    public List<Map<String, Object>> getClickNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getClickNovel");
        } else {
            return sqlSession.selectList("novel.getClickNovelByLid", lid);
        }
    }

    public List<Map<String, Object>> getClickNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getClickNovelL10");
        } else {
            return sqlSession.selectList("novel.getClickNovelByLidL10", lid);
        }
    }

    public List<NovelEntity> getEditNovel(SqlSession sqlSession) {
        return sqlSession.selectList("novel.getEditNovel");
    }

    public List<Map<String, Object>> getCollectNovelByLid(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getCollectNovel");
        } else {
            return sqlSession.selectList("novel.getCollectNovelByLid", lid);
        }
    }

    public List<Map<String, Object>> getCollectNovelByLidL10(SqlSession sqlSession, int lid) {
        if (lid == 0) {
            return sqlSession.selectList("novel.getCollectNovelL10");
        } else {
            return sqlSession.selectList("novel.getCollectNovelByLidL10", lid);
        }
    }

    public List<Map<String, Object>> getAllNovelByLid(SqlSession sqlSession, int lid) {
        return sqlSession.selectList("novel.getAllNovelByLid", lid);
    }

    public List<Map<String, Object>> getNovelByBookName(SqlSession sqlSession, String bookName) {
        return sqlSession.selectList("novel.getNovelByBookName", bookName);
    }

    public boolean addChapterNum(SqlSession sqlSession, Long nid) {
        int updateNum = sqlSession.update("novel.addChapterNum", nid);
        return updateNum == 1;
    }

    public Boolean updateScore(SqlSession sqlSession, Map map) {
        int updateNum = sqlSession.update("novel.updateScore", map);
        return updateNum == 1;
    }

    public List<Map<String, Object>> getNovelByUid(SqlSession sqlSession, int uid) {
        return sqlSession.selectList("novel.getNovelByUid", uid);
    }
}
