package com.recipe.roulette.app.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.recipe.roulette.app.constants.Constants;
import com.recipe.roulette.app.events.InternetConnectionEvent;
import com.recipe.roulette.app.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by varsovski on 03-Oct-16.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d(Constants.APP_TAG, "NetworkStateReceiver | Connection change!");
        if (intent.getExtras() != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();


            if (activeNetwork != null) {
                LogUtil.d(Constants.APP_TAG, "NetworkStateReceiver | activeNetwork.isConnected() == " + activeNetwork.isConnected());
                EventBus.getDefault().post(new InternetConnectionEvent(activeNetwork.isConnected()));
            } else
                EventBus.getDefault().post(new InternetConnectionEvent(false));
        }
    }
}
