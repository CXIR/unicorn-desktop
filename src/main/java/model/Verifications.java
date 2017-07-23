package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Micka on 03/07/2017.
 */
public class Verifications {
    public Verifications() {

    }

    /**
     * Check if the field is empty
     * @param str
     * @return
     */
    public boolean isNotEmpty(String str){
        if (str.equals("")) {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Check is a mail
     * @param email
     * @return
     */
    public boolean isValidEmail(String email) {
        String reg = "^[A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+\\.[A-Za-z0-9]{2,}$";
        Pattern pattern = java.util.regex.Pattern.compile(reg);
        Matcher match = pattern.matcher(email);
        return match.matches();
    }
}
