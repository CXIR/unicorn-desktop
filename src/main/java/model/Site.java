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
    public HashMap<String, String> map;
    protected int id;
    protected String name;
    protected String adress;
    protected String city;
    protected String postalCode;

    public Site(){

    }

    public Site(int id, String name, String adress, String city, String postalCode){
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.postalCode = postalCode;
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        map = new HashMap<>();

        map.put("id","int");
        map.put("name","String");
        map.put("adress","String");
        map.put("city","String");
        map.put("postalCode","String");

        return map;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    //GET SITE
    public Site getSite(){
        String method = "GET";
        String page = "/site/" + id;
        Request req = new Request(method, page);
        try {
            //return (Site) req.getSingleResult("Site");
            return (Site) req.getSingleResult("Site");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
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
            //req.get("Site");
            for (Object obj : req.getMultipleResults("Site")){
                sites.add((Site) obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
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
        json.put("adress", adress);
        json.put("city", city);
        json.put("postal", postalCode);
        return json;
    }

    //DELETE SITE



}
