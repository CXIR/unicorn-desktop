package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mickael.afonso on 15/05/2017.
 */

public class User {
    protected int id;
    protected String firstname;
    protected String lastname;
    protected Date birthdate;
    protected String strDate;
    protected String mailAdress;
    protected String password;
    protected String phoneNumber;
    protected String description;
    protected int positiveRating;
    protected int negativeRating;

    protected Site site;
    protected Status status;

    protected BooleanProperty admin = new SimpleBooleanProperty();
    protected BooleanProperty superAd = new SimpleBooleanProperty();

    protected Ride[] rides;

    public User(){ }

    public User(int id,
                String firstname,
                String lastname,
                Date birthdate,
                String mailAdress,
                String password,
                String phoneNumber,
                String description,
                int positiveRating,
                int negativeRating,
                Site site,
                Status status){

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

        if (status != null) {
            if (status.getId() != 0) {
                if (status.getId() == 2) {
                    setAdmin(true);
                } else if (status.getId() == 3) {
                    setSuperAd(true);
                }
            }
        }
    }


    /** HashMap which contains this Class properties with types */
    public HashMap<String,String> getProperties(){
        HashMap<String,String> map = new HashMap<>();

        map.put("id","int");
        map.put("firstname","String");
        map.put("lastname","String");
        map.put("birthdate","Date");
        map.put("mailAdress","String");
        map.put("password","String");
        map.put("phoneNumber","String");
        map.put("description","String");
        map.put("positiveRating","int");
        map.put("negativeRating","int");
        map.put("site","Site");
        map.put("status","Status");
        map.put("vehicle","Vehicle");

        return map;
    }


    public int getId() { return id; }

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

    /**
     * Get the birthdate with the format dd/MM/yyyy
     * @return
     */
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

    /**
     * GET Single USERS
     * @return a object user
     */
    public User getUser(int id) throws ParseException{
        Request request = new Request("GET", "users/"+id);
        Object user = request.getSingleResult("User");

        if(user instanceof User) return (User)user;
        else return null;
    }


    /**
     * GET ALL USERS
     * @return a list objects users
     */
    public ArrayList<User> getUsers() throws ParseException{
        Request request = new Request("GET", "/users/");
        ArrayList<Object> raw = request.getMultipleResults("User");
        ArrayList<User> users = new ArrayList<>();

        for(Object elem : raw){
            if(elem instanceof User){
                users.add((User)elem);
            }
        }
        return users;
    }

    /**
     * CREATE USER
     * Call the method post to insert user in the database
     */
    public void createUser() {
        String method = "POST";
        String page = "/users/new";
        Request req = new Request(method, page);
        req.post(jsonUser(false));
    }

    /**
     * UPDATE USER
     */
    public void updateUser(){
        String method = "POST";
        String page = "/users/edit/";
        Request req = new Request(method, page);
        req.post(jsonUser(true));
    }

    /** Transform this User into JSONObject */
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

    /**
     * UPDATE SITE
     * Call the method post to update site of the user
     */
    public void updateSite(){
        String method = "POST";
        String page = "/users/edit/site";
        Request req = new Request(method, page);
        JSONObject json = new JSONObject();
        json.put("user", String.valueOf(id));
        json.put("site", String.valueOf(site.getId()));
        req.post(json);
    }

    /**
     * UPDATE STATUS
     * @param status the idStatus
     */
    public void updateStatus(int status){
        String method = "POST";
        String page = "/users/edit/status";
        Request req = new Request(method, page);
        JSONObject json = new JSONObject();
        json.put("user", String.valueOf(id));
        json.put("status", String.valueOf(status));
        req.post(json);
    }

    /**
     * DELETE USER
     * Call the request DELETE with the user id
     */
    public void deleteUser(){
        String method = "DELETE";
        String page = "/users/" + id;
        new Request(method, page);
    }

    public String toString(){
        return this.id+" "
                +this.firstname+" "
                +this.lastname+" "
                +this.phoneNumber+" "
                +this.mailAdress+" "
                +this.description+" site : {"
                +this.site.toString()+"} status : {"
                +this.status.toString()+"} rate : { bad : "
                +this.positiveRating+" good : "
                +this.negativeRating+" }";
    }
}
