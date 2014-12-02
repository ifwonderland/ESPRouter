package router.util;

import org.apache.log4j.Logger;
import org.ini4j.Ini;
import router.model.EmailRouteConfig;
import router.model.EmailServiceProviderAPIConfig;
import router.service.esp.mandrill.MandrillEmailRouteService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Responsible for loading properties or configurations.
 * <p/>
 * Created by shuang on 11/26/2014.
 */
public class ConfigurationUtil {

    private static final Logger log = Logger.getLogger(ConfigurationUtil.class);


    public static EmailRouteConfig loadEmailRouteConfig() throws IOException {
        EmailRouteConfig.EmailServiceProvider preferedEsp = getPreferredEsp();
        EmailServiceProviderAPIConfig apiConfig = getEspApiConfig(preferedEsp);

        EmailRouteConfig config = new EmailRouteConfig(preferedEsp, apiConfig);

        return config;
    }


    private static EmailRouteConfig.EmailServiceProvider getPreferredEsp() throws IOException {
        Ini emailRouteConfigIni = new Ini();
        InputStream in = null;
        try {
            in = ConfigurationUtil.class.getResourceAsStream(EmailRouteConfig.configFileName);
            emailRouteConfigIni.load(in);
            String preferedEsp = emailRouteConfigIni.get(EmailRouteConfig.espSectionName, EmailRouteConfig.preferredEspPropertyName);

            return EmailRouteConfig.EmailServiceProvider.valueOf(preferedEsp);
        } catch (IOException e) {
            log.error("Exception happened when trying to load ini file : EmailRouteConfig.ini, make sure it is in classpath: ");
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                log.error("Exception happened when trying to close input stream for ini file : EmailRouteConfig.ini, make sure it is in classpath: ");
                throw e;
            }
        }
    }


    private static EmailServiceProviderAPIConfig getEspApiConfig(EmailRouteConfig.EmailServiceProvider esp) throws IOException {
        if (esp == null)
            throw new IOException("Unable to get ESP API config if the esp is null. ");

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = ConfigurationUtil.class.getResourceAsStream(EmailRouteConfig.propertyFileName);
            prop.load(input);

            switch (esp) {
                case Mandrill:
                    return getMandrillApiConfig(prop);

                case Mailgun:
                    return getMailGunApiConfig(prop);
            }

        } catch (IOException ex) {
            log.error("Exception happened when trying to load properties. ", ex);
            throw ex;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("Exception happened when trying to close input stream for property loading. ", e);
                    throw e;
                }
            }
        }

        throw new IOException("Unable to get ESP API config. ");
    }


    /**
     * Get API config for Mandrill APP
     *
     * @param prop
     * @return
     */
    private static EmailServiceProviderAPIConfig getMandrillApiConfig(final Properties prop) {
        String userName = prop.getProperty(EmailRouteConfig.EspConfigProperties.MandrillUserName.getPropertyName());
        String password = prop.getProperty(EmailRouteConfig.EspConfigProperties.MandrillPassword.getPropertyName());
        String apiKey = prop.getProperty(EmailRouteConfig.EspConfigProperties.MandrillApiKey.getPropertyName());
        String server = prop.getProperty(EmailRouteConfig.EspConfigProperties.MandrillServer.getPropertyName());

        EmailServiceProviderAPIConfig apiConfig = new EmailServiceProviderAPIConfig(apiKey, userName, password, server);
        return apiConfig;
    }


    /**
     * Get API config for Mailgun APP
     *
     * @param prop
     * @return
     */
    private static EmailServiceProviderAPIConfig getMailGunApiConfig(final Properties prop) {
        String userName = "";
        String password = "";
        String apiKey = prop.getProperty(EmailRouteConfig.EspConfigProperties.MailGunApiKey.getPropertyName());
        String server = prop.getProperty(EmailRouteConfig.EspConfigProperties.MailGunServer.getPropertyName());

        EmailServiceProviderAPIConfig apiConfig = new EmailServiceProviderAPIConfig(apiKey, userName, password, server);
        return apiConfig;
    }

}