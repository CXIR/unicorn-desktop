package annotationParser.annotation;

/**
 * Created by aimee on 30/06/2017.
 */
public @interface Method {

    String auteur();
    String date();
    String nomDeLaMethode();
    String portee();
    String typeRetour();
    String description();
    Parameter[] parametres();
    Modification[] modifications();

}