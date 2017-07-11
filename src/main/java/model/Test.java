package model;


import org.json.simple.parser.ParseException;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ludwigroger on 06/07/2017.
 */

public class Test {
    public static void main(String[] args) throws ParseException{

        User user = new User();

        Request query = new Request("GET","/users/2");
        Object o = query.getSingleResult("User");

        if(o instanceof User){
            System.out.println(o.toString());
        }

    }
}

