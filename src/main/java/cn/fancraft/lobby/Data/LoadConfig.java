package cn.fancraft.lobby.Data;

import cn.fancraft.lobby.Main;
import com.moandjiezana.toml.Toml;

import java.io.*;

public class LoadConfig extends Main {
    public static void loadConfig(){
        File configFile = new File("config.toml");
        if (!configFile.exists()){
            extractResourceToFile("config.toml",configFile);
        }

        Config = new Toml().read(configFile);
    }
    private static void extractResourceToFile(String resourceName, File destinationFile) {
        try (InputStream inputStream = LoadConfig.class.getResourceAsStream("/" + resourceName);
             OutputStream outputStream = new FileOutputStream(destinationFile)) {

            if (inputStream == null) {
                throw new FileNotFoundException("资源 " + resourceName + " 未在JAR中找到");
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
