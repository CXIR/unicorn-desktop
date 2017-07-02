package model;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Created by mickael.afonso on 17/05/2017.
 */
public class Request {
    private String site = "http://localhost:3000";
    private String link;
    public HttpURLConnection conn;

    public Request(String meth, String page){
        link = site + page;
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            if(meth.toUpperCase().equals("POST")){
                conn.setDoOutput(true);
                //conn.setRequestProperty("Content-Type", "x-www-form-urlencoded");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestMethod("POST");
            }
            else if(meth.toUpperCase().equals("DELETE")){
                conn.setDoOutput(true);
                conn.setRequestMethod("DELETE");
                conn.connect();
            }
            else{
                conn.setRequestMethod("GET");
                conn.connect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("La connexion au serveur a échoué.");
            alert.showAndWait();
        }

    }

    public Object get(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = in.readLine();
            if (line != null) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                //On retourne le Json parsé
                return obj;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void post(JSONObject json){
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(conn.getOutputStream());
            json.writeJSONString(writer);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
