package io.swagger.petspore.core.settings;

public enum ChannelSettings {
    MOBILE1("settings_mob_1.json"),
    MOBILE2("settings_mob_2.json");

    private final String settingsPath;

    ChannelSettings(String settingsPath) {
        this.settingsPath = settingsPath;
    }

    public String getSettingsPath() {
        return settingsPath;
    }

}
