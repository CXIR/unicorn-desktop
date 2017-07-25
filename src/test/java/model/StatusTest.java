package model;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StatusTest {

    Status status = new Status(9000,"Unicorn");

    @Test
    public void should_get_id() throws Exception {
        Assert.assertEquals(9000,status.getId());
        System.out.println("Status ID correctly recovered");
    }

    @Test
    public void should_set_id() throws Exception {
        status.setId(9001);

        Assert.assertEquals(9001,status.getId());
        System.out.println("Status ID correctly modified");
    }

    @Test
    public void should_get_label() throws Exception {
        Assert.assertEquals("Unicorn",status.getLabel());
        System.out.println("Status label correctly recovered");
    }

    @Test
    public void should_set_label() throws Exception {
        status.setLabel("Super Unicorn");

        Assert.assertEquals("Super Unicorn",status.getLabel());
        System.out.println("Status label correctly modified");
    }

    @Test
    public void should_set_invalid() throws Exception {
        status.setInvalid(false);

        Assert.assertEquals(false,status.isInvalid());
        System.out.println("Status invalide correctly modified");
    }

    @Test
    public void should_get_invalid() throws Exception {
        Assert.assertEquals(false,status.isInvalid());
        System.out.println("Status invalid correctly recovered");
    }

    @Test
    public void should_json_status() throws Exception {
        String json = "{\"id\":9000,\"label\":\"Unicorn\"}";
        JSONObject convert = status.jsonStatus(true);

        assertTrue("Status json conversion failed",convert.toString().equals(json));
        System.out.println("Status json correctly converted");
    }

    @Test
    public void should_create_status() throws Exception {
        boolean created = false;
        Status toCreate = new Status(9000,"Unicorn");
        toCreate.createStatus();

        ArrayList<Status> statuses = toCreate.getAllStatus();
        for(Status registered : statuses){
            if(registered.toString().equals(toCreate.toString())){
                toCreate.setId(registered.getId());
                created = true;
            }
        }
        toCreate.deleteStatus();

        assertTrue("Status insertion failed",created);
        System.out.println("Status insertion succeded");
    }

    @Test
    public void should_get_status() throws Exception {
        boolean recovered = false;
        Status toGet = new Status(9000,"Unicorn");
        toGet.createStatus();

        ArrayList<Status> statuses = toGet.getAllStatus();
        for(Status registered : statuses){
            if(registered.toString().equals(toGet.toString())){
                toGet.setId(registered.getId());
                recovered = true;
            }
        }
        toGet.deleteStatus();

        assertTrue("Status recover failed",recovered);
        System.out.println("Status recover succeded");
    }

    @Test
    public void should_update_status() throws Exception {
        boolean updated = false;
        Status toCreate = new Status(9000,"Unicorn");
        toCreate.createStatus();

        ArrayList<Status> statuses = toCreate.getAllStatus();

        for(Status registered : statuses){
            if(registered.toString().equals(toCreate.toString())){
                toCreate.setId(registered.getId());
                toCreate.setLabel("Pony Pony");
                toCreate.updateStatus();
            }
        }

        statuses.clear();
        statuses = toCreate.getAllStatus();

        for(Status registered : statuses){
            if(registered.toString().equals(toCreate.toString())){
                updated = true;
                break;
            }
        }

        toCreate.deleteStatus();

        assertTrue("Status update failed",updated);
        System.out.println("Status update succeded");
    }



}