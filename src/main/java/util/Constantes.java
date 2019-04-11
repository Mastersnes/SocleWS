package util;


import java.text.SimpleDateFormat;

/**
 * Constantes
 */
public class Constantes {
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final SimpleDateFormat MONTH = new SimpleDateFormat("MM/yyyy");
    public static final DateComparator DATE_COMPARATOR = new DateComparator();

    public static final boolean DEBUG_ENABLED = true;
}
