package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Micka on 30/06/2017.
 */

public class Vehicle {
    protected int id;
    protected String brand;
    protected String model;
    protected String registrationNumber;
    protected int placesNumber;
    protected String vehicleType;
    protected BooleanProperty isOK = new SimpleBooleanProperty();

    public Vehicle(){ }

    public Vehicle(int id, String brand, String model, String registrationNumber, int placesNumber, String vehicleType, boolean isOK, User user){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.placesNumber = placesNumber;
        this.vehicleType = vehicleType;
        setIsOK(isOK);
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

        map.put("id","int");
        map.put("firstname","String");
        map.put("lastname","String");
        map.put("birthdate","Date");
        map.put("mailAdress","String");
        map.put("password","String");
        map.put("phoneNumber","String");
        map.put("description","String");
        map.put("positiveRating","int");
        map.put("negativeRating","int");
        map.put("site","Site");
        map.put("status","Status");

        return map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isIsOK() {
        return isOK.get();
    }

    public BooleanProperty isOKProperty() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK.set(isOK);
    }

    /** Get All Vehicle */
    public ArrayList<Object> getVehicles() throws ParseException{
        Request request = new Request("GET", "/vehicle/");
        return request.getMultipleResults("Vehicle");
    }

    /** Get Single Vehicle */
    public Object getVehicle(int id) throws ParseException{
        Request request = new Request("GET","/vehicle/"+id);
        return request.getSingleResult("Vehicle");
    }
}
