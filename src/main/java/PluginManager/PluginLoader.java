package PluginManager;

import javafx.application.Platform;
import javafx.stage.Stage;
import model.Main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.jar.*;

/**
 * Created by aimee on 06/07/2017.
 */
public class PluginLoader {

    private List plugins;
    private String directoryPath = "./plugin";
    private static URLClassLoader ucl;

    public void LoadPlugins() {

        //Directory with plugins
        File loc = new File(this.directoryPath);

        //Jar List
        File[] files = loc.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getPath().toLowerCase().endsWith(".jar");}
        });

        URL[] urls = new URL[files.length];

        for (int i = 0; i < files.length; i++){
            try {
                urls[i] = files[i].toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        ucl = new URLClassLoader(urls);
        ServiceLoader<IPlugin> sl = ServiceLoader.load(IPlugin.class, ucl);
        Iterator<IPlugin> apit = sl.iterator();

        while (apit.hasNext()) {
            IPlugin plugin = apit.next();
            plugin.init();
            //System.out.println(plugin.getName());
        }
    }

    public static void closeFile(){
        try {
            ucl.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
