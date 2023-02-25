package com.raphaelframos.refii.comment;

import com.raphaelframos.refii.common.utils.DateUtils;
import com.raphaelframos.refii.common.utils.UserLevelUtils;

public class CommentResponse {

    private Long id;
    private String message;
    private String date;
    private String userPhoto;
    private String userName;
    private String userLevel;
    private boolean my;

    public CommentResponse(Comment comment, boolean author) {
        setMy(author);
        setUserLevel(UserLevelUtils.getLevel(comment.getProfile().getPoints()));
        setUserName(comment.getProfile().getName());
        setUserPhoto(comment.getProfile().getPhoto());
        setMessage(comment.getMessage());
        setDate(DateUtils.date(comment.getDate()));
        setId(comment.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public boolean isMy() {
        return my;
    }

    public void setMy(boolean my) {
        this.my = my;
    }
}
