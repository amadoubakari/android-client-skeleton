package client.android.config;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.Serializable;

import client.android.dao.db.User;

/**
 * Created by User on 23/10/2018.
 */

public class DBConfig extends OrmLiteConfigUtil implements Serializable {

    private static final Class<?>[] classes = new Class[] {
            User.class,
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}