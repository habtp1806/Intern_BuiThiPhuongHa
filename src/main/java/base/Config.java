package base;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties;
    private static JSONObject jsonObject;


    static {
        loadProperties();
        loadJsonData();
    }

    public static void loadProperties() {
        properties = new Properties();
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties file not found.");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    private static void loadJsonData() {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("testData.json")) {
            if (input == null) {
                throw new IOException("data.json file not found.");
            }
            jsonObject = new JSONObject(new JSONTokener(input));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load data.json file.");
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getTimeInSeconds(String propertyName) {
        String propertyValue = getProperty(propertyName);
        return Integer.parseInt(propertyValue);
    }

    public static JSONObject getJsonObject() {
        return jsonObject;
    }

    public static JSONArray getTestDataArray() {
        JSONArray testDataArray = jsonObject.getJSONArray("testData");
        return testDataArray;
    }
}
