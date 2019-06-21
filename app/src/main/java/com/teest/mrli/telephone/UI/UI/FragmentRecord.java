package com.teest.mrli.telephone.UI.UI;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Model.CallLogSQL;
import com.teest.mrli.telephone.UI.Model.ContactsSQL;
import com.teest.mrli.telephone.UI.Controller.Message;
import com.teest.mrli.telephone.UI.Controller.MessageAdapter;
import com.teest.mrli.telephone.UI.Controller.TimeUtils;

/**
 * Created by DELL on 2018/11/13.
 */

public class FragmentRecord extends Fragment {
    private ListView record_list;
    String tel;
    private ArrayList<Message> messageList = new ArrayList<Message>();


    private int picture[] = new int[]{R.drawable.user_apple_pic, R.drawable.user_banana_pic,R.drawable.user_watermelon_pic,R.drawable.user_cherry_pic,R.drawable.user_orange_pic};


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            Bundle bundle = new Bundle();
            record_list = null;
            tel = null;
            messageList.clear();
            onActivityCreated(bundle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        record_list = (ListView)getView().findViewById(R.id.record_list);



        /*ContentResolver resolver = getActivity().getContentResolver();
        Uri uri = CallLog.Calls.CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null, null, null);*/
        CallLogSQL callLogSQL = new CallLogSQL();
        Cursor cursor = callLogSQL.selectCallLog(getActivity());
        Random random = new Random();

        while (cursor.moveToNext()){
            //String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME)); //联系人姓名
            String name = "";
            tel = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));              //联系人电话号码
            ContactsSQL contactsSQL = new ContactsSQL();
            Cursor cursor1 = contactsSQL.selectContacts(getActivity());
            while (cursor1.moveToNext()){
                String tel1 = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name1 = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if (tel1.equals(tel)){
                    name = name1;
                    break;
                }else {
                    name = tel;
                }
            }
            /*if (name == null){
                name = tel;
                *//*ContentValues values2 = new ContentValues();
                values2.put(CallLog.Calls.CACHED_NAME, name);
                resolver.update(uri, values2, "number='"+name+"'", null);*//*
                CallLogSQL callLogSQL1 = new CallLogSQL();
                callLogSQL1.updateCallLogNum(getActivity(), name, name);
            }*/

            TimeUtils timeUtils = new TimeUtils();
            long duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));//通话时长
            int d = (int)duration;
            String dur = timeUtils.getTime(d);
            long time = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));        //通话日期
            String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(time));
            int a = random.nextInt(5);
            int state = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));       //通话类型
            String type = "";
            switch (state){
                case 1:type = "打入";break;
                case 2:type = "呼出";break;
                case 3:type = "未接";break;
            }
            messageList.add(new Message(picture[a], name, dur, date, type));
        }


        record_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CallLogSQL callLogSQL1 = new CallLogSQL();
                Cursor cursor1 = callLogSQL1.selectCallLog(getActivity());
                String num = "";
                String name = "";
                while (cursor1.moveToNext()) {
                    num = cursor1.getString(cursor1.getColumnIndex(CallLog.Calls.NUMBER));
                    ContactsSQL contactsSQL = new ContactsSQL();
                    Cursor cursor2 = contactsSQL.selectContacts(getActivity());
                    while (cursor2.moveToNext()){
                        String tel1 = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String name1 = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        if (tel1.equals(num)){
                            name = name1;
                            break;
                        }else {
                            name = tel;
                        }
                    }

                    //name = cursor1.getString(cursor1.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    if (name.equals(messageList.get(position).getName())){
                        break;
                    }
                }
                if (name.equals(messageList.get(position).getName())){
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + num);
                    intent.setData(data);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + messageList.get(position).getName());
                    intent.setData(data);
                    startActivity(intent);
                }

                record_list.setAdapter(new MessageAdapter(getActivity(),R.layout.item_record_single,messageList));

            }
        });

        record_list.setAdapter(new MessageAdapter(getActivity(),R.layout.item_record_single,messageList));


    }


}
