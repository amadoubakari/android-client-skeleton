package com.flys.generictools.dao.daoException;

/**
 * @author AMADOU BAKARI
 * @since 14/05/2018
 * @todo Elle permet de gérer toutes les exceptions liées à la Dao.
 * @version 1.0.0
 * @email: amadou_bakari@yahoo.fr
 * @tél :(+237) 674316936 / 6900660199
 */

public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
