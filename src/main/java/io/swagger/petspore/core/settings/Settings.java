package io.swagger.petspore.core.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.path.json.JsonPath;
import io.swagger.petspore.core.settings.exception.AutotestException;
import io.swagger.petspore.utils.Properties;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static io.swagger.petspore.core.settings.ChannelSettings.MOBILE1;
import static io.swagger.petspore.core.settings.ChannelSettings.MOBILE2;

/*
Класс хелпер для работы с файлом настроек json src/main/resources
-> Вложенный класс Sender как pojo модель для читаемого json файла
->

/
 */


public class Settings {

    private static final String SETTINGS_FILE_NAME = getSettingsFileName();
    private static Settings instance = new Settings();
    private static Gson gson;

    public static Settings getInstance() {
        settingsInit(null);
        return instance;
    }

    private List<Sender> senders;

    /*
    Вложенный класс Sender как модель для Json -> Pojo
    /
     */

    private class Sender {
        private String login;
        private String loginID;
        private String description;
        private String phoneNumber;

        String getLogin() {
            return login;
        }

        String getLoginID() {
            return loginID;
        }

        String getDescription() {
            return description;
        }

        String getPhoneNumber() {
            return phoneNumber;
        }

    }

    private List<Sender> getSenders() {         //список всех блоков sender в файле
        return senders;
    }

    /*
    По значению channel в application.properties
    выбирается необходимый JSON в main/java/resources

    */

    private static String getSettingsFileName() {
        ChannelSettings option = ChannelSettings.valueOf(Properties.get("channel"));
        switch (option) {
            case MOBILE1:
                return String.format(MOBILE1.getSettingsPath(), Properties.get("testBlock"));
            case MOBILE2:
                return String.format(MOBILE2.getSettingsPath(), Properties.get("testBlock"));
            default:
                throw new IllegalArgumentException("Не задан channel в application.properties для выбора json файла настроек");
        }
    }

    /*
        У конкретного блока Sender из getSenderByDescription получаем getPhoneNumber
     */

    public String getPhoneNumberByDescription(String description) {
        return getSenderByDescription(description).getPhoneNumber();

    }

    /*
        Получение конкретного блока Sender по description из json
     */

    private Sender getSenderByDescription(String description) {
        for (Sender sender : getSenders()) {
            if (sender.getDescription().equals(description)) {
                return sender;
            }
        }
        throw new AutotestException("Sender с описанием \"" + description + "\" не найден в файле настроек " + SETTINGS_FILE_NAME);
    }

    /*
        Инициализация файла с настройками

     */

    public static void settingsInit(String fileName) {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            final String userDir = System.getProperty("user.dir") + "/src/main/resources";
            Path path;
            if (fileName == null || fileName.isEmpty()) {
                path = FileSystems.getDefault().getPath(userDir, SETTINGS_FILE_NAME);   //ищем файл SETTINGS_FILE_NAME в текущей директории userDir
            } else {
                path = FileSystems.getDefault().getPath(userDir).resolve(fileName);     // получение
            }
            final String jsonString = new String(Files.readAllBytes(path));             // прочитать всё в path
            new JsonPath(jsonString);
            instance = gson.fromJson(jsonString, Settings.class);                       //отобразить jsonString и сделать Pojo по модели Settings.class
            System.out.println("получен файл с настройками " + gson.toJson(instance));
        } catch (IOException ex) {
            throw new IllegalStateException("Невозможно получить settings.json", ex);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не валидный JSON с настройками", e);
        }
    }


}
