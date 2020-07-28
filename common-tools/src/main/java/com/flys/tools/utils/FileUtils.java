package com.flys.tools.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 18/09/2018.
 */

public class FileUtils {

    /**
     * elle renvoie l'image bitmat correspondant à un fichier à partir de son uri.
     *
     * @param uri
     * @param context
     * @return
     * @throws IOException
     */
    public static Bitmap getBitmapFromUri(Uri uri, Context context) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /**
     * Elle permet de renvoyer le chemin absolu d'un fichier à partir de son uri et selon le type de la plateforme.
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFileAbsolutPathFromURI(Context context, Uri uri) {

        String path = null;
        if (Build.VERSION.SDK_INT < 11)
            path = RealPathUtils.getRealPathFromURI_BelowAPI11(context, uri);
            // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19)
            path = RealPathUtils.getRealPathFromURI_API11to18(context, uri);
            // SDK > 19 (Android 4.4)
        else
            path = RealPathUtils.getRealPathFromURI_API19(context, uri);
        return path;
    }

    /**
     * Elle permet d'obtenir l'extension d'un fichier.
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getMimeType(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }

    /**
     * Elle permet d'afficher les caractéristiques(la taille, le nom,  d'un fichier.
     *
     * @param uri
     */
    public static void dumpImageMetaData(Context context, Uri uri) {

        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null, null);


        // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
        // "if there's anything to look at, look at it" conditionals.
        if (cursor != null && cursor.moveToFirst()) {
            try {
                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                String displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.e(TAG, "Display Name: " + displayName);

                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.
                String size = null;
                if (!cursor.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    size = cursor.getString(sizeIndex);

                } else {
                    size = "Unknown";
                }
                Log.e(TAG, "Size: " + size);
                Log.e(TAG, "file extension: " + getMimeType(context, uri));

            } finally {
                cursor.close();
            }
        }
    }

    public static String saveToInternalStorage(Bitmap bitmapImage, String fileName, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Cagnottes", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, fileName);

        if (mypath.exists()) {
            mypath.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }


    //Suppression d'un fichier existant
    public static void deleteFileInInternalStorage(String fileName, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Cagnottes", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, fileName);

        if (mypath.exists()) {
            mypath.delete();
        }
    }

    /**
     * @param bitmapImage
     * @param dirName
     * @param fileName
     * @param context
     * @return
     */
    public static String saveToInternalStorage(Bitmap bitmapImage, String dirName, String fileName, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(dirName, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, fileName);

        if (mypath.exists()) {
            mypath.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }

    /**
     * @param bytes
     * @param dirName
     * @param fileName
     * @param context
     * @return
     */
    public static String saveToInternalStorage(byte[] bytes, String dirName, String fileName, Context context) {
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(dirName, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
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
    public static void deleteFileInInternalStorage(String dirName, String fileName, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(dirName, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, fileName);

        if (mypath.exists()) {
            mypath.delete();
        }
    }

    public static String getInternalStorageFolder(Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Cagnottes", Context.MODE_PRIVATE);
        return directory.getAbsolutePath();
    }

    /**
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public static Bitmap getBytes(Context context, Uri uri) throws IOException {
        InputStream iStream = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = iStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return BitmapFactory.decodeByteArray(byteBuffer.toByteArray(), 0, byteBuffer.toByteArray().length);

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
        File file = new File(directory, fileName);
        return file.exists();
    }
}
