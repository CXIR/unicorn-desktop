package model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
