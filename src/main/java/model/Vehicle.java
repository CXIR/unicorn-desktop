package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by Micka on 30/06/2017.
 */
public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int regisNum;
    private int place;
    private String type;
    private BooleanProperty isOK = new SimpleBooleanProperty();

    public Vehicle(int id, String brand, String model, int regisNum, int place, String type, boolean isOK){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.regisNum = regisNum;
        this.place = place;
        this.type = type;
        setIsOK(isOK);
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

    public int getRegisNum() {
        return regisNum;
    }

    public void setRegisNum(int regisNum) {
        this.regisNum = regisNum;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
