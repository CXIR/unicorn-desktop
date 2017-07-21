package model;

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
    public HashMap<String,String> map;
    protected int id;
    protected String message;
    protected User Plaintiff;
    protected User Reported;
    protected BooleanProperty bloqued = new SimpleBooleanProperty();

    public Report(){}

    public Report(int id, String message, User plaintiff, User reported){
        this.id = id;
        this.message = message;
        this.Plaintiff = plaintiff;
        this.Reported = reported;
        if (reported.getStatus().getId() == 4) {
            setBloqued(true);
        }
    }

    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        map = new HashMap<>();

        map.put("id","int");
        map.put("message","String");
        map.put("Plaintiff","User");
        map.put("Reported","User");

        return map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlaintiff() {
        return Plaintiff;
    }

    public void setPlaintiff(User plaintiff) {
        this.Plaintiff = plaintiff;
    }

    public User getReported() {
        return Reported;
    }

    public void setReported(User reported) {
        this.Reported = reported;
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
    }

    public ArrayList<Report> getReports(){
        String method = "GET";
        String page = "/report/";
        Request req = new Request(method, page);
        ArrayList<Report> reports = new ArrayList<>();
        System.out.println();
        try {
            for(Object obj : req.getMultipleResults("Report")){
                if(obj instanceof Report){
                    Report report = (Report) obj;
                    System.out.println(report.getReported());
                    if (report.getReported().getStatus().getId() == 4){
                        report.setBloqued(true);
                    }
                    reports.add(report);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return reports;
    }
}

