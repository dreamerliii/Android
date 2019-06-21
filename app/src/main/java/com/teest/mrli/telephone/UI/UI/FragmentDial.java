package com.teest.mrli.telephone.UI.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Model.CallLogSQL;
import com.teest.mrli.telephone.UI.Model.ContactsSQL;
import com.teest.mrli.telephone.UI.Controller.Friends;
import com.teest.mrli.telephone.UI.Model.GetPhoneNumberFromMobile;

/**
 * Created by DELL on 2018/11/12.
 */

public class FragmentDial extends Fragment{
    private TextView tv_display;
    private TextView tv_add;
    private ImageView iv_one;
    private ImageView iv_two;
    private ImageView iv_three;
    private ImageView iv_four;
    private ImageView iv_five;
    private ImageView iv_six;
    private ImageView iv_seven;
    private ImageView iv_eight;
    private ImageView iv_nine;
    private ImageView iv_zero;
    private ImageView iv_star;
    private ImageView iv_sharp;
    private ImageView iv_call;
    private ImageView iv_back;
    private String s = "";
    String tel;
    ArrayList<Friends> friendsList = new ArrayList<>();
    GetPhoneNumberFromMobile getPhoneNumberFromMobile;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            Bundle bundle = new Bundle();
            tv_display.setText("");
            s="";
            friendsList.clear();
            onActivityCreated(bundle);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dial, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_display = (TextView)getView().findViewById(R.id.tv_display);
        tv_add = (TextView)getView().findViewById(R.id.tv_add);
        iv_one = (ImageView)getView().findViewById(R.id.iv_one);
        iv_two = (ImageView)getView().findViewById(R.id.iv_two);
        iv_three = (ImageView)getView().findViewById(R.id.iv_three);
        iv_four = (ImageView)getView().findViewById(R.id.iv_four);
        iv_five = (ImageView)getView().findViewById(R.id.iv_five);
        iv_six = (ImageView)getView().findViewById(R.id.iv_six);
        iv_seven = (ImageView)getView().findViewById(R.id.iv_seven);
        iv_eight = (ImageView)getView().findViewById(R.id.iv_eight);
        iv_nine = (ImageView)getView().findViewById(R.id.iv_nine);
        iv_zero = (ImageView)getView().findViewById(R.id.iv_zero);
        iv_star = (ImageView)getView().findViewById(R.id.iv_star);
        iv_sharp = (ImageView)getView().findViewById(R.id.iv_sharp);
        iv_call = (ImageView)getView().findViewById(R.id.iv_call);
        iv_back = (ImageView)getView().findViewById(R.id.iv_back);

        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();

        friendsList = getPhoneNumberFromMobile.getPhoneNumberFromMobile(getActivity());

        tv_display.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence ss, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence ss, int start, int before, int count) {
                String input = tv_display.getText().toString().trim();
                if(!input.isEmpty()){
                    tv_add.setText("添加号码");
                    int i = 0;
                    while (i<friendsList.size()){
                        if (input.equals(friendsList.get(i).getTelephone())){
                            tv_add.setText(friendsList.get(i).getName());
                            tv_add.setClickable(false);
                            break;
                        }else {
                            tv_add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), NewUserActivity.class);
                                    ArrayList<String> info = new ArrayList<String>();
                                    info.add(s);
                                    Bundle bundle = new Bundle();
                                    bundle.putStringArrayList("number", info);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                        i++;
                    }

                }else{
                    tv_add.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable ss) {
            }
        });

        iv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "1";
                //String t = s;
                tv_display.setText(s);
            }
        });
        iv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "2";
                tv_display.setText(s);
            }
        });
        iv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "3";
                tv_display.setText(s);
            }
        });
        iv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "4";
                tv_display.setText(s);
            }
        });
        iv_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "5";
                tv_display.setText(s);
            }
        });
        iv_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "6";
                tv_display.setText(s);
            }
        });
        iv_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "7";
                tv_display.setText(s);
            }
        });
        iv_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "8";
                tv_display.setText(s);
            }
        });
        iv_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "9";
                tv_display.setText(s);
            }
        });
        iv_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "0";
                tv_display.setText(s);
            }
        });
        iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "*";
                tv_display.setText(s);
            }
        });
        iv_sharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += "#";
                tv_display.setText(s);
            }
        });
        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DBConnection con = new DBConnection();
                SQLiteDatabase db = con.getWritableDatabase();
                Cursor cursor1 = db.rawQuery("select * from contacts", null);*/

                /*ContentResolver resolver = getActivity().getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Uri uri2 = CallLog.Calls.CONTENT_URI;
                Cursor cursor1 = resolver.query(uri, null, null, null, null);*/
                ContactsSQL contactsSQL = new ContactsSQL();
                Cursor cursor1 = contactsSQL.selectContacts(getActivity());
                while (cursor1.moveToNext()){
                    String name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String tel = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (s.equals(tel)){
                       /* ContentValues values = new ContentValues();
                        values.put(CallLog.Calls.CACHED_NAME, name);
                        resolver.update(uri2, values, "number='"+tel+"'", null);*/
                        CallLogSQL callLogSQL = new CallLogSQL();
                        callLogSQL.updateCallLogNum(getActivity(), name, tel);

                    }
                }

                //Cursor cursor = db.rawQuery("select * from records", null);

                /*ContentResolver resolver1 = getActivity().getContentResolver();
                Uri uri1 = CallLog.Calls.CONTENT_URI;
                Cursor cursor = resolver1.query(uri1, null, null, null, null);*/
                CallLogSQL callLogSQL = new CallLogSQL();
                Cursor cursor = callLogSQL.selectCallLog(getActivity());
                if(cursor.moveToFirst()){

                    do{
                        tel = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                        if (s.equals(tel)){
                            /*String sql1 = "update records set cishu=cishu+1 where tel='"+ tel.toString() +"'";
                            db.execSQL(sql1);

                            dt = new Date();
                            time = dt.toLocaleString();
                            String sql = "update records set abc='"+ time.toString() + "' where tel='"+ tel.toString() +"'";
                            db.execSQL(sql);*/
                            break;
                        }
                    }while (cursor.moveToNext());

                    if (s.equals(tel.toString())){
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + s);
                        intent.setData(data);
                        startActivity(intent);
                    }
                    else {
                        /*dt = new Date();
                        time = dt.toLocaleString();
                        i = 1;
                        String sql = "insert into records(name, tel, abc, cishu) values('"+ s.toString() +"','"+ s.toString() +"','"+ time.toString() + "','"+ i +"')";
                        db.execSQL(sql);*/

                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + s);
                        intent.setData(data);
                        startActivity(intent);
                    }
                }
                else {
                    /*i = 1;
                    dt = new Date();
                    time = dt.toLocaleString();
                    String sql = "insert into records(name, tel, abc) values('"+ s.toString() +"','"+ s.toString() +"','"+ time.toString() + "')";
                    db.execSQL(sql);*/
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + s);
                    intent.setData(data);
                    startActivity(intent);
                }

            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s.length()>0){
                    s = s.substring(0,s.length()-1);
                    tv_display.setText(s);
                }else{
                    tv_display.setText("");
                }
            }
        });
    }

    /*public void checkAndRequestPermission() {
        //1、首先声明一个数组permissions，将需要的权限都放在里面
        String[] permissions = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_CONTACTS
        ,Manifest.permission.READ_CALL_LOG,Manifest.permission.WRITE_CALL_LOG};

        // 2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
        *//**
         * 判断哪些权限未授予
         *//*
        List<String> mPermissionList = new ArrayList<String>();
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        //3、判断permissionList是否为空，不为空，调用ActivityCompat.requestPermissions()授予权限。如果permissionList为空，表示权限都授予了，执行对应的方法
        *//**
         * 判断是否为空
         *//*
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

        } else {//请求权限方法
            permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);
        }
    }*/
}
