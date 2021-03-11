package com.fawzi.firestore.models;

public class Contact {

    private String id;

    private String name;

    private String email;

    private String address;

    private String number;

    public Contact() {
    }

    public Contact(String name, String email, String address, String number) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
