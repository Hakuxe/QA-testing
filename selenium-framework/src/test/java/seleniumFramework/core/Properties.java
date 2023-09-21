package seleniumFramework.core;

public class Properties {

    public enum Browsers{
        CHROME,
        FIREFOX
    }
    public static long TIMEOUT = 30;
    public static boolean CLOSE_BROWSER = false;
    public static Browsers BROWSER = Browsers.CHROME;
    public static String EXECUTION = "local";
    public static String SELENIUM_HUB_URL = "";
    public static String URL = "";
    public static String DEFAULT_USER = "";
    public static String DEFAULT_PASSWORD= "";



}
