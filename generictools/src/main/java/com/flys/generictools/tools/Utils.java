package com.flys.generictools.tools;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.generictools.dao.db.DBPersistence;
import com.flys.generictools.dao.db.Entities;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class Utils implements Serializable {

    /**
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String loadJSONFromAsset(Context context, String fileName) {

        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     *
     * @param context
     * @return
     */
    public static final DBPersistence getPersistanceData(Context context) {
        DBPersistence dbPersistence = new DBPersistence();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(context, "persistence.json");
        try {
            dbPersistence = mapper.readValue(jsonInput, DBPersistence.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbPersistence;
    }
}
