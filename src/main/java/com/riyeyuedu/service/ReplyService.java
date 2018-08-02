package com.riyeyuedu.service;

import com.riyeyuedu.dao.ReplyDao;
import com.riyeyuedu.entity.ReplyEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReplyService {
    private ReplyDao replyDao;

    private SqlSession sqlSession;

    @Autowired
    public void setReplyDao(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Boolean addReply(ReplyEntity replyEntity) {
        return replyDao.addReply(sqlSession, replyEntity);
    }

    public List<Map<String, Object>> getReplyByPid(Long pid) {
        return replyDao.getReplyByPid(sqlSession, pid);
    }
}
