package com.riyeyuedu.dao;

import com.riyeyuedu.entity.RecordEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RecordDao {
    public Boolean addRecord(SqlSession sqlSession, RecordEntity record) {
        int insertNum = sqlSession.insert("record.insertRecord", record);
        return insertNum == 1;
    }

    public RecordEntity getRecord(SqlSession sqlSession, RecordEntity record) {
        return sqlSession.selectOne("record.getRecord", record);
    }

    public List<Map<String, Object>> getAllRecordDetail(SqlSession sqlSession, int uid) {
        return sqlSession.selectList("record.getAllRecordDetail", uid);
    }

    public Map<String, Object> getRecordDetail(SqlSession sqlSession, RecordEntity recordEntity) {
        return sqlSession.selectOne("record.getRecordDetail", recordEntity);
    }

    public Boolean deleteRecord(SqlSession sqlSession, RecordEntity record) {
        int deleteNum = sqlSession.delete("record.deleteRecord", record);
        return deleteNum == 1;
    }

    public Boolean updateRecord(SqlSession sqlSession, RecordEntity recordEntity) {
        int updateNum = sqlSession.update("record.updateRecord", recordEntity);
        return updateNum == 1;
    }
}
