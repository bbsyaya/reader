package com.riyeyuedu.dao;

import com.riyeyuedu.entity.AttitudeEntity;
import com.riyeyuedu.entity.PostEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDao {
    public Boolean addPost(SqlSession sqlSession, PostEntity post) {
        int addNum = sqlSession.insert("post.insertPost", post);
        return addNum == 1;
    }

    public List<Map<String, Object>> getPostByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectList("post.selectPostByNid", nid);
    }

    public Map<String, Object> getPostByPid(SqlSession sqlSession, Long pid) {
        return sqlSession.selectOne("post.getPostByPid", pid);
    }

    public Integer getPostNumByNid(SqlSession sqlSession, Long nid) {
        return sqlSession.selectOne("post.getPostNumByNid", nid);
    }

    public Boolean addCommentNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.addCommentNum", pid);
        return updateNum == 1;
    }

    public Boolean reduceCommentNUm(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.reduceCommentNum", pid);
        return updateNum == 1;
    }

    public Boolean addLikeNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.addLikeNum", pid);
        return updateNum == 1;
    }

    public Boolean reduceLikeNum(SqlSession sqlSession, Long pid) {
        int updateNum = sqlSession.update("post.reduceLikeNum", pid);
        return updateNum == 1;
    }

    public Boolean addAttitude(SqlSession sqlSession, AttitudeEntity attitudeEntity) {
        int addNum = sqlSession.insert("post.insertAttitude", attitudeEntity);
        return addNum == 1;
    }

    public Integer isAttitude(SqlSession sqlSession, AttitudeEntity attitudeEntity) {
        return sqlSession.selectOne("post.isAttitude", attitudeEntity);
    }

    public Boolean updateAttitude(SqlSession sqlSession, AttitudeEntity attitudeEntity) {
        int updateNum = sqlSession.update("post.updateAttitude", attitudeEntity);
        return updateNum == 1;
    }
}
