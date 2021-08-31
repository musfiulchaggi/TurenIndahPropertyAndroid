package com.example.turenindahproperty;

import java.io.Serializable;

public class data_property implements Serializable {
    //Deklarasi Variable
    private String name;
    private int lt;
    private int lb;
    private int km;
    private int kt;
    private int price;
    private String alamat;
    private String map_long;
    private String map_lat;
    private String deskripsi;
    private String agent_name;
    private String agent_email;
    private String agent_phone;
    private String agent_whatsapp;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }

    public int getLb() {
        return lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getKt() {
        return kt;
    }

    public void setKt(int kt) {
        this.kt = kt;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMap_long() {
        return map_long;
    }

    public void setMap_long(String map_long) {
        this.map_long = map_long;
    }

    public String getMap_lat() {
        return map_lat;
    }

    public void setMap_lat(String map_lat) {
        this.map_lat = map_lat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAgent_email() {
        return agent_email;
    }

    public void setAgent_email(String agent_email) {
        this.agent_email = agent_email;
    }

    public String getAgent_phone() {
        return agent_phone;
    }

    public void setAgent_phone(String agent_phone) {
        this.agent_phone = agent_phone;
    }

    public String getAgent_whatsapp() {
        return agent_whatsapp;
    }

    public void setAgent_whatsapp(String agent_whatsapp) {
        this.agent_whatsapp = agent_whatsapp;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_property(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_property(String name, int lt, int lb, int km, int kt, String alamat, String map_long, String map_lat, String deskripsi, String agent_name, String agent_email, String agent_phone, String agen_whatsapp, String image) {
        this.name = name;
        this.lt = lt;
        this.lb = lb;
        this.km = km;
        this.kt = kt;
        this.alamat = alamat;
        this.map_long = map_long;
        this.map_lat = map_lat;
        this.deskripsi = deskripsi;
        this.agent_name = agent_name;
        this.agent_phone = agent_phone;
        this.agent_email = agent_email;
        this.agent_whatsapp = agen_whatsapp;
        this.image = image;
    }

}
