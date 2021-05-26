package com.myassistant.manager.Model;

public class jobrequestitem {

    String req_assistant_id,user_id,description,whatsapp_phone,location,status,specialty_id,hourly_work_id,user_name,assistant_name,specialty_name;

    public jobrequestitem(String req_assistant_id, String user_id, String description, String whatsapp_phone, String location, String status, String specialty_id, String hourly_work_id, String user_name, String assistant_name, String specialty_name) {
        this.req_assistant_id = req_assistant_id;
        this.user_id = user_id;
        this.description = description;
        this.whatsapp_phone = whatsapp_phone;
        this.location = location;
        this.status = status;
        this.specialty_id = specialty_id;
        this.hourly_work_id = hourly_work_id;
        this.user_name = user_name;
        this.assistant_name = assistant_name;
        this.specialty_name = specialty_name;
    }

    public String getReq_assistant_id() {
        return req_assistant_id;
    }

    public void setReq_assistant_id(String req_assistant_id) {
        this.req_assistant_id = req_assistant_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatsapp_phone() {
        return whatsapp_phone;
    }

    public void setWhatsapp_phone(String whatsapp_phone) {
        this.whatsapp_phone = whatsapp_phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(String specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getHourly_work_id() {
        return hourly_work_id;
    }

    public void setHourly_work_id(String hourly_work_id) {
        this.hourly_work_id = hourly_work_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAssistant_name() {
        return assistant_name;
    }

    public void setAssistant_name(String assistant_name) {
        this.assistant_name = assistant_name;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }
}

