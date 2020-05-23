package com.flys.generictools.dao.IDao;

import com.flys.generictools.dao.daoException.DaoException;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Elle définie toutes fonctionnalités qu'une dao doit implémenter.
 * @email: amadou_bakari@yahoo.fr
 * @tel: (+237) 674316936/ 690660199
 * @since 14/05/2018
 */
public interface GenericDao<T, K> {

    //Elle permet de préciser la dao avec laquelle on va connecter à la base de données (autrement dit la source de données).
    public Dao<T, K> getDao();

    //Processus à exécuter avant de persister l'entité dans la base de données.
    public boolean processBeforeSave(T t);

    //Processus à exécuter après avoir persister l'entité dans la base de données.
    public boolean processAfterSave(T t);

    //Elle permet de persister une entité en base de données.
    public T save(T t) throws DaoException;

    //Process to update entity
    public T update(T t);

    //process before update
    public boolean processBeforeUpdate(T t);

    //process after update
    public boolean processAfterUpdate(T t);

    //get all entities from database
    public List<T> getAll() throws DaoException;

    //Rechercher un élément par une propriété
    public List<T> findByPropertyName(String propertyName, Object value) throws DaoException;

    //Rechercher un élément par son identifiant
    public T findById(K k) throws DaoException;

    //delete entity from database
    public boolean delete(T t) throws DaoException;

    //delete element by id
    public boolean deleteById(K k) throws DaoException;

    //Flusher la base de données
    public void flush();

    //Classe managée par entity managed
    public Class<T> getEntityClassManaged();

}