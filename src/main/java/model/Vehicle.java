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
    private int id;
    private String brand;
    private String model;
    private String registrationNumber;
    private int placesNumber;
    private String vehicleType;
    private BooleanProperty isOK = new SimpleBooleanProperty();
    private User user;

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
        return getVehicle(req.get());
    }

    public Vehicle getVehicle(Object object){
        if (object != null){
            JSONObject jsonObject = (JSONObject) object;

            //On récupère les champs
            int idVehicle = parseInt(jsonObject.get("id").toString());
            String brand = jsonObject.get("brand").toString();
            String model = jsonObject.get("model").toString();
            String registrationNumber = jsonObject.get("registrationNumber").toString();
            int placesNumber = parseInt(jsonObject.get("placesNumber").toString());
            String vehicleType = jsonObject.get("vehicleType").toString();
            boolean isVehicleOK = (Boolean) jsonObject.get("isVehicleOK");

            User user = new User();
            if (jsonObject.get("driver") != null){

                user = user.getUser(jsonObject.get("driver"));
            }

            //Création de l'objet user
            Vehicle vehicle = new Vehicle(idVehicle, brand, model, registrationNumber, placesNumber, vehicleType, isVehicleOK, user);

            //on retourne user
            return vehicle;
        }
        return null;
    }

    public ArrayList<Vehicle> getVehicles(){
        String method = "GET";
        String page = "/vehicle/";
        Request req = new Request(method, page);
        return getVehicles(req.get());
    }

    public ArrayList<Vehicle> getVehicles(Object object){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        if (object != null){
            JSONArray array = (JSONArray) object;
            for(Object obj : array){
                JSONObject jsonObject = (JSONObject) obj;

                //On récupère les champs
                int idVehicle = parseInt(jsonObject.get("id").toString());
                String brand = jsonObject.get("brand").toString();
                String model = jsonObject.get("model").toString();
                String registrationNumber = jsonObject.get("registrationNumber").toString();
                int placesNumber = parseInt(jsonObject.get("placesNumber").toString());
                String vehicleType = jsonObject.get("vehicleType").toString();
                boolean isVehicleOK = (Boolean) jsonObject.get("isVehicleOK");

                User user = new User();
                if (jsonObject.get("driver") != null){

                    user = user.getUser(jsonObject.get("driver"));
                }

                //Ajout de l'objet site
                vehicles.add(new Vehicle(idVehicle, brand, model, registrationNumber, placesNumber, vehicleType, isVehicleOK, user));
            }
        }
        return vehicles;
    }
}
