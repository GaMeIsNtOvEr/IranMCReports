package me.gameisntover.iranmcreportsspigot.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class EncryptionUtils {

    public static String getBase64EncodedString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String getCode() {
        Random random = new Random();
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int x = alphabet.length();
        StringBuilder sB = new StringBuilder();

        for(int i = 0; i < 16; ++i) {
            sB.append(alphabet.charAt(random.nextInt(x)));
        }

        return sB.toString();
    }
}
