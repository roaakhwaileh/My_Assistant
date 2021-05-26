package com.myassistant.manager.Model;

public class Type {
    String specialty_id ,specialty_name;

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public Type(String specialty_id, String specialty_name) {
        this.specialty_id = specialty_id;
        this.specialty_name = specialty_name;
    }
}
