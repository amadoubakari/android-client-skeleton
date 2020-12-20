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
    //second text color
    private int secondTextColor;
    //primary text color
    private int primaryTextColor;
    //font reference
    private int font ;

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
     *
     * @param headerColor
     * @param fontReference
     */
    public DialogStyle(int headerColor, int fontReference) {
        this.headerColor = headerColor;
        this.font = fontReference;
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

    public DialogStyle(int headerColor, int stytle, String fontPathFile, int secondTextColor) {
        this.headerColor = headerColor;
        this.stytle = stytle;
        this.fontPathFile = fontPathFile;
        this.secondTextColor = secondTextColor;
    }

    public DialogStyle(int headerColor,String fontPathFile, int secondTextColor, int primaryTextColor) {
        this.headerColor = headerColor;
        this.fontPathFile = fontPathFile;
        this.secondTextColor = secondTextColor;
        this.primaryTextColor = primaryTextColor;
    }
    public DialogStyle(int headerColor,int secondTextColor, int primaryTextColor,int font) {
        this.headerColor = headerColor;
        this.secondTextColor = secondTextColor;
        this.primaryTextColor = primaryTextColor;
        this.font=font;
    }

    public DialogStyle(int primaryTextColor, int secondTextColor, int font) {
        this.secondTextColor = secondTextColor;
        this.primaryTextColor = primaryTextColor;
        this.font=font;
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

    public int getSecondTextColor() {
        return secondTextColor;
    }

    public void setSecondTextColor(int secondTextColor) {
        this.secondTextColor = secondTextColor;
    }

    public int getPrimaryTextColor() {
        return primaryTextColor;
    }

    public void setPrimaryTextColor(int primaryTextColor) {
        this.primaryTextColor = primaryTextColor;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }
}
