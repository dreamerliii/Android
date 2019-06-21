package com.teest.mrli.telephone.UI.Model;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by DELL on 2018/12/5.
 */

public class ContactsSQL {
    public Cursor selectContacts(Context context){
        ContentResolver resolver = context.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null, null, null);
        return cursor;
    }

    public void insertContacts(Context context, String name, String tel, String email){
        Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver1 = context.getContentResolver();
        ContentValues values = new ContentValues();
        long contact_id = ContentUris.parseId(resolver1.insert(uri1, values));
        Uri uri = Uri.parse("content://com.android.contacts/data");
        //姓名
        values.put("raw_contact_id", contact_id);
        values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/name");
        values.put("data2", name);
        resolver1.insert(uri, values);
        values.clear();
        //电话
        values.put("raw_contact_id", contact_id);
        values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
        values.put("data2", "2");
        values.put("data1", tel);
        resolver1.insert(uri, values);
        values.clear();
        //邮箱
        values.put("raw_contact_id", contact_id);
        values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
        values.put("data2", "2");
        values.put("data1", email);
        resolver1.insert(uri, values);
    }
}
