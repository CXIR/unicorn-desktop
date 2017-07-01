package annotationParser.processor;

import annotationParser.annotation.Method;
import annotationParser.annotation.Modification;
import annotationParser.annotation.Parameter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by aimee on 30/06/2017.
 */

@SupportedAnnotationTypes(value = {
        "com.sdz.annotation.Method",
        "com.sdz.annotation.Parametre",
        "com.sdz.annotation.Modification"
}
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HTMLProcessor extends AbstractProcessor {

    List<Method> list_method = new ArrayList<>();
    FileOutputStream fw = null;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("Début du traitement HTML");

        for(TypeElement typeElement : annotations) {

            for(Element element : roundEnv.getElementsAnnotatedWith(typeElement)){

                Method method = element.getAnnotation(Method.class);

                if (method != null) {
                    list_method.add(method);
                }

            }

        }

        genererHtml(list_method);

        System.out.println("Fin du traitement HTML");

        return true;
    }

    public void genererHtml(List<Method> list) {

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<table style=\"border-collapse:collapse\" border=\"1\">");

        html.append("<h1>Documentation</h1>");
        html.append("<tr>");

        html.append("<td style=\"border:1px solid black\">Portée</td>");
        html.append("<td style=\"border:1px solid black\">Type Retour</td>");
        html.append("<td style=\"border:1px solid black\">Nom de la méthode</td>");
        html.append("<td style=\"border:1px solid black\">Paramètres</td>");
        html.append("<td style=\"border:1px solid black\">Description</td>");
        html.append("<td style=\"border:1px solid black\">Auteur</td>");
        html.append("<td style=\"border:1px solid black\">Date</td>");
        html.append("<td style=\"border:1px solid black\">Modifications</td>");

        html.append("</tr>");


        Iterator<Method> it = list.iterator();

        if(list.isEmpty())return;

        File htmlFile = new File("Doc.html");

        try {
            fw = new FileOutputStream(htmlFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        while(it.hasNext()){

            Method m = it.next();
            html.append("<tr>");
            html.append("<td style=\"border:1px black\">"+ m.portee() + "</td>");
            html.append("<td style=\"border:1px black\">"+ m.typeRetour() + "</td>");
            html.append("<td style=\"border:1px black\">"+ m.nomDeLaMethode() + "</td>");

            html.append("<td style=\"border:1px black\">");

            for(Parameter param : m.parametres()) {
                html.append(param.type() + " " + param.nom() + " - " + param.description());
                html.append("</br>");
            }

            html.append("</td>");

            html.append("<td style=\"border:1px black\">"+ m.description() + "</td>");
            html.append("<td style=\"border:1px black\">"+ m.auteur() + "</td>");
            html.append("<td style=\"border:1px black\">"+ m.date() + "</td>");

            html.append("<td style=\"border:1px black\">");

            for(Modification modif : m.modifications()) {
                html.append(modif.auteur() + " - " + modif.dateModification() + " - " + modif.descriptionModification());
                html.append("</br>");
            }

            html.append("</td>");

        }


        html.append("</html>");
        html.append("</body>");
        html.append("</table>");

        try
        {
            fw.write(html.toString().getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try
            {
                fw.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

    }

}