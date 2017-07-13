package model;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.time.Instant;
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
    private String domain = "http://localhost:3000";
    private String link;
    private HttpURLConnection conn;

    public Request(String method, String path){
        link = domain + path;
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            if(method.toUpperCase().equals("POST")){
                conn.setDoOutput(true);
                //conn.setRequestProperty("Content-Type", "x-www-form-urlencoded");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestMethod("POST");
            }
            else if(method.toUpperCase().equals("DELETE")){
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
            alert.setTitle("Error");
            alert.setContentText("Failed to connect to the server "+domain);
            alert.showAndWait();
        }

    }

    @Deprecated
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

    public Object getSingleResult(String className) throws ParseException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
            String line = in.readLine();

            if(line != null){

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                JSONObject single = (JSONObject)obj;

                return createObject(className,single);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getMultipleResults(String className) throws ParseException{
        ArrayList<Object> results = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
            String line = in.readLine();

            if(line != null){

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                JSONArray array = (JSONArray)obj;

                for(int i = 0; i < array.size(); i++){
                    JSONObject elem = (JSONObject)array.get(i);
                    results.add(createObject(className,elem));
                }

                return results;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createObject(String className,JSONObject elem){
        try {
            String hauptklasse = "model."+className;
            Class<?> _class = Class.forName(hauptklasse);
            Object obj = _class.newInstance();

            Method prop =  _class.getMethod("getProperties");
            Object o =  prop.invoke(obj);

            HashMap<String,String> properties = (HashMap<String,String>)o;

            for(HashMap.Entry<String,String> entry : properties.entrySet()){
                String attribute = entry.getKey();
                String type = entry.getValue();

                if(elem.get(entry.getKey()) instanceof JSONArray){
                    ArrayList<Object> list = new ArrayList<>();
                    JSONArray array = (JSONArray)elem.get(entry.getKey());
                    for(int i = 0; i < array.size(); i++){
                        JSONObject array_elem = (JSONObject)array.get(i);
                        Object current = createObject(type,array_elem);
                        list.add(current);
                    }
                    Field f = _class.getDeclaredField(attribute);
                    f.set(obj,list);
                }
                else if(elem.get(entry.getKey()) instanceof JSONObject){
                    JSONObject object = (JSONObject)elem.get(entry.getKey());
                    Object association = createObject(type,object);

                    Field f = _class.getDeclaredField(attribute);
                    f.set(obj,association);
                }
                else if(elem.get(entry.getKey()) instanceof String){
                    String value = (String)elem.get(entry.getKey());
                    Field f = _class.getDeclaredField(attribute);

                    if(type == "String"){
                        Class cls = type.getClass();
                        Object param = Class.forName(cls.getName()).getConstructor(new Class[]{String.class}).newInstance(value);
                        f.set(obj,param);
                    }
                    else if(type == "boolean"){
                        f.setBoolean(obj,Boolean.parseBoolean(value));
                    }
                    else if(type == "Date"){
                        Date date = Date.from(Instant.parse(value));
                        f.set(obj,date);
                    }
                }
                else if(elem.get(entry.getKey()) instanceof Long){
                    Long value = (Long)elem.get(entry.getKey());
                    Field f = _class.getDeclaredField(attribute);

                    if(type == "int"){
                        f.setInt(obj,Integer.parseInt(value.toString()));
                    }
                }
            }

            return obj;

        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
