package com.gp.au.methods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.gp.au.environment.BaseTest;


/**
 * Utility to control environment
 * @author Gaurav Purwar
 * 
 */
public class PropertiesManagementMethods implements BaseTest {
    static String propertiesFilePath = "src/main/resources/config.properties";
    public String getProperty(String propName) {
        Properties prop = new Properties();

        InputStream is;
        try {
            is = new FileInputStream(propertiesFilePath);

            prop.load(is);
            String propertieValue = prop.getProperty(propName);

            is.close();

            return propertieValue;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}