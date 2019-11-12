package com.packt.chapter8;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileConfigSource implements ConfigSource {
    private final String CONFIG_FILE = "/tmp/customconfig.properties";
    private final String CONFIG_SOURCE_NAME = "ExternalConfigSource";
    private final int ORDINAL = 900;

    @Override
    public Map getProperties() {

        try(InputStream in = new FileInputStream( CONFIG_FILE )){

            Properties properties = new Properties();
            properties.load( in );

            Map map = new HashMap();
            properties.stringPropertyNames()
                    .stream()
                    .forEach(key-> map.put(key, properties.getProperty(key)));

            return map;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Set getPropertyNames() {

        try(InputStream in = new FileInputStream( CONFIG_FILE )){

            Properties properties = new Properties();
            properties.load( in );

            return properties.stringPropertyNames();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getOrdinal() {
        return ORDINAL;
    }

    @Override
    public String getValue(String s) {

        try(InputStream in = new FileInputStream( CONFIG_FILE )){
            Properties properties = new Properties();
            properties.load( in );
            return properties.getProperty(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getName() {
        return CONFIG_SOURCE_NAME;
    }
}