package model;


import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class VehicleTest {

    Site site = new Site(1,"Nord","4 Grand Place","Lille","59000");

    Status status = new Status(9000,"Unicorn");

    User user = new User(1,"Ludwig","Roger",new Date(),"roger.ludwig@outlook.com","crypted","none","none",0,0,site,status);

    Vehicle vehicle = new Vehicle(1,"Renault","Alpine","AS-587-EE",2,"Citadine",false,user);

    @Test
    public void should_get_id() throws Exception {
        Assert.assertEquals(1,vehicle.getId());
        System.out.println("Vehicle ID correctly recovered");
    }

    @Test
    public void should_set_id() throws Exception {
        vehicle.setId(2);

        assertTrue("Vehicle id modification failed",vehicle.getId() == 2);
        System.out.println("Vehicle ID correctly modified");
    }

    @Test
    public void should_get_brand() throws Exception {
        Assert.assertEquals("Renault",vehicle.getBrand());
        System.out.println("Vehicle brand correctly recovered");
    }

    @Test
    public void should_set_brand() throws Exception {
        vehicle.setBrand("Bentley");
        String brand = vehicle.getBrand();

        assertTrue("Vehicle brand modification failed",brand.equals("Bentley"));
        System.out.println("Vehicle brand correctly modified");
    }

    @Test
    public void should_get_model() throws Exception {
        Assert.assertEquals("Alpine",vehicle.getModel());
        System.out.println("Vehicle model correctly recovered");
    }

    @Test
    public void should_set_model() throws Exception {
        vehicle.setModel("Bentaga");
        String model = vehicle.getModel();

        assertTrue("Vehicle model modification failed",model.equals("Bentaga"));
        System.out.println("Vehicle model correctly modified");
    }

    @Test
    public void should_get_registrationNumber() throws Exception {
        Assert.assertEquals("AS-587-EE",vehicle.getRegistrationNumber());
        System.out.println("Vehicle registration correctly recovered");
    }

    @Test
    public void should_set_registrationNumber() throws Exception {
        vehicle.setRegistrationNumber("AS-587-HH");
        String registration = vehicle.getRegistrationNumber();

        assertTrue("Vehicle registration modification failed",registration.equals("AS-587-HH"));
        System.out.println("Vehicle registration correctly modified");
    }

    @Test
    public void should_get_placesNumber() throws Exception {
        Assert.assertEquals(2,vehicle.getPlacesNumber());
        System.out.println("Vehicle seats correctly recovered");
    }

    @Test
    public void should_set_placesNumber() throws Exception {
        vehicle.setPlacesNumber(1);

        assertTrue("Vehicle seats modification failed",vehicle.getPlacesNumber() == 1);
        System.out.println("Vehicle seats correctly modified");
    }

    @Test
    public void should_get_vehicleType() throws Exception {
        Assert.assertEquals("Citadine",vehicle.getVehicleType());
        System.out.println("Vehicle type correctly recovered");
    }

    @Test
    public void should_set_vehicleType() throws Exception {
        vehicle.setVehicleType("Sportive");
        String type = vehicle.getVehicleType();

        assertTrue("Vehicle type modification failed",type.equals("Sportive"));
        System.out.println("Vehicle type correctly modified");
    }

    @Test
    public void should_get_isVehicleOK() throws Exception {
        Assert.assertEquals(false,vehicle.isVehicleOK());
        System.out.println("Vehicle validation correctly recovered");
    }

    @Test
    public void should_set_isVehicleOK() throws Exception {
        vehicle.setVehicleOK(true);

        assertTrue("Vehicle validation modification failed",vehicle.isVehicleOK());
        System.out.println("Vehicle validation correctly modified");
    }

}