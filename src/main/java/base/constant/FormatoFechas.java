package base.constant;

public class FormatoFechas {

    public static final String PATTERN_DATE_NOTIMEZONE = "yyyy-MM-dd";
    public static final String PATTERN_DATETIME_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private FormatoFechas() {
        throw new IllegalStateException("Clase de Utilidades");
    }
}
