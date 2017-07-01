package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private String pass;
    private String desc;
    private String phone;
    private Site site;
    private String nameSite;
    private Status status;
    private String nameStatus;
    private BooleanProperty admin = new SimpleBooleanProperty();
    private BooleanProperty superAd = new SimpleBooleanProperty();
    private Ride ride;

    public User(){
    }

    public User(int id, String name, String first, String birth, String mail, ){
        this.id = id;
        this.name = name;
        this.first = first;
        this.birth = birth;
        this.mail = mail;
        this.site = site;
        this.nameSite = site.getName();
        this.status = status;
        this.nameStatus = status.getStatus();
        if (nameStatus.equals("admin")){
            setAdmin(true);
        }
        else if (nameStatus.equals("super admin")){
            setSuperAd(true);
        }
        this.ride = ride;
    }
    /*public User(int id, String name, String first, String birth, String mail, String pass, String phone, String desc, Site site, Status status, Ride ride){
        this.id = id;
        this.name = name;
        this.first = first;
        this.birth = birth;
        this.mail = mail;
        this.pass = pass;
        this.desc = desc;
        this.phone = phone;
        this.site = site;
        this.nameSite = site.getName();
        this.status = status;
        this.nameStatus = status.getStatus();
        if (nameStatus.equals("admin")){
            setAdmin(true);
        }
        else if (nameStatus.equals("super admin")){
            setSuperAd(true);
        }
        this.ride = ride;
    }*/

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getNameSite() {
        return site.getName();
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

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public boolean isSuperAd() {
        return superAd.get();
    }

    public BooleanProperty superAdProperty() {
        return superAd;
    }

    public void setSuperAd(boolean superAd) {
        this.superAd.set(superAd);
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
}
