package com.example.foodrecipemobileapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

public final class InternetUtils {

    public static boolean isInternetConnected(Context getApplicationContext) {
        boolean status = false;

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (cm.getActiveNetwork() != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null) {
                    // connected to the internet
                    status = true;
                }

            } else {
                if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting()) {
                    // connected to the internet
                    status = true;
                }
            }
        }
        return status;
    }
}
