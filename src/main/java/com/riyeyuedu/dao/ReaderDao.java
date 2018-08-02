package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ReaderEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderDao {
    public Boolean addReader(SqlSession sqlSession, ReaderEntity readerEntity) {
        int insertNum = sqlSession.insert("reader.insertReader", readerEntity);
        return insertNum == 1;
    }

    public Boolean changePassword(SqlSession sqlSession, ReaderEntity readerEntity) {
        int updateNum = sqlSession.update("reader.updatePassword", readerEntity);
        return updateNum == 1;
    }

    public String getPortraitByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectOne("reader.getPortraitByRid", rid);
    }

    public ReaderEntity getInfoByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectOne("reader.getInfoByRid", rid);
    }

    public ReaderEntity getReader(SqlSession sqlSession, ReaderEntity reader) {
        return sqlSession.selectOne("reader.getReader", reader);
    }

    public ReaderEntity getReaderByPhone(SqlSession sqlSession, String phone) {
        return sqlSession.selectOne("reader.getReaderByPhone", phone);
    }

    public ReaderEntity getReaderByUsername(SqlSession sqlSession, String username) {
        return sqlSession.selectOne("reader.getReaderByUsername", username);
    }

    public ReaderEntity getReaderByReaderName(SqlSession sqlSession, String readerName) {
        return sqlSession.selectOne("reader.getReaderByName", readerName);
    }

    public String getReaderRoleByRid(SqlSession sqlSession, int rid) {
        return sqlSession.selectOne("reader.getReaderRoleByRid", rid);
    }

    public Boolean updateAvatar(SqlSession sqlSession, ReaderEntity reader) {
        int updateNum = sqlSession.update("reader.updateAvatar", reader);
        return updateNum == 1;
    }

    public Boolean updateInfo(SqlSession sqlSession, ReaderEntity readerEntity) {
        int updateNum = sqlSession.update("reader.updateInfo", readerEntity);
        return updateNum == 1;
    }
}
