package com.flys.notification.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAdOptions;

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
        if (!directory.exists()) {
            return directory.exists();
        }
        File file = new File(directory, fileName);
        return file.exists();
    }

    /**
     *
     * @param context
     * @param templateView
     * @param adsId
     * @param adsNotificationContainer
     */
    public static void initializeAds(Context context, TemplateView templateView, String adsId, ConstraintLayout adsNotificationContainer) {
        AdLoader adLoader = new AdLoader.Builder(context,adsId)
                .forNativeAd(nativeAd -> {
                    NativeTemplateStyle styles = new
                            NativeTemplateStyle.Builder()
                            //.withMainBackgroundColor(new ColorDrawable(context.getColor(R.color.gnt_white)))
                            //.withPrimaryTextTypeface(ResourcesCompat.getFont(context,R.font.google_sans))
                            .build();

                    //TemplateView template = findViewById(R.id.my_template);
                    //templateView.setStyles(styles);
                    templateView.setNativeAd(nativeAd);
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        adsNotificationContainer.setVisibility(View.GONE);
                        // Handle the failure by logging, altering the UI, and so on.
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        adsNotificationContainer.setVisibility(View.VISIBLE);
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
}
