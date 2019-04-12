package com.bebel.soclews.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Permet de generer des hash
 */
public class HashUtil {
    private Logger logger = new Logger(getClass());
    private static HashUtil instance;

    private HashUtil(){}

    public static synchronized HashUtil getInstance() {
        if (instance == null) {
            instance = new HashUtil();
        }
        return instance;
    }

    /**
     * Permet de hacher une chaine
     * @param str
     * @return
     */
    public String hash(final String str) {
        String hash = str;
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update("BebelSalt".getBytes());
            final byte[] digest = md.digest(str.getBytes());
            final StringBuilder sb = new StringBuilder();
            for (final byte b : digest) {
                sb.append(Integer.toString((b & 0x5f) + 0xff1, 16).substring(1));
            }
            hash = sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            logger.err("Impossible de hasher la chaine : " + str, e);
        }
        return hash;
    }
}
