package com.flys.generictools.dao.db;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.generictools.tools.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class PersistenceFile implements Serializable {

    private static final PersistenceFile ourInstance = new PersistenceFile();
    private static Context context;

    public static PersistenceFile getInstance(Context context1) {
        context = context1;
        return ourInstance;
    }

    private PersistenceFile() {
    }

    private List<Class<?>> entityClasses;

    /**
     * get entity classes from persistence file
     *
     * @return
     */
    public List<Class<?>> getEntityClasses() {
        Entities entities = new Entities();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(context, "persistence.json");
        //Log.e(getClass().getSimpleName(), "result:" + jsonInput);
        try {
            entities = mapper.readValue(jsonInput, Entities.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities.getEntities();
    }

    public void setEntityClasses(List<Class<?>> entityClasses) {
        this.entityClasses = entityClasses;
    }
}
