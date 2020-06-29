package io.swagger.petspore.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/*

Класс для работы с application.properties
-> инициализация через System AsStream запись в prop
-> получени значения через get()
/

 */

public class Properties {

    private static Properties instance;             //инстанц property
    private static java.util.Properties props;      //инстанц util.Properties

    private Properties() {
        try {
            String sConfigFile = System.getProperty("user.dir") + "/src/test/resources/application.properties";
            InputStream in = Properties.class.getClassLoader().getResourceAsStream(sConfigFile);
            if (in == null) {
                in = new FileInputStream(sConfigFile);
            }
            props = new java.util.Properties();
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static synchronized Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    public static String get(String prop) {
        return getInstance().getProp(prop);
    }

    private String getProp(String name) {
        String val = getProperties().getProperty(name, "");
        if (val.isEmpty()) {
            System.out.println("Property {} was not found in Props");
        }
        return val.trim();
    }

    public static java.util.Properties getProperties() {
        return props;
    }
}