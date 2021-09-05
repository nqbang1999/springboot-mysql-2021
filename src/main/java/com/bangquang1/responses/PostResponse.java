package com.bangquang1.responses;

import com.bangquang1.models.Image;
import com.bangquang1.models.User;
import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private User user;
    private String dateTime;
    private Image image;
    private String imgURL;

    public PostResponse(Long id, String title, String content, User user, Image image) {
    }

//    public PostResponse(Long id, String title, String content, User user, LocalDateTime dateTime, Image image) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.user = user;
//        this.dateTime = dateTime;
//        this.image = image;
//    }

//    public PostResponse(Long id, String title, String content, User user, LocalDateTime dateTime, Image image, String imgURL) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.user = user;
//        this.dateTime = dateTime;
//        this.image = image;
//        this.imgURL = imgURL;
//    }


    public PostResponse(Long id, String title, String content, User user, String dateTime, Image image, String imgURL) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.dateTime = dateTime;
        this.image = image;
        this.imgURL = imgURL;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
