package com.flys.generictools.dao.daoImpl;

import com.flys.generictools.dao.IDao.GenericDao;
import com.flys.generictools.dao.daoException.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle permet d'assurer l'accès à la base de données(DAO).
 * @email : amadou_bakari@yahoo.fr
 * @tel (+237) 674316936 / 690660199
 * @since 14/05/2018
 */

public abstract class GenericDaoImpl<T, K> implements GenericDao<T, K> {

    private Class<T> entityClass=getEntityClassManaged();

    @Override
    public T save(T t) throws DaoException {
        T result;
        if (t == null) {
            throw new DaoException("Entité null");
        }

        try {
            result = getDao().createIfNotExists(t);
            flush();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean processBeforeSave(T t) {
        return true;
    }

    @Override
    public boolean processAfterSave(T t) {
        return true;
    }

    @Override
    public T update(T t) {
        try {
            getDao().update(t);
            flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean processBeforeUpdate(T t) {
        return false;
    }

    @Override
    public boolean processAfterUpdate(T t) {
        return false;
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> result = null;
        try {
            result = getDao().queryForAll();
            //flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<T> findByPropertyName(String propertyName, Object value) throws DaoException {
        List<T> result = null;
        try {
            result = getDao().queryForEq(propertyName, value);
            flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T findById(K k) throws DaoException {
        T result = null;
        try {
            result = getDao().queryForId(k);
            flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(T t) throws DaoException {
        boolean result = false;
        if (t == null) {
            throw new DaoException("entity null");
        }
        try {
            int index = getDao().delete(t);
            flush();
            if (index >= 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteById(K k) throws DaoException {
        boolean result = false;
        if (k == null) {
            throw new DaoException("entity null");
        }
        try {
            int index = getDao().deleteById(k);
            flush();
            if (index >= 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
