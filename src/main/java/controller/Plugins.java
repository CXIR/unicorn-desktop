package controller;

import PluginManager.PluginLoader;
import javafx.fxml.FXML;

/**
 * Created by Micka on 13/07/2017.
 */
public class Plugins {
    @FXML
    public void dad(){
        PluginLoader loader = new PluginLoader();
        loader.LoadPlugins();
    }
}
