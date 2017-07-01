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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static java.lang.Integer.parseInt;

/**
 * Created by mickael.afonso on 15/05/2017.
 */
public class User {
    private int id;
<<<<<<< HEAD
    private String name;
    private String first;
    private String birth;
    private String mail;
    private String pass;
    private String desc;
    private String phone;
    private Site site;
    private String nameSite;
    private Status status;
    private String nameStatus;
    private BooleanProperty admin = new SimpleBooleanProperty();
    private BooleanProperty superAd = new SimpleBooleanProperty();
    private Ride ride;
=======
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String mailAdress;
    private String password;
    private String phoneNumber;
    private String description;
    private int positiveRating;
    private int negativeRating;
    private Site site;
    private Status status;
    private Ride[] rides;
>>>>>>> refs/remotes/origin/master

    public User(){
    }

<<<<<<< HEAD
    public User(int id, String name, String first, String birth, String mail, ){
        this.id = id;
        this.name = name;
        this.first = first;
        this.birth = birth;
        this.mail = mail;
        this.site = site;
        this.nameSite = site.getName();
        this.status = status;
        this.nameStatus = status.getStatus();
        if (nameStatus.equals("admin")){
            setAdmin(true);
        }
        else if (nameStatus.equals("super admin")){
            setSuperAd(true);
        }
        this.ride = ride;
    }
    /*public User(int id, String name, String first, String birth, String mail, String pass, String phone, String desc, Site site, Status status, Ride ride){
        this.id = id;
        this.name = name;
        this.first = first;
        this.birth = birth;
        this.mail = mail;
        this.pass = pass;
        this.desc = desc;
        this.phone = phone;
        this.site = site;
        this.nameSite = site.getName();
        this.status = status;
        this.nameStatus = status.getStatus();
        if (nameStatus.equals("admin")){
            setAdmin(true);
        }
        else if (nameStatus.equals("super admin")){
            setSuperAd(true);
        }
        this.ride = ride;
    }*/
=======
    public User(int id, String firstname, String lastname, Date birthdate, String mailAdresse, int positiveRating, int negativeRating,  Site site, Status status){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.mailAdress = mailAdress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.positiveRating = positiveRating;
        this.negativeRating = negativeRating;
        this.site = site;
        this.status = status;
    }
>>>>>>> refs/remotes/origin/master

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

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

<<<<<<< HEAD
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getNameSite() {
        return site.getName();
=======
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
>>>>>>> refs/remotes/origin/master
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //GET USER
    public User getUser(String id){

        String method = "GET";
        String page = "/users/" + id;
        Request req = new Request(method, page);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(req.conn.getInputStream()));
            String line = in.readLine();

            if (line != null) {

                //On parse le JSON
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(line);
                JSONObject jsonObject = (JSONObject) obj;

                //On récupère les champs
                int idUser = parseInt(jsonObject.get("id").toString());
                String firstname = jsonObject.get("firstname").toString();
                String lastname = jsonObject.get("lastname").toString();
                String birth_date = jsonObject.get("birthdate").toString();
                String mailAdress = jsonObject.get("mailAdress").toString();
                String password = jsonObject.get("password").toString();
                String phoneNumber = jsonObject.get("phoneNumber").toString();
                String description = jsonObject.get("desciption").toString();
                int positiveRating = parseInt(jsonObject.get("positiveRating").toString());
                int negativeRating = parseInt(jsonObject.get("negativeRating").toString());

                //TODO : récupération du site et du status
                Site site = new Site(1, "toto", "2 rue toto", "Paris", "75014" );
                Status status = new Status(1 , "Admin");

                //convertion de la date de naissance
                Date birthdate = Date.from(Instant.parse(birth_date));

                //Création de l'objet user
                User user = new User(idUser, firstname, lastname, birthdate, mailAdress, positiveRating, negativeRating, site, status);

                //on retourne user
                return user;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

<<<<<<< HEAD
    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public boolean isSuperAd() {
        return superAd.get();
    }

    public BooleanProperty superAdProperty() {
        return superAd;
    }

    public void setSuperAd(boolean superAd) {
        this.superAd.set(superAd);
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }
=======
    //GET ALL USERS


    //UPDATE USER

        //Basic Information

        //Update Site

        //Update Status

        //Update Rating


    //CREATE USER


    //DELETE USER


    //LOCK USER ACCOUNT


>>>>>>> refs/remotes/origin/master
}
