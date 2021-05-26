package com.myassistant.manager.Model;

public class HoureRate {
    String specialty_id,price,specialty_name;

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public HoureRate(String specialty_id, String price, String specialty_name) {
        this.specialty_id = specialty_id;
        this.price = price;
        this.specialty_name = specialty_name;
    }
}
