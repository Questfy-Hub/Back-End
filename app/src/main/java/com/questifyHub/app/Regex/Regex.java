package com.questifyHub.app.Regex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.questifyHub.app.Exceptions.InvalidEmailException;

/**
 * Classe Regex
 * 
 */
public class Regex {

    /**
     * Método validateEmail onde é validado se o E-mail informado é um E-mail válido ou não
     * 
     * @param email
     * @return Retorna Verdadeiro se o E-mail for válido. Se não for um E-mail
     *         válido retorna a excessão InvalidEmailException
     */
    public static boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.(com|com\\.br|br)$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        } else {
            throw new InvalidEmailException("Email inválido");
        }
    }
}