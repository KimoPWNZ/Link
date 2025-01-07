import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config(String configFilePath) throws IOException {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        }
    }

    public int getMaxLinkLifespan() {
        return Integer.parseInt(properties.getProperty("maxLinkLifespan"));
    }

    public int getDefaultClickLimit() {
        return Integer.parseInt(properties.getProperty("defaultClickLimit"));
    }
}