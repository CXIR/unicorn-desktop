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
    protected boolean invalid;

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

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    @Override
    public String toString() {
        return this.label;
    }

    //GET STATUS

    /**
     * Get a status
     * @return
     */
    public Status getStatus(){
        String method = "GET";
        String page = "/status/";
        Request req = new Request(method, page);
        try {
            //return (Status) req.getSingleResult("Status");
            return (Status) req.getSingleResult("Status");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET STATUS

    /**
     * Get a list of status
     * @return
     */
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
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Create a status
     */
    public void createStatus(){
        String method = "POST";
        String page = "/status/new";
        Request req = new Request(method, page);
        req.post(jsonStatus(false));
        if (req.isError()){
            invalid = true;
        }
        else{
            invalid = false;
        }
    }

    /**
     * Uptade a status
     */
    public void updateStatus(){
        String method = "POST";
        String page = "/status/edit";
        Request req = new Request(method, page);
        req.post(jsonStatus(true));
        if (req.isError()){
            invalid = true;
        }
        else{
            invalid = false;
        }
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
