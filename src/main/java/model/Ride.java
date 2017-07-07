package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aimee on 30/06/2017.
 */
public class Ride {
    private int id;
    private Date add_date;

    //Départ
    private Date depature_date;
    private String depature_adress;
    private String depature_postalCode;
    private String depature_city;
    private int depature_idsite = 0;

    //Arrivée
    private Date arrival_date;
    private String arrival_adress;
    private String arrival_postalCode;
    private String arrival_city;
    private int arrival_idsite = 0;

    //Conducteur
    private User driver;

    //Passager
    private ArrayList<User> passengers = new ArrayList<User>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }

    public Date getDepature_date() {
        return depature_date;
    }

    public void setDepature_date(Date depature_date) {
        this.depature_date = depature_date;
    }

    public String getDepature_adress() {
        return depature_adress;
    }

    public void setDepature_adress(String depature_adress) {
        this.depature_adress = depature_adress;
    }

    public String getDepature_postalCode() {
        return depature_postalCode;
    }

    public void setDepature_postalCode(String depature_postalCode) {
        this.depature_postalCode = depature_postalCode;
    }

    public String getDepature_city() {
        return depature_city;
    }

    public void setDepature_city(String depature_city) {
        this.depature_city = depature_city;
    }

    public int getDepature_idsite() {
        return depature_idsite;
    }

    public void setDepature_idsite(int depature_idsite) {
        this.depature_idsite = depature_idsite;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_adress() {
        return arrival_adress;
    }

    public void setArrival_adress(String arrival_adress) {
        this.arrival_adress = arrival_adress;
    }

    public String getArrival_postalCode() {
        return arrival_postalCode;
    }

    public void setArrival_postalCode(String arrival_postalCode) {
        this.arrival_postalCode = arrival_postalCode;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public int getArrival_idsite() {
        return arrival_idsite;
    }

    public void setArrival_idsite(int arrival_idsite) {
        this.arrival_idsite = arrival_idsite;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public ArrayList<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<User> passengers) {
        this.passengers = passengers;
    }

    public void setPassenger(User user){
        this.passengers.add(user);
    }


    //GET TRAJETS POUR 1 USER (PAST AND FUTUR)



}
