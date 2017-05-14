package model;

import java.io.*;

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

    public void read(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            JsonFile json = new JsonFile();

            while((str = in.readLine()) != null) {
                if(ext.equals("txt") || ext.equals("csv") || ext.equals("xls") || ext.equals("xlsx")){
                    json.writeData(readCSV(str));
                }
                else if(ext.equals("xml")){
                    //readXML(str);
                }

            }
            in.close();
            System.out.println("C'est fait");
        }
        catch (IOException e) {
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

    /*public String[] readXML(String str){
        tablbalise = str.split(">");
        int derniere_balise;
        if(tablbalise.length > 1){
            for (int i = 0; i < tablbalise.length; i++){
                prembalise = tablbalise[0];
                prembalise = prembalise.substring(1);
                if(prembalise != baliserep){
                    System.out.println(prembalise);
                }
                tablordre = prembalise;
            }
        }
    }*/
}
