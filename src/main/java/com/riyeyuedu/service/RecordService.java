package com.riyeyuedu.service;

import com.riyeyuedu.dao.RecordDao;
import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecordService {
    private SqlSession sqlSession;

    private RecordDao recordDao;

    public RecordService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Autowired
    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public Boolean addRecord(RecordEntity record) {
        return recordDao.addRecord(sqlSession, record);
    }

    public RecordEntity getRecord(RecordEntity record) {
        return recordDao.getRecord(sqlSession, record);
    }

    public List<Map<String, Object>> getAllRecordDetail(int uid) {
        return recordDao.getAllRecordDetail(sqlSession, uid);
    }

    public Map<String, Object> getRecordDetail(RecordEntity recordEntity) {
        return recordDao.getRecordDetail(sqlSession, recordEntity);
    }

    public Boolean deleteRecord(RecordEntity record) {
        return recordDao.deleteRecord(sqlSession, record);
    }

    public Boolean updateRecord(RecordEntity recordEntity) {
        return recordDao.updateRecord(sqlSession, recordEntity);
    }
}
