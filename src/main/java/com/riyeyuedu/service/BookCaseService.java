package com.riyeyuedu.service;

import com.riyeyuedu.dao.BookCaseDao;
import com.riyeyuedu.entity.BookCaseEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BookCaseService {
    private SqlSession sqlSession;

    private BookCaseDao bookCaseDao;

    public BookCaseService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setBookCaseDao(BookCaseDao bookCaseDao) {
        this.bookCaseDao = bookCaseDao;
    }

    public Boolean addBookCase(BookCaseEntity bookCase) {
        return bookCaseDao.addBookCase(sqlSession, bookCase);
    }

    public Boolean addNovelToBookcase(Map map) {
        return bookCaseDao.addNovelToBookcase(sqlSession, map);
    }

    public BookCaseEntity getBookCase(BookCaseEntity bookCaseEntity) {
        return bookCaseDao.getBookcase(sqlSession, bookCaseEntity);
    }

    public BookCaseEntity isNovelInBookcase(Map map) {
        return bookCaseDao.isNovelInBookcase(sqlSession, map);
    }

    public Boolean deleteNovelInBookCase(Map map) {
        return bookCaseDao.deleteNovelInBookCase(sqlSession, map);
    }

    public Boolean deleteBookcase(Long bid) {
        return bookCaseDao.deleteBookcase(sqlSession, bid);
    }

    public List<Map<String, Object>> getAllBookcase(int uid) {
        List<Map<String, Object>> list = bookCaseDao.getAllBookcase(sqlSession, uid);
        for (Map<String, Object> map : list) {
            if (map.get("type").equals("默认分组")) {
                int index = list.indexOf(map);
                Collections.swap(list, 0, index);
            }
        }
        return list;
    }

    public Integer getBookNumByUid(int uid) {
        return bookCaseDao.getBookNumByUid(sqlSession, uid);
    }

    public BookCaseEntity getDefaultBookcase(int uid) {
        return bookCaseDao.getDefaultBookcase(sqlSession, uid);
    }

    public Boolean removeBookcase(Map map) {
        return bookCaseDao.updateType(sqlSession, map);
    }

    public Boolean toTop(Map map) {
        return bookCaseDao.updateLevel(sqlSession, map);
    }

    public Map<String, Object> getTopNum(Long bid) {
        return bookCaseDao.getTopNum(sqlSession, bid);
    }

    public Map<String, Object> getNovelCount(Long bid) {
        return bookCaseDao.getNovelCount(sqlSession, bid);
    }

    public Boolean updateLevelDown(Map map) {
        return bookCaseDao.updateLevelDown(sqlSession, map);
    }
}
