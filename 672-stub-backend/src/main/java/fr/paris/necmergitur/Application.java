package fr.paris.necmergitur;

import fr.paris.necmergitur.api.APIEndPoint;
import net.codestory.http.WebServer;

/**
 * Created by chriswoodrow on 16/01/2016.
 */
public class Application {
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.configure(routes -> {
            routes.add(new APIEndPoint());
        });

        String port = getEnvOrDefault("PORT", "8080");
        webServer.start(Integer.valueOf(port));
    }

    public static String getEnvOrDefault(String name, String defaultValue){
        String env = System.getenv(name);
        return env != null ? env : defaultValue;
    }

}
