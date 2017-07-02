package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Micka on 01/07/2017.
 */
public class Report {
    private int id;
    private String message;
    private User plaintiff;
    private User reported;
    private BooleanProperty bloqued = new SimpleBooleanProperty();

    public Report(){}

    public Report(int id, String message, User plaintiff, User reported){
        this.id = id;
        this.message = message;
        this.plaintiff = plaintiff;
        this.reported = reported;

        if (reported.getStatus().getId() != 0){
            if (reported.getStatus().getLabel().equals("bloqued")){
                setBloqued(true);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(User plaintiff) {
        this.plaintiff = plaintiff;
    }

    public User getReported() {
        return reported;
    }

    public void setReported(User reported) {
        this.reported = reported;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBloqued() {
        return bloqued.get();
    }

    public BooleanProperty bloquedProperty() {
        return bloqued;
    }

    public void setBloqued(boolean bloqued) {
        this.bloqued.set(bloqued);
        if (bloqued == true){
            if (reported.getStatus().getId() != 0){
                reported.setStatus(new Status());
                reported.updateStatus();
            }
            else{
                for (Status status : reported.getStatus().getAllStatus()){
                    if (status.getLabel().equals("bloqued")){
                        this.reported.setStatus(status);
                        reported.updateStatus();
                    }
                }
            }
        }
    }

    public ArrayList<Report> getReports(){
        String method = "GET";
        String page = "/report/";
        Request req = new Request(method, page);
        return getReports(req.get());
    }

    public ArrayList<Report> getReports(Object object){
        ArrayList<Report> reports = new ArrayList<>();
        if (object != null){
            JSONArray array = (JSONArray) object;
            for(Object obj : array){
                JSONObject jsonObject = (JSONObject) obj;

                //On récupère les champs
                int idReport = parseInt(jsonObject.get("id").toString());
                String message = jsonObject.get("message").toString();

                User plaintiff = new User();
                if (jsonObject.get("plaintiff") != null){
                    plaintiff = plaintiff.getUser(jsonObject.get("plaintiff"));
                }

                User reported = new User();
                if (jsonObject.get("reported") != null){
                    reported = plaintiff.getUser(jsonObject.get("reported"));
                }

                reports.add(new Report(idReport, message, plaintiff, reported));
            }
        }
        return reports;
    }
}

