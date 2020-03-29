package com.sqbika.afarmk.common.config;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class AFarmKConfig {

    @Expose
    public boolean enableIndividual = true;

    @Expose
    public TogglerProfile[] profiles = new TogglerProfile[0];

    private static AFarmKConfig INSTANCE;

    public static AFarmKConfig INSTANCE() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = ConfigHandler.readConfig();
        }
        return INSTANCE;
    };
}
