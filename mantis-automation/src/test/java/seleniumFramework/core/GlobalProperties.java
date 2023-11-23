package seleniumFramework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GlobalProperties {

    public static String BROWSER_DEFAULT = "FIREFOX";
    public static String URL = "https://mantis-prova.base2.com.br/bug_report_page.php";
    public static String DEFAULT_USER = "Arthur Norton";
    public static String DEFAULT_PASSWORD = "base2Test";
    public static long TIMEOUT = 30;

//    public GlobalProperties() {
//        Properties properties = new Properties();
//
//        try {
//            InputStream inputStream = Files.newInputStream(Paths.get("local.properties"));
//            properties.load(inputStream);
//        } catch (IOException error){
//            error.printStackTrace();
//        }
//
//        DEFAULT_USER = properties.getProperty("user");
//        DEFAULT_PASSWORD = properties.getProperty("password");
//    }


}
