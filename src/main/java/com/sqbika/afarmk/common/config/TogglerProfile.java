package com.sqbika.afarmk.common.config;

import com.google.gson.annotations.Expose;

public class TogglerProfile {

    @Expose
    public String name = "TempProfile";
    @Expose
    public boolean toggleLeft = false;
    @Expose
    public boolean toggleRight = false;
    @Expose
    public boolean toggleUp = false;
    @Expose
    public boolean toggleDown = false;
    @Expose
    public boolean toggleLeftClick = false;
    @Expose
    public boolean toggleRightClick = false;
    @Expose
    public boolean toggleJump = false;
    @Expose
    public boolean toggleCrouch = false;

    @Expose
    public int profileKeybind = 0;
}
