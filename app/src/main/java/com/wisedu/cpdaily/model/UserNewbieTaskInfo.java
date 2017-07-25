package com.wisedu.cpdaily.model;

/**
 * 新手任务详情
 * Created by wjj on 2017/7/11 14:05.
 */

public class UserNewbieTaskInfo {
    /**
     * isCompleted : false
     * totalLikNum : 10
     * likeNum : 7
     * totalCommentNum : 5
     * commentNum : 0
     */

    private boolean isCompleted;
    private int totalLikNum;
    private int likeNum;
    private int totalCommentNum;
    private int commentNum;

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getTotalLikNum() {
        return totalLikNum;
    }

    public void setTotalLikNum(int totalLikNum) {
        this.totalLikNum = totalLikNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getTotalCommentNum() {
        return totalCommentNum;
    }

    public void setTotalCommentNum(int totalCommentNum) {
        this.totalCommentNum = totalCommentNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
