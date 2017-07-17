package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

/**
 * Created by mickael.afonso on 18/05/2017.
 */
public class Site {
    protected int id;
    protected String name;
    protected String address;
    protected String city;
    protected int postal;

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

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    //GET SITE
    public Site getSite(){
        String method = "GET";
        String page = "/users/" + id;
        Request req = new Request(method, page);
        try {
            return (Site) req.getSingleResult("Site");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET ALL SITE
    public ArrayList<Site> getSites(){
        String method = "GET";
        String page = "/site/";
        Request req = new Request(method, page);
        ArrayList<Site> sites = new ArrayList<>();
        try {
            for (Object obj : req.getMultipleResults("Site")){
                sites.add((Site) obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sites;
    }

    //AJOUT SITE
    public void createSite(){
        String method = "POST";
        String page = "/site/new";
        Request req = new Request(method, page);
        req.post(jsonSite());
    }

    //MODIF SITE
    public void changeSite(){
        String method = "POST";
        String page = "/site/edit";
        Request req = new Request(method, page);
        req.post(jsonSite());
    }

    public JSONObject jsonSite(){
        JSONObject json = new JSONObject();
        json.put("id", String.valueOf(id));
        json.put("name", name);
        json.put("adress", address);
        json.put("city", city);
        json.put("postalCode", String.valueOf(postal));
        return json;
    }

    //DELETE SITE



}
