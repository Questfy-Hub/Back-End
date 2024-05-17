package com.questifyHub.app.Regex;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.questifyHub.app.Exceptions.InvalidEmailException;
public class Regex {
    
    public static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]/.[com|com.br|br]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            throw new InvalidEmailException("Email inválido");
        }else{
            return true;
        }
    }
}