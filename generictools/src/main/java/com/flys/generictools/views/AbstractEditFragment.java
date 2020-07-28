package com.flys.generictools.views;

import java.util.List;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @todo Fragment generic d'édition.
 * @since 04/04/2018
 */

public abstract interface AbstractEditFragment<T, K> {

    //Avant de sauvegarder un élément on test la validité des informations que l'utilisateur à saisi
    abstract boolean beforeSave();

    //Après avoir sauvegardé on se rassure effectivement que les informations ont été persistées
    abstract boolean afterSave();

    //La sauvegarde proprement dit
    abstract T save(T t);

    //L'annulation
    abstract boolean cancel();

    //la recherche d'un élément pour pouvoir afficher les détails
    abstract T find(K k);

    //Affichage de tous les éléments
    abstract List<T> findAll();

}
