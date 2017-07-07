package model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mickael.afonso on 18/05/2017.
 */
public class Site {
    protected int id;
    protected String name;
    protected String adress;
    protected String city;
    protected String postalCode;

    public Site(){ }

    public Site(int id, String name, String address, String city, String postal){
        this.id = id;
        this.name = name;
        this.adress = address;
        this.city = city;
        this.postalCode = postal;
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
        return adress;
    }

    public void setAddress(String address) {
        this.adress = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postalCode;
    }

    public void setPostal(String postal) {
        this.postalCode = postal;
    }


    //AJOUT SITE

    //MODIF SITE

    //DELETE SITE

    private HashMap<String,String[]> getProperties(){
        HashMap<String,String[]> map = new HashMap<>();

        map.put("id",new String[]{"int",null});
        map.put("name",new String[]{"String",null});
        map.put("adress",new String[]{"String",null});
        map.put("city",new String[]{"String",null});
        map.put("postalCode",new String[]{"String",null});

        return map;
    }

    /** Get single Site
     *  Please verify this returned Object is an instanceof Site
     * */
    public Object getSite(int id) throws ParseException {
        Request request = new Request("GET","/site/"+id);
        return request.getSingleResult("model.Site",this.getProperties());
    }

    /** Get all Sites
     * Please verify this returned Object is an instanceof Site
     * */
    public ArrayList<Object> getAllSites() throws ParseException{
        Request request = new Request("GET","/site/");
        return request.getMultipleResults("model.Site",this.getProperties());
    }

    /** Object to String method */
    public String toString(){
        return this.id+" "+this.name+" "+this.adress+" "+this.postalCode;
    }

}
