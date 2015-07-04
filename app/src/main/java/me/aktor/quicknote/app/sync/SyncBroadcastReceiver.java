package me.aktor.quicknote.app.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Andrea Tortorella on 7/4/15.
 */
public class SyncBroadcastReceiver extends BroadcastReceiver

/* WakefulBroadcastReceiver*/
{
    public static final String ACTION = "com.quicknote.BROADCAST_SYNC";

    @Override
    public void onReceive(Context context, Intent intent) {
        SyncService.start(context);
    }
}
