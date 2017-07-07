package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Micka on 29/06/2017.
 */
public class Status {
    private int id;
    private String label;

    public Status(){
    }

    public Status(int id, String label){
        this.id = id;
        this.label = label;
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
        return getStatus(req.get());
    }

    public Status getStatus(Object object){
        if (object != null) {
            JSONObject jsonObject = (JSONObject) object;

            //On récupère les champs
            int idStatus = parseInt(jsonObject.get("id").toString());
            String label = jsonObject.get("label").toString();

            //Création de l'objet status
            Status status = new Status(idStatus, label);

            //on retourne status
            return status;
        }
        return null;
    }

    //GET STATUS
    public ArrayList<Status> getAllStatus(){
        String method = "GET";
        String page = "/status/";
        Request req = new Request(method, page);
        return getAllStatus(req.get());
    }

    public ArrayList<Status> getAllStatus(Object object){
        ArrayList<Status> status = new ArrayList<>();
        if (object != null) {
            JSONArray array = (JSONArray) object;
            for (Object obj : array) {
                JSONObject jsonObject = (JSONObject) obj;

                //On récupère les champs
                int idStatus = parseInt(jsonObject.get("id").toString());
                String label = jsonObject.get("label").toString();

                //Création de l'objet status
                status.add(new Status(idStatus, label));

                //on retourne status
            }
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

}
