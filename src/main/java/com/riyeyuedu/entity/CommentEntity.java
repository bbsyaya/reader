package com.riyeyuedu.entity;

public class CommentEntity {
    private Long cid;

    private int ownerReaderId;

    private int targetReaderId;

    private String content;

    private int likeNum;

    private String createTime;

    private Long parentId;

    private int flag;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getOwnerReaderId() {
        return ownerReaderId;
    }

    public void setOwnerReaderId(int ownerReaderId) {
        this.ownerReaderId = ownerReaderId;
    }

    public int getTargetReaderId() {
        return targetReaderId;
    }

    public void setTargetReaderId(int targetReaderId) {
        this.targetReaderId = targetReaderId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
