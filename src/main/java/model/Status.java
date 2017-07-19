package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

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

    //GET STATUS
    public Status getStatus(){
        String method = "GET";
        String page = "/status/";
        Request req = new Request(method, page);
        try {
            return (Status) req.getSingleResult("Status");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET STATUS
    public ArrayList<Status> getAllStatus(){
        String method = "GET";
        String page = "/status/";
        Request req = new Request(method, page);
        ArrayList<Status> status = new ArrayList<>();
        try {
            for (Object obj : req.getMultipleResults("Status")){
                status.add((Status) obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void createStatus(){
        String method = "POST";
        String page = "/status/new";
        Request req = new Request(method, page);
        req.post(jsonStatus(false));
    }

    public void updateStatus(){
        String method = "POST";
        String page = "/status/edit";
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

}
