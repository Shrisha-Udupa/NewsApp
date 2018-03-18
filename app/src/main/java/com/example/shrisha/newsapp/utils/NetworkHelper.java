package com.example.shrisha.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shrisha on 18/3/18.
 */

public class NetworkHelper {


    public static boolean  hasNetwork(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
            return  networkInfo != null && networkInfo.isConnectedOrConnecting();

        } else
            return false;

    }
}
