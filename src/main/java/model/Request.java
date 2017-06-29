package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mickael.afonso on 17/05/2017.
 */
public class Request {
    private String site = "http://date.jsontest.com";

    public void get(String page){
        String link = site + page;
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while((line = in.readLine()) != null){
                //getUsers(line);
                System.out.println(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUsers(String line){
        String[] part = line.split(",");
        System.out.println();
    }

    public void getUser(){

    }

    public void putUsers(){

    }

    public void removeUsers(){

    }

    public void removeUser(){

    }
}
