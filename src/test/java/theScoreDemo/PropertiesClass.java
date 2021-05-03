package theScoreDemo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//Created by: Sean Xu
//This PropertiesClass class is loading the .properties file from specific directory. Please do make sure the directory is
//correct on your computer before executing

public class PropertiesClass {

    public static String getProperties(Object prop) throws IOException {
        java.util.Properties properties = new java.util.Properties();
        InputStream in1 = new BufferedInputStream(new FileInputStream("./src/test/resources/properties/android.properties"));
        InputStream in2 = new BufferedInputStream(new FileInputStream("./src/test/resources/properties/application.properties"));
        properties.load(in1);
        properties.load(in2);
        return (String) properties.get(prop);

    }
}
