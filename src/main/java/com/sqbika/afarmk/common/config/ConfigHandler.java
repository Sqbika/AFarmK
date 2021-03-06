package com.sqbika.afarmk.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sqbika.afarmk.LogHelper;
import com.sqbika.afarmk.common.Constants;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.util.Objects;

public class ConfigHandler {

    private static Gson gson;

    private static Gson getGson() {
        if (Objects.isNull(gson)) {
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        }
        return gson;
    }

    private static File getConfigFile() {
        return new File(FMLPaths.CONFIGDIR.get().toString() + "/" + Constants.CONFIG_PATH);
    }

    public static AFarmKConfig readConfig() {
        File configFile = getConfigFile();
        if (configFile.exists() && configFile.isFile()) {
            try {
                return getGson().fromJson(new JsonReader(new FileReader(configFile)), AFarmKConfig.class);
            } catch (FileNotFoundException e) {
                LogHelper.fatal("Failed to load the config file! Skipping config loading.", e);
            }
        } else {
            try {
                AFarmKConfig newConfig = new AFarmKConfig();
                createConfigFile(newConfig);
                return newConfig;
            } catch (IOException ioEx) {
                LogHelper.fatal("Unable to create a config file! Skipping config creation.", ioEx);
            }
        }
        //Failed to load / create a new config
        return null;
    }

    public static void createConfigFile(AFarmKConfig config) throws IOException {
        FileWriter fileWriter = new FileWriter(getConfigFile());
        fileWriter.write(getGson().toJson(config, AFarmKConfig.class));
        fileWriter.close();
    }
}
