package com.example.kokborokdictionary.model;


import java.io.Serializable;

import static com.example.kokborokdictionary.Values.Constants.DataRow.BANGLA;
import static com.example.kokborokdictionary.Values.Constants.DataRow.ENGLISH;
import static com.example.kokborokdictionary.Values.Constants.DataRow.FAVOURITE;
import static com.example.kokborokdictionary.Values.Constants.DataRow.ID;
import static com.example.kokborokdictionary.Values.Constants.DataRow.KOKBOROK;

public class DataRow implements Serializable {

    @Override
    public String toString() {
        return "Data: \n" + ID + ":" + id + "\n" + ENGLISH + ":" + english
                + "\n" + KOKBOROK + ":" + kokborok
                + "\n" + BANGLA + ":" + bangla
                + "\n" + FAVOURITE + ":" + isfavourite;
    }

    private int id;
    private String english, kokborok, bangla;
    private int isfavourite;

    public DataRow(int id, String english, String kokborok, String bangla, int isfavourite) {
        this.id = id;
        this.english = filterOutNull(english);
        this.kokborok = filterOutNull(kokborok);
        this.bangla = filterOutNull(bangla);
        this.isfavourite = isfavourite;
    }

    public String filterOutNull(String input) {
        return input == null ? "" : input;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKokborok() {
        return kokborok;
    }

    public void setKokborok(String kokborok) {
        this.kokborok = kokborok;
    }

    public String getBangla() {
        return bangla;
    }

    public void setBangla(String bangla) {
        this.bangla = bangla;
    }

    public int isIsfavourite() {
        return isfavourite;
    }

    public void setIsfavourite(int isfavourite) {
        this.isfavourite = isfavourite;
    }
}
