package model;

import jdk.nashorn.internal.parser.JSONParser;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * Created by mickael.afonso on 27/04/2017.
 */
public class JsonFile {
    public void readData() {
        //JSONParser parser = new JSONParser()
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

        try {
            FileReader file = new FileReader("file.js");
            engine.eval(file);
            file.close();
        }catch (Exception e){

        }




        /*Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());*/
    }

    public void writeData(String[] content){

    }
}
