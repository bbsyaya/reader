package com.riyeyuedu.service;

import com.riyeyuedu.dao.UserDao;
import com.riyeyuedu.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    private SqlSession sqlSession;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Boolean register(UserEntity userEntity) {
        return userDao.addUser(sqlSession, userEntity);
    }

    public Boolean changePassword(UserEntity userEntity) {
        return userDao.changePassword(sqlSession, userEntity);
    }

    public String getPortraitByUid(int uid) {
        return userDao.getPortraitByUid(sqlSession, uid);
    }

    public UserEntity getInfoByUid(int uid) {
        return userDao.getInfoByUid(sqlSession, uid);
    }

    public UserEntity getUserByPhone(String phone) {
        return userDao.getUserByPhone(sqlSession, phone);
    }

    public UserEntity getUserByUsername(String username) {
        return userDao.getUserByUsername(sqlSession, username);
    }

    public UserEntity getReaderByName(String name) {
        return userDao.getReaderByReaderName(sqlSession, name);
    }

    public Boolean updateAvatar(UserEntity userEntity) {
        return userDao.updateAvatar(sqlSession, userEntity);
    }

    public Boolean updateInfo(UserEntity userEntity) {
        return userDao.updateInfo(sqlSession, userEntity);
    }
}
