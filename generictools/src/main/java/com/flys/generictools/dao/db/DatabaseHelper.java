package com.flys.generictools.dao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.generictools.tools.Utils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 07/05/2018.
 */

public class DatabaseHelper<T, K> extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something appropriate for your app
    //private static final String DATABASE_NAME = "flys.db";
    // any time you make changes to your database objects, you may have to increase the database version
    //private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    //private Dao<T, K> simpleDao = null;
    //private RuntimeExceptionDao<T, K> simpleRuntimeDao = null;

    List<Class<?>> classList;

    public DatabaseHelper(Context context, int ormlite_config) {
        super(context, Utils.getPersistanceData(context).getDatabaseName(), null, Utils.getPersistanceData(context).getDatabaseVersion(), ormlite_config);
        this.classList = Utils.getPersistanceData(context).getEntities();
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            for (Class<?> entityClass : classList
            ) {
                TableUtils.createTableIfNotExists(connectionSource, entityClass);
            }

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            for (Class<?> entityClass : classList
            ) {
                TableUtils.dropTable(connectionSource, entityClass, true);
            }

            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

   /* public List<Class<?>> getEntityClasses(Context context) {
        Entities entities = new Entities();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(context, "persistence.json");
        try {
            entities = mapper.readValue(jsonInput, Entities.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities.getEntities();
    }*/
}
