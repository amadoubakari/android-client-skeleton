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
    //Primary color of the application
    private int headerColor;
    //theme and style to apply to text
    private int stytle;
    //font style path
    private String fontPathFile;

    /**
     * Default constructor
     */
    public DialogStyle() {
    }

    /**
     * @param headerColor
     */
    public DialogStyle(int headerColor) {
        this.headerColor = headerColor;
    }

    /**
     * @param headerColor
     * @param stytle
     */
    public DialogStyle(int headerColor, int stytle) {
        this.headerColor = headerColor;
        this.stytle = stytle;
    }

    /**
     *
     * @param headerColor
     * @param fontPathFile
     */
    public DialogStyle(int headerColor, String fontPathFile) {
        this.headerColor = headerColor;
        this.fontPathFile = fontPathFile;
    }

    /**
     *
     * @param headerColor
     * @param stytle
     * @param fontPathFile
     */
    public DialogStyle(int headerColor, int stytle, String fontPathFile) {
        this.headerColor = headerColor;
        this.stytle = stytle;
        this.fontPathFile = fontPathFile;
    }

    public int getHeaderColor() {
        return headerColor;
    }

    public void setHeaderColor(int headerColor) {
        this.headerColor = headerColor;
    }

    public int getStytle() {
        return stytle;
    }

    public void setStytle(int stytle) {
        this.stytle = stytle;
    }

    public String getFontPathFile() {
        return fontPathFile;
    }

    public void setFontPathFile(String fontPathFile) {
        this.fontPathFile = fontPathFile;
    }
}
