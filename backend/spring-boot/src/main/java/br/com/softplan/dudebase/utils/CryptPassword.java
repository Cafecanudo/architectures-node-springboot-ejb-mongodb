package br.com.softplan.dudebase.utils;

import lombok.extern.java.Log;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log
public class CryptPassword {

    private CryptPassword() {
    }

    public static String cript(final String text) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return text != null ? bCryptEncoder.encode(text) : null;
    }

    public static boolean compare(final String text1, final String text2) {
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.matches(text1, text2);
    }

}
