package com.flys.notification.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @since 30/05/2020 13:13
 */
public class Notifications implements Serializable {

    private List<Notification> notifications;

    public Notifications() {
    }

    public Notifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
