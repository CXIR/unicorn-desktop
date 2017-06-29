package model;

import javafx.beans.property.StringProperty;

/**
 * Created by mickael.afonso on 15/05/2017.
 */
public class User {
    private int id;
    private String name;
    private String first;
    private String birth;
    private String mail;
    private Site place;
    private String namePlace;
    private Status status;
    private String nameStatus;

    public User(){
    }

    public User(int id, String name, String first, String birth, String mail, Site place, Status status){
        this.id = id;
        this.name = name;
        this.first = first;
        this.birth = birth;
        this.mail = mail;
        this.place = place;
        this.namePlace = place.getName();
        this.status = status;
        this.nameStatus = status.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Site getPlace() {
        return place;
    }

    public void setPlace(Site place) {
        this.place = place;
    }

    public String getNamePlace() {
        return place.getName();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNameStatus() {
        return nameStatus;
    }
}
