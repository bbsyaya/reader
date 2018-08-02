package com.riyeyuedu.dao;

import com.riyeyuedu.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public Boolean addUser(SqlSession sqlSession, UserEntity userEntity) {
        int insertNum = sqlSession.insert("user.insertUser", userEntity);
        return insertNum == 1;
    }

    public Boolean changePassword(SqlSession sqlSession, UserEntity userEntity) {
        int updateNum = sqlSession.update("user.updatePassword", userEntity);
        return updateNum == 1;
    }

    public String getPortraitByUid(SqlSession sqlSession, int uid) {
        return sqlSession.selectOne("user.getPortraitByUid", uid);
    }

    public UserEntity getInfoByUid(SqlSession sqlSession, int uid) {
        return sqlSession.selectOne("user.getInfoByUid", uid);
    }

    public UserEntity getUserByPhone(SqlSession sqlSession, String phone) {
        return sqlSession.selectOne("user.getUserByPhone", phone);
    }

    public UserEntity getUserByUsername(SqlSession sqlSession, String username) {
        return sqlSession.selectOne("user.getUserByUsername", username);
    }

    public UserEntity getReaderByReaderName(SqlSession sqlSession, String username) {
        return sqlSession.selectOne("user.getUserByName", username);
    }

    public Boolean updateAvatar(SqlSession sqlSession, UserEntity userEntity) {
        int updateNum = sqlSession.update("user.updateAvatar", userEntity);
        return updateNum == 1;
    }

    public Boolean updateInfo(SqlSession sqlSession, UserEntity userEntity) {
        int updateNum = sqlSession.update("user.updateInfo", userEntity);
        return updateNum == 1;
    }
}
