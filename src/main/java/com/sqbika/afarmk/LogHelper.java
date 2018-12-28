package com.sqbika.afarmk;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    private static Logger logger = LogManager.getLogger("AFarmK");

    public static void log(Level level, String format, Object... data){
        logger.log(level, String.format(format, data));
    }

    public static void warn(String stuff, Object... data) {
        log(Level.WARN, stuff, data);
    }

    public static void info(String stuff, Object... data) {
        log(Level.INFO, stuff, data);
    }
}
