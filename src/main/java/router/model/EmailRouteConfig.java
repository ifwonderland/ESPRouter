package router.model;

/**
 * Created by shuang on 11/26/2014.
 */
public class EmailRouteConfig {

    public static final String configFileName = "/EmailRouteConfig.ini";

    public static final String espSectionName = "EmailServiceProvider";


    public enum EmailServiceProvider {
        Mandrill, Mailgun
    }

    public static final String propertyFileName = "/esp-services-config.properties";

    public enum EspConfigProperties {
        MandrillUserName("mandrill.username"), MandrillPassword("mandrill.password"), MandrillApiKey("mandrill.api.key"), MandrillServer("mandrill.server"),
        MailGunApiKey("mailgun.api.key"), MailGunServer("mailgun.server");

        String propertyName;

        EspConfigProperties(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    public static final String preferredEspPropertyName = "PreferedSerivce";

    private EmailServiceProvider emailServiceProvider;

    private EmailServiceProviderAPIConfig apiConfig;

    public EmailRouteConfig(EmailServiceProvider emailServiceProvider, EmailServiceProviderAPIConfig apiConfig) {
        this.emailServiceProvider = emailServiceProvider;
        this.apiConfig = apiConfig;
    }

    public EmailServiceProvider getEmailServiceProvider() {
        return emailServiceProvider;
    }

    public EmailServiceProviderAPIConfig getApiConfig() {
        return apiConfig;
    }
}
