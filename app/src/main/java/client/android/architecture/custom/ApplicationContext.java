package client.android.architecture.custom;

import android.app.Application;
import android.content.Context;

public class ApplicationContext extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void attachBaseContext(Context base) {
        //MultiDex.install(base);
        super.attachBaseContext(base);
    }

    public static Context getContext() {
        return mContext;
    }
}