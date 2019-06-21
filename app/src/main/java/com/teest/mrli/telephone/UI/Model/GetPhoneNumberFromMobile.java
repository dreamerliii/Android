package com.teest.mrli.telephone.UI.Model;


import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.teest.mrli.telephone.UI.Controller.Friends;

/**
 * Created by Mr.Li on 2018/11/26.
 */

public class GetPhoneNumberFromMobile {
    private ArrayList<Friends> list;

    public ArrayList<Friends> getPhoneNumberFromMobile(Context context) {
        // TODO Auto-generated constructor stub
        list = new ArrayList<Friends>();
        Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI,
                null, null, null, null);

        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(Phone.NUMBER));

            String id = cursor.getString(cursor
                    .getColumnIndex(Phone.RAW_CONTACT_ID));

            Friends friends = new Friends(name);


            Uri uri = Uri.parse("content://com.android.contacts/contacts/"+id+"/data");
            Cursor cursor1 = context.getContentResolver().query(uri,new String[]{ContactsContract.Data.DATA1, ContactsContract.Data.MIMETYPE},ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID+"="
                    +id,null,null);

            while (cursor1.moveToNext()){//读取通讯录的邮箱
                String email = cursor1.getString(cursor1.getColumnIndex("data1"));
                if (cursor1.getString(cursor1.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")){
                    friends.setEmail(email);
                    break;
                }
            }
            friends.setTelephone(number);

            friends.setId(id);
            list.add(friends);
        }
        return list;
    }

    public void DeleteCotact(Friends friends,Context context) throws Exception{
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = context.getContentResolver().query(uri,new String[]{ContactsContract.Data._ID},"display_name=?",new String[]{friends.getName()},null);
        if (cursor.moveToFirst())
        {
            context.getContentResolver().delete(uri,"display_name=?",new String[]{friends.getName()});
            uri = Uri.parse("content://com.android.contacts/data");
            context.getContentResolver().delete(uri,"raw_contact_id=?",new String[]{friends.getId()+""});
        }
    }

    public void updataCotact(Friends friend,Context context) throws Exception {
        Uri uri = Uri.parse("content://com.android.contacts/data");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        //姓名
        values.put("data2", friend.getName());
        resolver.update(uri, values,"mimetype=? and raw_contact_id=?",new String[]{"vnd.android.cursor.item/name",friend.getId()+""});
        values.clear();
        //电话
        values.put("data2", "2");
        values.put("data1", friend.getTelephone());
        resolver.update(uri, values,"mimetype=? and raw_contact_id=?",new String[]{"vnd.android.cursor.item/phone_v2",friend.getId()+""});
        values.clear();
        //邮箱
        values.put("data2", "2");
        values.put("data1", friend.getEmail());
        resolver.update(uri, values,"mimetype=? and raw_contact_id=?",new String[]{"vnd.android.cursor.item/email_v2",friend.getId()+""});
        values.clear();
    }

}
