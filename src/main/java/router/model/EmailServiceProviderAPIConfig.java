package router.model;

/**
 * API configurations, these are not persisted but just passing around in memory, ideally, these should be encripted.
 * <p/>
 * Created by shuang on 11/26/2014.
 */
public class EmailServiceProviderAPIConfig {

    private String apiKey;

    private String userName;

    private String password;


    private String server;

    public EmailServiceProviderAPIConfig(String apiKey, String userName, String password, String server) {
        this.apiKey = apiKey;
        this.userName = userName;
        this.password = password;
        this.server = server;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getServer() {
        return server;
    }
}
