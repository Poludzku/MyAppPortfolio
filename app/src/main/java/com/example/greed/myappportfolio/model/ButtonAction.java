package com.example.greed.myappportfolio.model;

import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by greed on 16/09/15.
 */
public class ButtonAction {
    private String message;
    private Intent intent;

    public static ButtonAction createInstance(String message, String packageName, PackageManager packageManager){
        Intent launchIntent = (packageName == null) ? null : packageManager.getLaunchIntentForPackage(packageName);
        return new ButtonAction(message, launchIntent);
    }

    private ButtonAction(String message, Intent intent) {
        this.message = message;
        this.intent = intent;
    }

    public String getMessage() {
        return message;
    }

    public Intent getIntent() {
        return intent;
    }
}
