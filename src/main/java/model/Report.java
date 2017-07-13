package model;

import com.sun.org.apache.regexp.internal.RE;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

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
        System.out.println(reported.getLastname());
        if (reported.getStatus() != null) {
            if (reported.getStatus().getId() == 4) {
                setBloqued(true);
            }
        }
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

        map.put("id","int");
        map.put("message","String");
        map.put("plaintiff","User");
        map.put("reported","User");

        return map;
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
        /*if (bloqued == true){
            if (reported.getStatus().getId() != 0){
                reported.setStatus(new Status());
                reported.updateStatus(4);
            }
            else{
                for (Status status : reported.getStatus().getAllStatus()){
                    if (status.getId() == 4){
                        this.reported.setStatus(status);
                        reported.updateStatus(1);
                    }
                }
            }
        }*/
    }

    /** GET SINGLE REPORT */
    public Report getReport(int id) throws ParseException{
        Request request = new Request("GET","/report/"+id);
        Object report = request.getSingleResult("Report");

        if(report instanceof Report) return (Report)report;
        return null;
    }

    /** GET ALL REPORTS */
    public ArrayList<Report> getReports() throws ParseException{
        Request request = new Request("GET","/report/");
        ArrayList<Object> raw = request.getMultipleResults("Report");
        ArrayList<Report> reports = new ArrayList<>();

        for(Object elem : raw){
            if(elem instanceof Report){
                reports.add((Report)elem);
            }
        }
        return reports;
    }
}

