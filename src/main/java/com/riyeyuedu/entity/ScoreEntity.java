package com.riyeyuedu.entity;

public class ScoreEntity {
    private int uid;

    private Long nid;

    private int score;

    private String content;

    private Long time;

    private Integer likeNum;

    public int getUid() {
        return uid;
    }

    public void setUid(int rid) {
        this.uid = uid;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
}
