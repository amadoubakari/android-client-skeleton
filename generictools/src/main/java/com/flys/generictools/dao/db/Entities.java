package com.flys.generictools.dao.db;

import java.io.Serializable;
import java.util.List;

public class Entities implements Serializable {

    private List<Class<?>> entities;

    public Entities() {
    }

    public List<Class<?>> getEntities() {
        return entities;
    }

    public void setEntities(List<Class<?>> entities) {
        this.entities = entities;
    }
}
