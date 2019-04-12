package com.bebel.soclews.util;

import java.nio.charset.StandardCharsets;
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
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final String strToHash = "JHGKJHGjhkgkhjgxcvkjgKHJGJH4455456s4dfsdfkhgHJKGJHGdckldsjvkljklLHKH54654" + str + "sdfqsHJGKJGHG5465564HJGHJFJHGJHG23465dfgdfg34654GHFHGF";
            final byte[] digest = md.digest(strToHash.getBytes(StandardCharsets.UTF_8));
            hash = bytesToHex(digest);
        } catch (final NoSuchAlgorithmException e) {
            logger.err("Impossible de hasher la chaine : " + str, e);
        }
        return hash;
    }

    private String bytesToHex(final byte[] hash) {
        final StringBuffer sb = new StringBuffer();
        for (final byte b : hash) {
            final String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}
