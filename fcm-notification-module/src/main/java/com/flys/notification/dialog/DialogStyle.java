package com.flys.notification.dialog;

import java.io.Serializable;

/**
 * @author AMADOU BAKARI
 * @version 1.0.0
 * @email amadou_bakari@yahoo.fr
 * @aim: encapsule dialog style
 * @since 26/06/2020
 */
public class DialogStyle implements Serializable {
    private int headerColor;

    public DialogStyle(int headerColor) {
        this.headerColor = headerColor;
    }

    public int getHeaderColor() {
        return headerColor;
    }

    public void setHeaderColor(int headerColor) {
        this.headerColor = headerColor;
    }
}
