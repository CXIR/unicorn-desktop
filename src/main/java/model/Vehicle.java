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
import java.util.HashMap;

import static java.lang.Integer.parseInt;

/**
 * Created by Micka on 30/06/2017.
 */
public class Vehicle {
    public HashMap<String,String> map;
    protected int id;
    protected String brand;
    protected String model;
    protected String registrationNumber;
    protected int placesNumber;
    protected String vehicleType;
    protected boolean isVehicleOK;
    protected BooleanProperty vehicleValid = new SimpleBooleanProperty();
    protected User user;
    protected boolean invalid;

    public Vehicle(){}

    public Vehicle(int id, String brand, String model, String registrationNumber, int placesNumber, String vehicleType, boolean isVehicleOK, User user){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.placesNumber = placesNumber;
        this.vehicleType = vehicleType;
        setVehicleValid(isVehicleOK);
        this.user = user;
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        map = new HashMap<>();

        map.put("id","int");
        map.put("brand","String");
        map.put("model","String");
        map.put("registrationNumber","String");
        map.put("placesNumber","int");
        map.put("vehicleType","String");
        map.put("isVehicleOK","boolean");
        map.put("user","User");

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

    public boolean isVehicleOK() {
        return isVehicleOK;
    }

    public void setVehicleOK(boolean vehicleOK) {
        isVehicleOK = vehicleOK;
    }

    public boolean isVehicleValid() {
        return vehicleValid.get();
    }

    public BooleanProperty vehicleValidProperty() {
        return vehicleValid;
    }

    public void setVehicleValid(boolean vehicleValid) {
        this.setVehicleOK(vehicleValid);
        this.vehicleValid.set(vehicleValid);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public Vehicle getVehicle(int id) {
        String method = "GET";
        String page = "/vehicle/" + id;
        Request req = new Request(method, page);
        try {
            Object obj = req.getSingleResult("Vehicle");
            if(obj instanceof Vehicle){
                Vehicle vehicle = (Vehicle) obj;
                vehicle.setVehicleValid(vehicle.isVehicleOK());
                return vehicle;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the list of the vehicles
     * @return
     */
    public ArrayList<Vehicle> getVehicles(){
        String method = "GET";
        String page = "/vehicle/";
        Request req = new Request(method, page);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            for (Object obj : req.getMultipleResults("Vehicle")){
                if(obj instanceof Vehicle){
                    Vehicle vehicle = (Vehicle) obj;
                    vehicle.setVehicleValid(vehicle.isVehicleOK());
                    vehicles.add(vehicle);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    /**
     * Validate the vehicle
     */
    public void validateVehicle(){
        String method = "GET";
        String page = "/vehicle/validate/" + id;
        Request req = new Request(method, page);
        try {
            req.getSingleResult("Vehicle");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unvalidate the vehicle
     */
    public void unvalidateVehicle(){
        String method = "GET";
        String page = "/vehicle/unvalidate/" + id;
        Request req = new Request(method, page);
        try {
            req.getSingleResult("Vehicle");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.brand+" "+this.model+" "+this.registrationNumber+" "+this.placesNumber+" "+this.user.toString();
    }
}
