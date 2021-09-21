package com.flys.notification.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@DatabaseTable(tableName = "notification")
public class Notification implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String subTitle;
    @DatabaseField
    private String content;
    @DatabaseField
    private String imageUrl;
    @DatabaseField(dataType = DataType.DATE_STRING, canBeNull = false)
    private Date date;
    @DatabaseField
    private String imageName;
    @DatabaseField(canBeNull = false, defaultValue = "false")
    private boolean seen;
    @DatabaseField
    private String source;
    @DatabaseField
    private String sourceIcon;

    private byte[] image;

    public Notification() {
    }

    public Notification(String title, String subTitle, String content) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

    public Notification(String title, String subTitle, String content, String imageUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Notification(String title, String subTitle, String content, String imageUrl, byte[] image) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.imageUrl = imageUrl;
        this.image = image;
    }

    public Notification(String title, String subTitle, String content, String imageUrl, Date date, byte[] image) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.image = image;
    }

    public Notification(String title, String subTitle, String content, String imageUrl, Date date, String imageName, byte[] image) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.imageName = imageName;
        this.image = image;
    }

    public Notification(String title, String subTitle, String content, String imageUrl, Date date, String imageName, boolean seen, byte[] image) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.imageName = imageName;
        this.seen = seen;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceIcon() {
        return sourceIcon;
    }

    public void setSourceIcon(String sourceIcon) {
        this.sourceIcon = sourceIcon;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", imageName='" + imageName + '\'' +
                ", seen=" + seen +
                ", source='" + source + '\'' +
                ", sourceIcon='" + sourceIcon + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
