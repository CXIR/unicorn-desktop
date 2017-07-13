package model;

import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by aimee on 30/06/2017.
 */

public class Ride {
    protected int id;
    protected Date add_date;

    protected Date depature_date;
    protected String depature_adress;
    protected String depature_postalCode;
    protected String depature_city;
    protected Site departure;

    protected Date arrival_date;
    protected String arrival_adress;
    protected String arrival_postalCode;
    protected String arrival_city;
    protected Site arrival;

    protected User driver;

    protected ArrayList<User> passengers = new ArrayList<User>();

    public Ride(){}

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

        map.put("id","int");
        map.put("add_date","Date");
        map.put("depature_date","Date");
        map.put("depature_adress","String");
        map.put("depature_postalCode","String");
        map.put("depature_city","String");
        map.put("departure","Site");
        map.put("arrival_date","Date");
        map.put("arrival_adress","String");
        map.put("arrival_postalCode","String");
        map.put("arrival_city","String");
        map.put("arrival","Site");
        map.put("driver","User");

        return map;
    }

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

    /**GET SINGLE RIDE */
    public Ride getRide(int id) throws ParseException{
        Request request = new Request("GET","/ride/"+id);
        Object ride = request.getSingleResult("Ride");

        if(ride instanceof Ride) return (Ride)ride;
        return null;
    }

    /** GET ALL RIDES */
    public ArrayList<Ride> getRides() throws ParseException{
        Request request = new Request("GET","/ride/");
        return null;
    }

    /** Object to String Method*/
    @Override
    public String toString(){
        return this.id+" "
        +this.add_date+" "
        +this.depature_date+" "
        +this.depature_adress+" "
        +this.depature_postalCode+" "
        +this.depature_city+" "
        +this.departure.toString()+" "
        +this.arrival_date+" "
        +this.arrival_adress+" "
        +this.arrival_postalCode+" "
        +this.arrival_city+" "
        +this.arrival.toString()+" "
        +this.driver.toString();
    }
}