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
    protected int id;
    protected String message;
    protected User plaintiff;
    protected User reported;
    protected BooleanProperty bloqued = new SimpleBooleanProperty();

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
    }

    public ArrayList<Report> getReports(){
        String method = "GET";
        String page = "/report/";
        Request req = new Request(method, page);
        ArrayList<Report> reports = new ArrayList<>();
        try {
            for(Object obj : req.getMultipleResults("Report")){
                reports.add((Report) obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reports;
    }
}

