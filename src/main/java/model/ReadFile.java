package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by mickael.afonso on 11/05/2017.
 */
public class ReadFile {
    private File file;
    private String ext;

    public ReadFile(File file, String ext){
        this.file = file;
        this.ext = ext;

    }

    /**
     * Lecture du fichier et attribution des valeurs dans la classe User
     */
    public void read(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;

            while((line = in.readLine()) != null) {
                String[] tabl = readCSV(line);
                if (tabl.length >= 7){
                    String firstname = tabl[0];
                    String lastname = tabl[1];
                    String birth_date = tabl[2];
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date birthdate = format.parse(birth_date);
                    String mailAdress = tabl[3];
                    String password = tabl[4];
                    //String site = tabl[5];
                    int idSite = parseInt(tabl[5]);
                    int idStatus = parseInt(tabl[6]);

                    User user = new User();
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setBirthdate(birthdate);
                    user.setMailAdress(mailAdress);
                    user.setPassword(password);
                    Site site = new Site();
                    site.setId(idSite);
                    user.setSite(site);
                    Status status = new Status();
                    status.setId(idStatus);
                    user.setStatus(status);

                    user.createUser();
                }

            }
            in.close();
            System.out.println("C'est fait");
        }
        catch (IOException e) {
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String[] readCSV(String str){
        String[] tabldonnee;

        if(str.contains(",")){
            tabldonnee = str.split(",");
        }
        else if(str.contains(";")){
            tabldonnee = str.split(";");
        }
        else if(str.contains("\t")){
            tabldonnee = str.split("\t");
        }
        else{
            tabldonnee = new String[] {str};
        }
        return tabldonnee;
    }
}
