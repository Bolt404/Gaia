package org.apollo.template.Controller;

import org.apollo.template.Service.Debugger.DebugMessage;


public class SettingsController {
    private static SettingsController INSTANCE;



    private SettingsController() {
        if (INSTANCE == null) {
            DebugMessage.info(this, "Creating an instance of " + this);
        }
    }

    public static SettingsController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SettingsController();
        }
        return INSTANCE;
    }

}
