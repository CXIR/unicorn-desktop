package model;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by mickael.afonso on 27/04/2017.
 */
public class JsonFile {
    public void readData() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("N:/Calexpress/13 - METHODES/13.2 Public/13.2.3 Divers/08 - Mickael AFONSO/Partage_ta_caisse/unicorn-core-master/routes/users.js"));
            Invocable invocable = (Invocable) engine;

            Object result = invocable.invokeFunction("test", "user_id");

            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void readData(String name, String first){}

    public void writeData(String[] content){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader("script.js"));
            Invocable invocable = (Invocable) engine;
            invocable.invokeFunction("fun1", "Peter Parker");

        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
