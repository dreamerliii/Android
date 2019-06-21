package com.teest.mrli.telephone.UI.Model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

/**
 * Created by DELL on 2018/12/5.
 */

public class CallLogSQL {
    public Cursor selectCallLog(Context context){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null, null, "date desc");
        return cursor;
    }

    public Cursor selectCallLogName(Context context, String name){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, "name='"+name+"'", null, null);
        return cursor;
    }

    public void updateCallLogNum(Context context, String name, String tel){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.CACHED_NAME, name);
        resolver.update(uri, values, "number='"+tel+"'", null);
    }
}
