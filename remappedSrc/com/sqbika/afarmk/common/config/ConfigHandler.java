package com.sqbika.afarmk.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sqbika.afarmk.AFarmK;
import com.sqbika.afarmk.LogHelper;
import com.sqbika.afarmk.common.Constants;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
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
        return new File(FabricLoader.getInstance().getConfigDirectory() + "/" + Constants.CONFIG_PATH);
    }

    public static AFarmKConfig readConfig() {
        File configFile = getConfigFile();
        if (configFile.exists() && configFile.isFile()) {
            try {
                AFarmKConfig config = getGson().fromJson(new JsonReader(new FileReader(configFile)), AFarmKConfig.class);
                if (config == null) {
                    //TODO: Remove after fixing empty config issue.
                    AFarmKConfig newConfig = new AFarmKConfig();
                    createConfigFile(newConfig);
                    LogHelper.warn("Empty or invalid AFarmK config found. Overriding.");
                    return newConfig;
                }
                return config;
            } catch (FileNotFoundException e) {
                LogHelper.fatal("Failed to load the config file! Skipping config loading.", e);
            } catch (IOException e) {
                e.printStackTrace();
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
