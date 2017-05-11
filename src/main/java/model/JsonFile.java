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
    public void readData() throws Exception{
        //JSONParser parser = new JSONParser()
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("script.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());
    }
}
