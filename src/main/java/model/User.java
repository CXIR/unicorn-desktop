package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Created by mickael.afonso on 15/05/2017.
 */
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String strDate;
    private String mailAdress;
    private String password;
    private String phoneNumber;
    private String description;
    private int positiveRating;
    private int negativeRating;
    private Site site;
    private Status status;
    private String nameStatus;
    private BooleanProperty admin = new SimpleBooleanProperty();
    private BooleanProperty superAd = new SimpleBooleanProperty();
    private Ride[] rides;

    public User(){
    }

    public User(int id, String firstname, String lastname, Date birthdate, String mailAdress, String password, String phoneNumber, String description, int positiveRating, int negativeRating,  Site site, Status status){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        if (birthdate != null){
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
            this.strDate = form.format(birthdate);
        }
        this.mailAdress = mailAdress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.positiveRating = positiveRating;
        this.negativeRating = negativeRating;
        this.site = site;
        this.status = status;

        System.out.println(lastname);
        if (status != null) {
            if (status.getId() != 0) {
                this.nameStatus = status.getLabel();
                if (status.getId() == 2) {
                    setAdmin(true);
                } else if (status.getId() == 3) {
                    setSuperAd(true);
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getStrDate() {
        if (birthdate != null){
            SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
            this.strDate = form.format(birthdate);
        }
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getNameSite() {
        return site.getName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPositiveRating() {
        return positiveRating;
    }

    public void setPositiveRating(int positiveRating) {
        this.positiveRating = positiveRating;
    }

    public int getNegativeRating() {
        return negativeRating;
    }

    public void setNegativeRating(int negativeRating) {
        this.negativeRating = negativeRating;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
        /*if (admin == true){
            if (status.getId() != 0){
                status = new Status();
                updateStatus();
            }
            else{
                for (Status status : status.getAllStatus()){
                    if (status.getLabel().equals("admin")){
                        this.status = status;
                        updateStatus();
                    }
                }
            }
        }*/
    }

    public boolean isSuperAd() {
        return superAd.get();
    }

    public BooleanProperty superAdProperty() {
        return superAd;
    }

    public void setSuperAd(boolean superAd) {
        this.superAd.set(superAd);
        /*if (superAd == true){
            if (status.getId() != 0){
                status = new Status();
                updateStatus();
            }
            else{
                for (Status status : status.getAllStatus()){
                    if (status.getLabel().equals("superAdmin")){
                        this.status = status;
                        updateStatus();
                    }
                }
            }
        }*/
    }

    public Ride[] getRides() {
        return rides;
    }

    public void setRides(Ride[] rides) {
        this.rides = rides;
    }

    public String convertDate(){
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        return form.format(birthdate);
    }

    //GET USER
    //Récupère le JSon
    public User getUser(String id){
        String method = "GET";
        String page = "/users/" + id;
        Request req = new Request(method, page);
        return getUser(req.get());
    }

    public User getUser(Object object){
        if (object != null){
            JSONObject jsonObject = (JSONObject) object;

            //On récupère les champs
            int idUser = parseInt(jsonObject.get("id").toString());
            String firstname = jsonObject.get("firstname").toString();
            String lastname = jsonObject.get("lastname").toString();
            String birth_date = jsonObject.get("birthdate").toString();
            String mailAdress = jsonObject.get("mailAdress").toString();
            String password = jsonObject.get("password").toString();

            String phoneNumber;
            if (jsonObject.get("phoneNumber") != null){
                phoneNumber = jsonObject.get("phoneNumber").toString();
            }
            else{
                phoneNumber = "";
            }

            String description;
            if (jsonObject.get("description") != null){
                description = jsonObject.get("desciption").toString();
            }
            else{
                description = "";
            }

            int positiveRating = parseInt(jsonObject.get("positiveRating").toString());
            int negativeRating = parseInt(jsonObject.get("negativeRating").toString());

            //On récupère le site et le status
            Site site = null;
            if (jsonObject.get("site") != null){
                site = site.getSite(jsonObject.get("site"));
            }

            Status status = null;
            if (jsonObject.get("status") != null){
                status = status.getStatus(jsonObject.get("status"));
            }

            //convertion de la date de naissance
            Date birthdate = Date.from(Instant.parse(birth_date));

            //Création de l'objet user
            User user = new User(idUser, firstname, lastname, birthdate, mailAdress, password, phoneNumber, description, positiveRating, negativeRating, site, status);

            //on retourne user
            return user;
        }
        return null;
    }

    //GET ALL USERS
    public ArrayList<User> getUsers() {
        String method = "GET";
        String page = "/users/all";
        Request req = new Request(method, page);
        return getUsers(req.get());
    }

    public ArrayList<User> getUsers(Object object){
        ArrayList<User> users = new ArrayList<>();

        if (object != null) {
            JSONArray array = (JSONArray) object;
            for (Object obj : array) {
                JSONObject jsonObject = (JSONObject) obj;

                //On récupère les champs
                int idUser = parseInt(jsonObject.get("id").toString());
                String firstname = jsonObject.get("firstname").toString();
                String lastname = jsonObject.get("lastname").toString();
                String birth_date = jsonObject.get("birthdate").toString();
                String mailAdress = jsonObject.get("mailAdress").toString();
                String password = jsonObject.get("password").toString();

                String phoneNumber;
                if (jsonObject.get("phoneNumber") != null) {
                    phoneNumber = jsonObject.get("phoneNumber").toString();
                } else {
                    phoneNumber = "";
                }

                String description;
                if (jsonObject.get("description") != null) {
                    description = jsonObject.get("desciption").toString();
                } else {
                    description = "";
                }
                int positiveRating = parseInt(jsonObject.get("positiveRating").toString());
                int negativeRating = parseInt(jsonObject.get("negativeRating").toString());

                //On récupère le site et le status
                Site site = new Site();
                if (jsonObject.get("site") != null) {
                    site = site.getSite(jsonObject.get("site"));
                }

                Status status = new Status();
                if (jsonObject.get("status") != null) {
                    status = status.getStatus(jsonObject.get("status"));
                }

                //convertion de la date de naissance
                Date birthdate = Date.from(Instant.parse(birth_date));

                //Ajout de l'objet user
                users.add(new User(idUser, firstname, lastname, birthdate, mailAdress, password, phoneNumber, description, positiveRating, negativeRating, site, status));

            }
        }
        //on retourne la liste de users
        return users;
    }

    //CREATE USER
    public void createUser() {
        String method = "POST";
        String page = "/users/new";
        Request req = new Request(method, page);
        req.post(jsonUser(false));
    }

    //UPDATE USER
    public void updateUser(){
        String method = "POST";
        String page = "/users/edit/";
        Request req = new Request(method, page);
        req.post(jsonUser(true));
    }

    public JSONObject jsonUser(boolean update){
        JSONObject json = new JSONObject();
        if (update){
            json.put("id", String.valueOf(id));
        }
        json.put("name", lastname);
        json.put("first", firstname);
        json.put("birth", birthdate.toInstant().toString());
        json.put("mail", mailAdress);
        json.put("pass", password);
        json.put("site", getStatus().getId());
        json.put("status", getSite().getId());
        return json;
    }

    //Update Site
    public void updateSite(){
        String method = "POST";
        String page = "/users/edit/site";
        Request req = new Request(method, page);
        JSONObject json = new JSONObject();
        json.put("user", String.valueOf(id));

        json.put("site", String.valueOf(site.getId()));
        req.post(json);
    }

    //Update Status
    public void updateStatus(int status){
        String method = "POST";
        String page = "/users/edit/status";
        Request req = new Request(method, page);
        JSONObject json = new JSONObject();
        json.put("user", String.valueOf(id));

        json.put("status", String.valueOf(status));
        req.post(json);
    }

    //Update Rating

    //DELETE USER
    public void deleteUser(){
        String method = "DELETE";
        String page = "/users/" + id;
        new Request(method, page);
    }

    //LOCK USER ACCOUNT
}
