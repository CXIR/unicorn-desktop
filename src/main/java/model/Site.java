package model;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

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
        json.put("postalCode", String.valueOf(postalCode));
        return json;
    }


    /** Get single Site
     *  Please verify this returned Object is an instanceof Site
     * */
    public Site getSite(int id) throws ParseException {
        Request request = new Request("GET","/site/"+id);
        Object site = request.getSingleResult("Site");

        if(site instanceof Site) return (Site)site;
        return null;
    }

    /** Get all Sites
     * Please verify this returned Object is an instanceof Site
     * */
    public ArrayList<Site> getAllSites() throws ParseException{
        Request request = new Request("GET","/site/");
        ArrayList<Object> raw =  request.getMultipleResults("Site");
        ArrayList<Site> sites = new ArrayList<>();

        for(Object elem : raw){
            if(elem instanceof Site){
                sites.add((Site)elem);
            }
        }
        return sites;
    }

    /** Object to String method */
    public String toString(){
        return this.id+" "+this.name+" "+this.adress+" "+this.postalCode;
    }

}
