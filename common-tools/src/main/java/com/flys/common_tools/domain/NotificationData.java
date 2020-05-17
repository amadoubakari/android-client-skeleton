package com.flys.common_tools.domain;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class NotificationData implements Serializable {
    private String title;
    private String body;
    private String okMsg;
    private String noMsg;
    private Drawable icon;
    private int resourcesThemes;

    public NotificationData() {
    }

    public NotificationData(String title, String body, Drawable icon) {
        this.title = title;
        this.body = body;
        this.icon = icon;
    }

    public NotificationData(String title, String body, Drawable icon, int resourcesThemes) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.resourcesThemes = resourcesThemes;
    }

    public NotificationData(String title, String body, String okMsg, String noMsg, Drawable icon, int resourcesThemes) {
        this.title = title;
        this.body = body;
        this.okMsg = okMsg;
        this.noMsg = noMsg;
        this.icon = icon;
        this.resourcesThemes = resourcesThemes;
    }

    public NotificationData(String title, String body, String oui, String no, Drawable drawable) {
        this.title = title;
        this.body = body;
        this.okMsg = oui;
        this.noMsg = no;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getResourcesThemes() {
        return resourcesThemes;
    }

    public void setResourcesThemes(int resourcesThemes) {
        this.resourcesThemes = resourcesThemes;
    }

    public String getOkMsg() {
        return okMsg;
    }

    public void setOkMsg(String okMsg) {
        this.okMsg = okMsg;
    }

    public String getNoMsg() {
        return noMsg;
    }

    public void setNoMsg(String noMsg) {
        this.noMsg = noMsg;
    }
}
