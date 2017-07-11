package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Micka on 29/06/2017.
 */

public class Status {
    protected int id;
    protected String label;

    public Status(){
    }

    public Status(int id, String label){
        this.id = id;
        this.label = label;
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

        map.put("id","int");
        map.put("label","String");

        return map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Object getStatus(int id) throws ParseException{
        Request request = new Request("GET","/status/"+id);
        return request.getSingleResult("Status");
    }

    public ArrayList<Object> getAllStatus() throws ParseException{
        Request request = new Request("GET","/status/");
        return request.getMultipleResults("Status");
    }

    public void createStatus(){
        String method = "POST";
        String page = "/status/new";
        Request req = new Request(method, page);
        req.post(jsonStatus(false));
    }

    public void updateStatus(){
        String method = "POST";
        String page = "/users/edit";
        Request req = new Request(method, page);
        req.post(jsonStatus(true));
    }

    public JSONObject jsonStatus(boolean update){
        JSONObject json = new JSONObject();
        if (update){
            json.put("id", id);
        }
        json.put("label", label);
        return json;
    }

    public String toString(){
        return this.id+" "+this.label;
    }

}
