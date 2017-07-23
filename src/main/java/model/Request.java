package model;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import controller.Message;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Created by mickael.afonso on 17/05/2017.
 */
public class Request {
    private boolean error;
    private String site = "http://localhost:3000";
    private String link;
    public HttpURLConnection conn;

    public Request(String meth, String page){
        link = site + page;
        //System.out.println(link);
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            if(meth.toUpperCase().equals("POST")){
                conn.setDoOutput(true);
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
            new Message("La connexion au serveur a échoué.");
        }

    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void post(JSONObject json){
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(conn.getOutputStream());
            System.out.println(json);
            json.writeJSONString(writer);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);

                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(line);
                    if (obj instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) obj;
                        if (jsonObject.get("result") != null){
                            if (jsonObject.get("result") instanceof Long){
                                if (Long.parseLong(jsonObject.get("result").toString()) == 0){
                                    new RequestException(jsonObject.get("message").toString());
                                    setError(true);
                                }
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getSingleResult(String className) throws ParseException, RequestException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
            String line = in.readLine();

            if(line != null){

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);

                if (obj instanceof JSONObject){

                    JSONObject jsonObject = (JSONObject) obj;

                    if (jsonObject.get("result") != null){
                        if (jsonObject.get("result") instanceof Long){
                            if (Long.parseLong(jsonObject.get("result").toString()) == 0){
                                new RequestException(jsonObject.get("message").toString());
                                setError(true);
                            }
                        }
                    }
                    if(jsonObject.get("result") == "1") System.out.println(jsonObject);
                    if (jsonObject.get("content") != null){
                        if (jsonObject.get("content") instanceof JSONObject){
                            JSONObject single = (JSONObject) jsonObject.get("content");
                            return createObject(className,single);
                        }
                    }
                }
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getMultipleResults(String className) throws ParseException, RequestException {
        ArrayList<Object> results = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new InputStreamReader(this.conn.getInputStream()))) {
            String line = in.readLine();

            if(line != null){
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                if (obj instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) obj;
                    if (jsonObject.get("result") != null){
                        if (jsonObject.get("result") instanceof Long){
                            if (Long.parseLong(jsonObject.get("result").toString()) == 0){
                                new RequestException(jsonObject.get("message").toString());
                                setError(true);
                            }
                        }
                    }

                    if (jsonObject.get("content") != null){
                        if (jsonObject.get("content") instanceof JSONArray){
                            JSONArray array = (JSONArray) jsonObject.get("content");
                            for (int i = 0; i < array.size(); i++) {
                                JSONObject elem = (JSONObject) array.get(i);
                                results.add(createObject(className, elem));
                            }
                        }
                    }
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
                String majAttribute = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                String type = entry.getValue();
                String nameField = attribute;

                if (elem.get(attribute) == null && elem.get(majAttribute) != null){
                    attribute = majAttribute;
                }

                if(elem.get(attribute) instanceof JSONArray){
                    ArrayList<Object> list = new ArrayList<>();
                    JSONArray array = (JSONArray)elem.get(attribute);
                    for(int i = 0; i < array.size(); i++){
                        JSONObject array_elem = (JSONObject)array.get(i);
                        Object current = createObject(type,array_elem);
                        list.add(current);
                    }
                    Field f = _class.getDeclaredField(nameField);
                    f.set(obj,list);
                }
                else if(elem.get(attribute) instanceof JSONObject){
                    JSONObject object = (JSONObject)elem.get(attribute);
                    Object association = createObject(type,object);

                    Field f = _class.getDeclaredField(nameField);
                    f.set(obj,association);
                }
                else if(elem.get(attribute) instanceof String){
                    String value = (String)elem.get(attribute);
                    Field f = _class.getDeclaredField(nameField);
                    if(type == "String"){
                        Class cls = type.getClass();
                        Object param = Class.forName(cls.getName()).getConstructor(new Class[]{String.class}).newInstance(value);
                        f.set(obj,param);
                    }
                    else if(type == "Date"){
                        Date date = Date.from(Instant.parse(value));
                        f.set(obj,date);
                    }
                }
                else if (elem.get(attribute) instanceof Boolean){
                    boolean value = (Boolean) elem.get(attribute);
                    Field f = _class.getDeclaredField(nameField);
                    f.setBoolean(obj, value);
                }
                else if(elem.get(attribute) instanceof Long){
                    Long value = (Long)elem.get(attribute);
                    Field f = _class.getDeclaredField(nameField);

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
