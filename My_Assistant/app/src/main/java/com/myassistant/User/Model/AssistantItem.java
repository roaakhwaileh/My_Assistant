package com.myassistant.User.Model;

import java.util.List;

public class AssistantItem {

    String assistant_id,fullname,phone,photo,specialty_id,specialty_name,price,hourly_work_id;

    public String getAssistant_id() {
        return assistant_id;
    }

    public String getHourly_work_id() {
        return hourly_work_id;
    }

    public void setHourly_work_id(String hourly_work_id) {
        this.hourly_work_id = hourly_work_id;
    }

    public void setAssistant_id(String assistant_id) {
        this.assistant_id = assistant_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AssistantItem(String assistant_id, String fullname, String phone, String photo, String specialty_id, String specialty_name, String price,String hourly_work_id) {
        this.assistant_id = assistant_id;
        this.fullname = fullname;
        this.phone = phone;
        this.photo = photo;
        this.specialty_id = specialty_id;
        this.specialty_name = specialty_name;
        this.price = price;
        this.hourly_work_id=hourly_work_id;
    }
}
