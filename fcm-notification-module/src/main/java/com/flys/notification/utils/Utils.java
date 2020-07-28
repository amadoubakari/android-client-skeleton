package com.flys.notification.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.flys.notification.R;
import com.flys.notification.domain.Notification;
import com.flys.notification.domain.Notifications;
import com.flys.notification.fragment.NotificationFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    /**
     * Lire un fichier à partir d'un système de fichier
     *
     * @param path
     * @param fileName
     * @param context
     * @return
     */
    public static BitmapDrawable loadImageFromStorage(String path, String fileName, Context context) {

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(path, Context.MODE_PRIVATE);
        path = directory.getAbsolutePath();
        BitmapDrawable background = null;
        try {
            File f = new File(path, fileName);
            if (f.exists() && f.canRead()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                background = new BitmapDrawable(context.getResources(), bitmap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return background;
    }
    /**
     * Suppression d'un fichier existant
     *
     * @param fileName
     * @param context
     */
    public static boolean fileExist(String dirName, String fileName, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(dirName, Context.MODE_PRIVATE);
        // Create imageDir
        if(!directory.exists()){
           return directory.exists();
        }
        File file = new File(directory, fileName);
        return file.exists();
    }
}
