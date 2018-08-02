package com.riyeyuedu.service;

import com.riyeyuedu.dao.MessageDao;
import com.riyeyuedu.entity.MessageEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    private MessageDao messageDao;

    private SqlSession sqlSession;

    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Boolean addMessage(MessageEntity messageEntity) {
        return messageDao.addMessage(sqlSession, messageEntity);
    }

    public List<Map<String, Object>> getMessageByToId(Map map) {
        return messageDao.getMessageByToId(sqlSession, map);
    }

    public MessageEntity getMessageByMid(Long mid) {
        messageDao.updateRead(sqlSession, mid);
        return messageDao.getMessageByMid(sqlSession, mid);
    }

    public Integer getUnReadMessageNum(int uid) {
        return messageDao.getUnReadMessageNum(sqlSession, uid);
    }

    public Boolean deleteMessage(Long mid) {
        return messageDao.deleteMessageByMid(sqlSession, mid);
    }
}
