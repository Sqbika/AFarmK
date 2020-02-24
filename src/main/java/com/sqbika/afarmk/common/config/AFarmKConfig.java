package com.sqbika.afarmk.common.config;

import java.util.Objects;

public class AFarmKConfig {

    public boolean enableIndividual = true;

    private static AFarmKConfig INSTANCE;

    public static AFarmKConfig INSTANCE() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = ConfigHandler.readConfig();
        }
        return INSTANCE;
    };
}
