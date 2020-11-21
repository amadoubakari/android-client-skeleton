/*
 * copyright KyOSSI
 * alright reserved.
 *
 *
 *
 */
package com.kyossi.firebase.tools;

/**
 * This class is used to execute process before and after updating the app
 *
 * @author AMADOU BAKARI
 * @version 1.0.O
 * @email amadoubakari1992@gmail.com
 * @since 21/11/2020
 */
public interface UpdateProcess {
    /**
     * Action to execute before the beginning of the updating app
     */
    void beginUpdateProcess();

    /**
     * Action to execute after the beginning of the updating app
     */
    void endUpdateProcess();
}
