package com.sqbika.afarmk;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class LogHelper {
    private static Logger logger;

    public static Logger getLogger() {
        if (Objects.isNull(logger)) {
            logger = LogManager.getLogger(AFarmK.class);
        }
        return logger;
    }

    public static void log(Level level, String format, Object... data){
        getLogger().log(level, String.format("[AFarmK] " + format, data));
    }

    public static void warn(Object stuff, Object... data) {
        log(Level.WARN, ""+stuff, data);
    }

    public static void fatal(String log, Exception ex) {
        getLogger().fatal(log, ex);
    }

    public static void info(Object stuff, Object... data) {
        log(Level.INFO, ""+stuff, data);
    }

    public static void chat(Object stuff) {
        //Minecraft.getMinecraft().player.sendChatMessage("[AFarmK] - " + stuff);
    }

    public static void setLogger(Logger logger) {
        LogHelper.logger = logger;
    }
}
