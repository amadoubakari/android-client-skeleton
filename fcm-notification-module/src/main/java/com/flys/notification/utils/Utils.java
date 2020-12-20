package com.flys.notification.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @goal list of functions used by app
 * @since 30/05/2020
 */
public class Utils implements Serializable {
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
