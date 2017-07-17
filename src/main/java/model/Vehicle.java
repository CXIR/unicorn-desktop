package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

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
    protected User user;

    public Vehicle(){}

    public Vehicle(int id, String brand, String model, String registrationNumber, int placesNumber, String vehicleType, boolean isOK, User user){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.placesNumber = placesNumber;
        this.vehicleType = vehicleType;
        setIsOK(isOK);
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle(String id) {
        String method = "GET";
        String page = "/vehicle/" + id;
        Request req = new Request(method, page);
        try {
            return (Vehicle) req.getSingleResult("Vehicle");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Vehicle> getVehicles(){
        String method = "GET";
        String page = "/vehicle/";
        Request req = new Request(method, page);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            for (Object obj : req.getMultipleResults("Vehicle")){
                vehicles.add((Vehicle) obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
