package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Micka on 03/07/2017.
 */
public class Verifications {
    public Verifications() {

    }
    public boolean isempty(String str){
        if (str.equals("")) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isValidEmail(String email) {
        String reg = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = java.util.regex.Pattern.compile(reg);
        Matcher match = pattern.matcher(email);
        return match.matches();
    }

    public boolean isValidPassword(String password) {
        String reg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,25}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(reg);
        java.util.regex.Matcher match = pattern.matcher(password);
        return match.matches();
    }
}
