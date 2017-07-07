package model;


import org.json.simple.parser.ParseException;

/**
 * Created by ludwigroger on 06/07/2017.
 */

public class Test {
    public static void main(String[] args) throws ParseException {

        Site site = new Site();

        System.out.println(site.getSite(1));

    }
}

