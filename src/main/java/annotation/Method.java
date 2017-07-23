package annotation;

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