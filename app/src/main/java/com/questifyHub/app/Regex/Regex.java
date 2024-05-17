package com.questifyHub.app.Regex;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.questifyHub.app.Exceptions.InvalidEmailException;
public class Regex {
    
    public static boolean validateEmail(String email){
        Pattern p = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.(com|com\\.br|br)$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if(m.find()){
            return true;
        }else{
            throw new InvalidEmailException("Email inválido");
        }
    }
}