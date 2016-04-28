package com.hai.vocalockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by HP on 4/8/2016.
 */
public class VocaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_SCREEN_OFF) || action.equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent displayIntent = new Intent(context, DisplayService.class);
            context.startService(displayIntent);
        }
    }
}
