package com.flys.notification.utils;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;
import com.flys.notification.domain.Notifications;
import com.flys.notification.fragment.NotificationFragment;

import java.io.Serializable;
import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @goal list of functions used by app
 * @since 30/05/2020
 */
public class Utils implements Serializable {
    /**
     * Launch notification interface
     */
    public static void startNotification(FragmentActivity fragmentActivity, int container, List<Notification> notifications) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("notifications", new Notifications(notifications));
        NotificationFragment notificationFragment = new NotificationFragment();
        notificationFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, notificationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
