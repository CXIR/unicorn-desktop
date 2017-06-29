package model;

/**
 * Created by mickael.afonso on 18/05/2017.
 */
public class Site {
    private int id;
    private String name;
    private String address;
    private String city;
    private Integer postal;

    public Site(){

    }

    public Site(int id, String name, String address, String city, int postal){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postal = postal;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }
}
