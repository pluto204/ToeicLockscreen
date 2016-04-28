package com.hai.vocalockscreen;

import java.io.Serializable;

/**
 * Created by HP on 4/24/2016.
 */
public class MyWord implements Serializable {
    private int id;
    private String tu;
    private String phienam;
    private String nghia;

    public MyWord(){

    }
    public MyWord(String tu, String nghia, String phienam){
        this.setTu(tu);
        this.setPhienam(phienam);
        this.setNghia(nghia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getPhienam() {
        return phienam;
    }

    public void setPhienam(String phienam) {
        this.phienam = phienam;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }
}
