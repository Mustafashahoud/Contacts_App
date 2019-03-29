package com.example.contacts.models;

public class ModelContacts {

    String name, number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ModelContacts(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
