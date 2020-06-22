package com.flys.generictools.dao.db;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.generictools.tools.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class DBPersistence implements Serializable {

    private String databaseName;
    private int databaseVersion;
    private List<Class<?>> entities;

    public DBPersistence() {
    }

    public DBPersistence(String databaseName, int databaseVersion, List<Class<?>> entityClasses) {
        this.databaseName = databaseName;
        this.databaseVersion = databaseVersion;
        this.entities = entityClasses;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public int getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(int databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public List<Class<?>> getEntities() {
        return entities;
    }

    public void setEntities(List<Class<?>> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "DBPersistence{" +
                "databaseName='" + databaseName + '\'' +
                ", databaseVersion=" + databaseVersion +
                ", entities=" + entities +
                '}';
    }
}
