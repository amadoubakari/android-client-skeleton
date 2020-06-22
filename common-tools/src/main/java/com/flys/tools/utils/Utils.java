package com.flys.tools.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;

public class Utils implements Serializable {

    /**
     * apply a font on a menu
     *
     * @param context
     * @param menu
     * @param fontPath path to the font
     */
    public static void applyFontStyleToMenu(Context context, Menu menu, String fontPath) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = menuItem.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(context, subMenuItem, fontPath);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(context, menuItem, fontPath);
        }
    }

    /**
     * @param context
     * @param mi
     * @param fontPath
     */
    private static void applyFontToMenuItem(Context context, MenuItem mi, String fontPath) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), fontPath);
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    //Elle permet de partager un texte avec d'autres applications(facebook, whatsapp, twitter, skype, etc. ), ce texte peut être une URL

    /**
     * @param context
     * @param subject object d'envoie du fichier
     * @param text    le texte à envoyer et ça peut être une URL
     * @param title   le titre de la fenêtre qui va s'afficher
     */
    public static void shareText(Context context, String subject, String text, String title) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, subject);
        share.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(share, title));
    }

    /**
     * Elle permet de partager un fichier
     *
     * @param context
     */
    public static void shareFile(Context context) {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "/myImage.png";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        context.startActivity(Intent.createChooser(share, "Share Image!"));
    }

    /**
     * Read html file from assets
     *
     * @param filename
     * @param webView
     */
    public static void readHtmlFileFromAssets(Context context, String filename, WebView webView) {

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);

        AssetManager mgr = context.getAssets();
        try {
            InputStream in = mgr.open(filename, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            webView.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param in
     * @return
     * @throws IOException
     */
    private static String StreamToString(InputStream in) throws IOException {

        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }

    /**
     * @param context
     * @param view
     * @param msg
     */
    public static Snackbar showErrorMessage(Context context, View view, String msg) {
        return Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", v -> {

                });
    }
}
