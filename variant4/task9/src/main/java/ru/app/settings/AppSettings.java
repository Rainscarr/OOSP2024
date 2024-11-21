package ru.app.settings;

import java.util.HashMap;
import java.util.Map;

public class AppSettings {
    private static AppSettings instance;
    private Map<String, String> options = new HashMap<>();

    private AppSettings() {}

    public static AppSettings getInstance() {
        if (instance == null) {
            instance = new AppSettings();
        }
        return instance;
    }

    public void setOption(String key, String value) {
        options.put(key, value);
    }

    public String getOption(String key) {
        return options.getOrDefault(key, "Не задано");
    }
}
