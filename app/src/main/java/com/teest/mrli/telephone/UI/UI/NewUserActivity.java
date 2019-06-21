package com.teest.mrli.telephone.UI.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Model.CallLogSQL;
import com.teest.mrli.telephone.UI.Model.ContactsSQL;


/**
 * Created by DELL on 2018/11/14.
 */

public class NewUserActivity extends Activity {

    private TextView newbuilt_cancel;
    private TextView newbuilt_save;
    private EditText newbuild_name;
    private EditText newbuilt_main_tel;
    private EditText newbuild_email;
    private TextView warning_name;
    private TextView warning_phone;
    String tel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbuilt);

        ArrayList<String> infoList = new ArrayList<String>();
        Bundle bundle = this.getIntent().getExtras();
        infoList = bundle.getStringArrayList("number");

        newbuilt_cancel = (TextView)findViewById(R.id.newbuilt_cancel);
        newbuilt_save = (TextView)findViewById(R.id.newbuilt_save);
        newbuild_name = (EditText)findViewById(R.id.newbuild_name);
        newbuilt_main_tel = (EditText)findViewById(R.id.newbuilt_main_tel);
        newbuild_email = (EditText)findViewById(R.id.newbuild_email);
        warning_name = (TextView) findViewById(R.id.warning_name);
        warning_phone = (TextView) findViewById(R.id.warning_phone);

        newbuilt_main_tel.setText(infoList.get(0));
        //final String a = newbuilt_main_tel.toString();

        newbuilt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        newbuilt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newbuild_name.getText().toString().isEmpty()&&newbuilt_main_tel.getText().toString().isEmpty()){
                    warning_name.setText("姓名不能为空");
                    warning_phone.setText("电话号不能为空");
                }else if (newbuild_name.getText().toString().isEmpty() )
                {
                    warning_name.setText("姓名不能为空");
                    warning_phone.setText("");
                }else if (newbuilt_main_tel.getText().toString().isEmpty()){
                    warning_name.setText("");
                    warning_phone.setText("电话号不能为空");
                }else {
                    /*DBConnection con = new DBConnection();
                SQLiteDatabase db = con.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from contacts", null);*/

                /*ContentResolver resolver = getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor cursor = resolver.query(uri, null, null, null, null);*/

                    ContactsSQL contactsSQL = new ContactsSQL();
                    Cursor cursor = contactsSQL.selectContacts(getApplication());
                    while (cursor.moveToNext()) {
                        tel = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if (tel.equals(newbuilt_main_tel.getText().toString()) ) {
                            break;
                        }

                    }
                    if(tel.equals(newbuilt_main_tel.getText().toString())){
                        Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {

                    /*Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts");
                    ContentResolver resolver1 = getContentResolver();
                    ContentValues values = new ContentValues();
                    long contact_id = ContentUris.parseId(resolver1.insert(uri1, values));
                    Uri uri = Uri.parse("content://com.android.contacts/data");
                    //姓名
                    values.put("raw_contact_id", contact_id);
                    values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/name");
                    values.put("data2", newbuild_name.getText().toString());
                    resolver1.insert(uri, values);
                    values.clear();
                    //电话
                    values.put("raw_contact_id", contact_id);
                    values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
                    values.put("data2", "2");
                    values.put("data1", newbuilt_main_tel.getText().toString());
                    resolver1.insert(uri, values);
                    values.clear();
                    //邮箱
                    values.put("raw_contact_id", contact_id);
                    values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
                    values.put("data2", "2");
                    values.put("data1", newbuild_email.getText().toString());
                    resolver1.insert(uri, values);*/
                        ContactsSQL contactsSQL1 = new ContactsSQL();

                        contactsSQL1.insertContacts(getApplication(), newbuild_name.getText().toString(), newbuilt_main_tel.getText().toString(), newbuild_email.getText().toString());

                    /*ContentResolver resolver = getContentResolver();
                    Uri uri = CallLog.Calls.CONTENT_URI;
                    ContentValues values = new ContentValues();
                    values.put(CallLog.Calls.CACHED_NAME, newbuild_name.getText().toString());
                    resolver.update(uri, values, "number='"+newbuilt_main_tel.getText().toString()+"'", null);*/

                        /*CallLogSQL callLogSQL = new CallLogSQL();
                        callLogSQL.updateCallLogNum(getApplication(), newbuild_name.getText().toString(), newbuilt_main_tel.getText().toString());*/

                    }
                }
            }
        });

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认对话框");
        builder.setMessage("是否确定放弃保存");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
