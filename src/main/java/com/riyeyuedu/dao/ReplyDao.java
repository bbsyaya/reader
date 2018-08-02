package com.riyeyuedu.dao;

import com.riyeyuedu.entity.ReplyEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReplyDao {
    public Boolean addReply(SqlSession sqlSession, ReplyEntity replyEntity) {
        int addNum = sqlSession.insert("reply.insertReply", replyEntity);
        return addNum == 1;
    }

    public List<Map<String, Object>> getReplyByPid(SqlSession sqlSession, Long pid) {
        return sqlSession.selectList("reply.getReplyByPid", pid);
    }
}
