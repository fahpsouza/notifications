package br.com.mercadolivre.notifications.util;

import java.security.SecureRandom;

public class Util {

    public int generateRandomInt(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max - min + 1) + min;
    }

}
