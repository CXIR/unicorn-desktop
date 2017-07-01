package model;

<<<<<<< HEAD
/**
 * Created by Micka on 30/06/2017.
 */
public class Ride {
    private int id;
    private String ad_date;
    private String ad_mess;
    private String depart_date;
    private String depart_adres;
    private String depart_postal;
    private String depart_city;

    public Ride(){}

    public Ride(int id, String ad_date, String ad_mess, String depart_date, String depart_adres, String depart_postal, String depart_city){
        this.id = id;
        this.ad_date = ad_date;
        this.ad_mess = ad_mess;
        this.depart_date = depart_date;
        this.depart_adres = depart_adres;
        this.depart_postal = depart_postal;
        this.depart_city = depart_city;
    }
=======
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
    private User[] passengers;

>>>>>>> refs/remotes/origin/master

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getAd_date() {
        return ad_date;
    }

    public void setAd_date(String ad_date) {
        this.ad_date = ad_date;
    }

    public String getAd_mess() {
        return ad_mess;
    }

    public void setAd_mess(String ad_mess) {
        this.ad_mess = ad_mess;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getDepart_adres() {
        return depart_adres;
    }

    public void setDepart_adres(String depart_adres) {
        this.depart_adres = depart_adres;
    }

    public String getDepart_postal() {
        return depart_postal;
    }

    public void setDepart_postal(String depart_postal) {
        this.depart_postal = depart_postal;
    }

    public String getDepart_city() {
        return depart_city;
    }

    public void setDepart_city(String depart_city) {
        this.depart_city = depart_city;
    }
=======
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

    public User[] getPassengers() {
        return passengers;
    }

    public void setPassengers(User[] passengers) {
        this.passengers = passengers;
    }


    //GET TRAJETS POUR 1 USER (PAST AND FUTUR)



>>>>>>> refs/remotes/origin/master
}
