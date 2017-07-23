package model;

import controller.Message;

/**
 * Created by Micka on 20/07/2017.
 */
public class RequestException extends Exception {
    public RequestException(String message){
        new Message("Une erreur est survenue lors de l'execution : \n" + message);
    }
}
