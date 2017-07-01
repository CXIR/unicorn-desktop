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
    private HttpURLConnection conn;

    public Request(String meth, String page){
        link = site + page;
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            if(meth.equals("post")){
                //conn.setDoInput(true);
                conn.setDoOutput(true);
                //conn.setRequestProperty("Content-Type", "x-www-form-urlencoded");
                //conn.setRequestProperty("Content-Type", "application/json");
                //conn.setRequestProperty("charset", "utf-8");
                conn.setRequestMethod("POST");
            } else{
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

    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = in.readLine()) != null){
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(line);
                for(Object obj : array){
                    JSONObject json = (JSONObject) obj;

                    String strdate = json.get("birthdate").toString();
                    Instant instant = Instant.parse(strdate);
                    Date da = Date.from(instant);
                    SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
                    String date = form.format(da);
                    int id = ((Long) json.get("id")).intValue();
                    //users.add(new User(id, json.get("lastname").toString(), json.get("firstname").toString(), date, json.get("mailAdress").toString(), json.get("password").toString(), json.get("phoneNumber").toString(), json.get("description").toString(), new Site(), new Status(), new Ride()));
                    users.add(new User(id, json.get("lastname").toString(), json.get("firstname").toString(), date, json.get("mailAdress").toString(), json.get("password").toString()));

                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("La connexion à la base de données a échouée !");
            alert.showAndWait();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String str){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = in.readLine()) != null){
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(line);
                for(Object obj : array){
                    JSONObject json = (JSONObject) obj;

                    String strdate = json.get("birthdate").toString();
                    if(strdate.contains("Z")){
                        Instant instant = Instant.parse(strdate);
                        strdate = java.util.Date.from(instant).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                    }
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE);
                    LocalDate date = LocalDate.parse(strdate, format);
                    int id = ((Long) json.get("id")).intValue();
                    //User user = new User(id, json.get("lastname").toString(), json.get("firstname").toString(), date.toString(), json.get("mailAdress").toString(), json.get("password").toString(), json.get("phoneNumber").toString(), json.get("description").toString(), new Site(), new Status(), new Ride());
                    //return user;
                    return null;
                }
                System.out.println(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void putUsers(){

    }

    public void putUser(User user){
        //String param = "name=" + user.getName() + "&first=" + user.getFirst() + "&birth=" + user.getBirth() + "&mail=" + user.getMail() + "&pass=Test1234";
        HashMap<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("first", user.getFirst());
        map.put("birth", "1993-03-25");
        map.put("mail", user.getMail());
        map.put("pass", "Test1234");
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(getDataEncode(map));
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

    public void removeUsers(){

    }

    public void removeUser(){

    }

    public ArrayList<Site> getSites(){
        ArrayList<Site> sites = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = in.readLine()) != null){
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(line);
                for(Object obj : array){
                    JSONObject json = (JSONObject) obj;

                    int id = ((Long) json.get("id")).intValue();
                    int postal = ((Long) json.get("postalCode")).intValue();
                    sites.add(new Site(id, json.get("name").toString(), json.get("adress").toString(), json.get("city").toString(), postal));
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sites;
    }
    public void putSite(Site site){
        HashMap<String, String> map = new HashMap<>();
        map.put("name", site.getName());
        map.put("adress", site.getAddress());
        map.put("city", site.getCity());
        map.put("postalCode", site.getPostal().toString());

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            writer.write(getDataEncode(map));
            //writer.write("&" + URLEncoder.encode("postalCode", "UTF-8") + "=" + site.getPostal());
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDataEncode(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()){
            if (first){
                first = false;
            } else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


}
