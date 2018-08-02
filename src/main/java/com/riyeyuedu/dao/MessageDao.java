package com.riyeyuedu.dao;

import com.riyeyuedu.entity.MessageEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MessageDao {

    public Boolean addMessage(SqlSession sqlSession, MessageEntity messageEntity) {
        int addNum = sqlSession.insert("message.insertMessage", messageEntity);
        return addNum == 1;
    }

    public List<Map<String, Object>> getMessageByToId(SqlSession sqlSession, Map map) {
        return sqlSession.selectList("message.getMessageByToId", map);
    }

    public MessageEntity getMessageByMid(SqlSession sqlSession, Long mid) {
        return sqlSession.selectOne("message.getMessageByMid", mid);
    }

    public Integer getUnReadMessageNum(SqlSession sqlSession, int uid) {
        return sqlSession.selectOne("message.getUnReadMessageNum", uid);
    }

    public Boolean deleteMessageByMid(SqlSession sqlSession, Long mid) {
        int deleteNum = sqlSession.delete("message.deleteMessageByMid", mid);
        return deleteNum == 1;
    }

    public Boolean updateRead(SqlSession sqlSession, Long mid) {
        int updateNum = sqlSession.update("message.updateRead", mid);
        return updateNum == 1;
    }
}
