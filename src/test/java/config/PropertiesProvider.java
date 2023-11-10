package config;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertiesProvider {
    public final Properties properties = readProperties();
    public final String LOGIN_ERROR_MESSAGE = properties.getProperty("login.error.message.authentication.failed");
    public final String USERNAME_REQUIRED_MESSAGE = properties.getProperty("login.error.message.username.required");
    public final String PASSWORD_REQUIRED_MESSAGE = properties.getProperty("login.error.message.password.required");
    public final String USER_LOCKED_MESSAGE = properties.getProperty("login.error.message.user.locked");

    private Properties readProperties() {
        Properties props = new Properties();
        try {
            props.load(PropertiesProvider.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}