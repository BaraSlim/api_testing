package io.swagger.petspore.context;

import io.swagger.petspore.core.settings.Settings;
import io.swagger.petspore.utils.Properties;

/*
Класс с основными контектсами тестов:
Методы получения настроек из json

/
 */

public abstract class BaseContext {
    protected static final String MAIN_CONTEXT_PATH = Properties.get("main.context_path");

    private static String phoneNumber;

    public static final String getPhoneBySenderDescription(String description) {
        return phoneNumber = Settings.getInstance().getPhoneNumberByDescription(description);
    }
}
