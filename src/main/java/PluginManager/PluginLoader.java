package PluginManager;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Main;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.jar.*;

/**
 * Created by aimee on 06/07/2017.
 */
public class PluginLoader {

    private List plugins;
    private String directoryPath = "plugin";
    private static URLClassLoader ucl;

    /**
     * Find jar on the directory
     * Find the class who implement Iplugin
     */
    public void LoadPlugins() {

        //Directory with plugins
        File loc = new File(this.directoryPath);

        //Jar List
        File[] files = loc.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getPath().toLowerCase().endsWith(".jar");}
        });

        File directoryTemp = new File(directoryPath + "/temporaryJar");

        ArrayList<File> filesTemp = new ArrayList<>();

        for (File file : files){
            try {
                File newFile = File.createTempFile(file.getName(), ".jar", directoryTemp);
                Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                newFile.deleteOnExit();
                filesTemp.add(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        URL[] urls = new URL[filesTemp.size()];

        for (int i = 0; i < filesTemp.size(); i++){
            try {
                urls[i] = filesTemp.get(i).toURI().toURL();
                System.out.println(urls[i]);
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
        }
    }

    /**
     * Close plugins Jar
     */
    public static void closeFile(){
        try {
            ucl.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
