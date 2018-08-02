package com.riyeyuedu.dao;

import com.riyeyuedu.entity.BookCaseEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookCaseDao {
    public Boolean addBookCase(SqlSession sqlSession, BookCaseEntity bookCase) {
        int addNum = sqlSession.insert("bookCase.insertBookCase", bookCase);
        return addNum == 1;
    }

    public Boolean addNovelToBookcase(SqlSession sqlSession, Map map) {
        int addNum = sqlSession.insert("bookCase.insertNovelToBookcase", map);
        return addNum == 1;
    }

    public BookCaseEntity getBookcase(SqlSession sqlSession, BookCaseEntity bookCaseEntity) {
        return sqlSession.selectOne("bookCase.getBookcase", bookCaseEntity);
    }

    public BookCaseEntity isNovelInBookcase(SqlSession sqlSession, Map map) {
        return sqlSession.selectOne("bookCase.isNovelInBookcase", map);
    }

    public Boolean deleteNovelInBookCase(SqlSession sqlSession, Map map) {
        int deleteNum = sqlSession.delete("bookCase.deleteNovelInBookCase", map);
        return deleteNum == 1;
    }

    public Boolean deleteBookcase(SqlSession sqlSession, Long bid) {
        int deleteNum = sqlSession.delete("bookCase.deleteBookcase", bid);
        return deleteNum == 1;
    }

    public List<Map<String, Object>> getAllBookcase(SqlSession sqlSession, int uid) {
        return sqlSession.selectList("bookCase.getAllBookcase", uid);
    }

    public Integer getBookNumByUid(SqlSession sqlSession, int uid) {
        return sqlSession.selectOne("bookCase.getBookNumByUid", uid);
    }

    public BookCaseEntity getDefaultBookcase(SqlSession sqlSession, int uid) {
        return sqlSession.selectOne("bookCase.getDefaultBookcase", uid);
    }

    public Boolean updateType(SqlSession sqlSession, Map map) {
        int updateNum = sqlSession.update("bookCase.updateType", map);
        return updateNum == 1;
    }

    public Boolean updateLevel(SqlSession sqlSession, Map map) {
        int updateNum = sqlSession.update("bookCase.updateLevel", map);
        return updateNum == 1;
    }

    public Map<String, Object> getTopNum(SqlSession sqlSession, Long bid) {
        return sqlSession.selectOne("bookCase.getTopNum", bid);
    }

    public Map<String, Object> getNovelCount(SqlSession sqlSession, Long bid) {
        return sqlSession.selectOne("bookCase.getNovelCount", bid);
    }

    public Boolean updateLevelDown(SqlSession sqlSession, Map map) {
        int updateNum = sqlSession.update("bookCase.updateLevelDown", map);
        return updateNum == 1;
    }
}