package seleniumFramework.core;

public class Properties {

    public enum Browsers{
        CHROME,
        FIREFOX
    }
    public static boolean CLOSE_BROWSER = false;
    public static Browsers BROWSER_DEFAULT = Browsers.CHROME;
    public static long TIMEOUT = 30;

    public static String URL = "https://seubarriga.wcaquino.me/";
    public static String DEFAULT_USER = "wagner@costa";
    public static String DEFAULT_PASSWORD= "123456";


}
