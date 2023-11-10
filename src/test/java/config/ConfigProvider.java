package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigProvider {
    public final Config config = readConfig();
    public final String LOGIN_URL = config.getString("urls.login");
    public final String MAIN_URL = config.getString("urls.main");
    public final String STANDARD_USER_USERNAME = config.getString("users.standard.username");
    public final String STANDARD_USER_PASSWORD = config.getString("users.standard.password");
    public final String LOCKED_USER_USERNAME = config.getString("users.locked.username");
    public final String LOCKED_USER_PASSWORD = config.getString("users.locked.password");

    private Config readConfig() {
        return ConfigFactory.load("application.conf");
    }
}
