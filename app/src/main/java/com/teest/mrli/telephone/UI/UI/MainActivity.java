package com.teest.mrli.telephone.UI.UI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teest.mrli.telephone.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView_yellowpages;//黄页按键
    private TextView textView_callRecord;//通话记录按键
    private TextView textView_friends;//联系人按键
    private TextView textView_dial;//拨号按钮
    private TextView textView_headname;//顶部名称按钮

    FragmentYellowPage fragmentYellowPage;
    FragmentFriends fragmentFriends;
    FragmentRecord fragmentRecord;
    FragmentDial fragmentDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_yellowpages = (TextView) findViewById(R.id.yellowpages_text);
        textView_callRecord = (TextView) findViewById(R.id.callRecord_text);
        textView_friends = (TextView) findViewById(R.id.friends_text);
        textView_dial = (TextView) findViewById(R.id.dial_text);
        textView_headname = (TextView) findViewById(R.id.headname);

        textView_dial.setTextColor(0xFF45C01A);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        fragmentDial = new FragmentDial();
        beginTransaction.add(R.id.fragment_container,fragmentDial).commit();
    }

    public void onTabClicked(View view) {
        if (view == findViewById(R.id.yellowpages)) {
            textView_headname.setText("黄页");
            textView_yellowpages.setTextColor(0xFF45C01A);//0xFF999999白色
            textView_callRecord.setTextColor(0xFF999999);
            textView_friends.setTextColor(0xFF999999);
            textView_dial.setTextColor(0xFF999999);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            hideAll(beginTransaction);
            if (fragmentYellowPage == null){
                fragmentYellowPage = new FragmentYellowPage();
                beginTransaction.add(R.id.fragment_container,fragmentYellowPage).commit();
            }else {
                beginTransaction.show(fragmentYellowPage).commit();
            }

        } else if (view == findViewById(R.id.callRecord)) {
            textView_headname.setText("通话记录");
            textView_yellowpages.setTextColor(0xFF999999);//0xFF999999白色
            textView_callRecord.setTextColor(0xFF45C01A);
            textView_friends.setTextColor(0xFF999999);
            textView_dial.setTextColor(0xFF999999);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            hideAll(beginTransaction);
            if (fragmentRecord == null){
                fragmentRecord = new FragmentRecord();
                beginTransaction.add(R.id.fragment_container,fragmentRecord).commit();
            }else {
                fragmentRecord.onResume();
                beginTransaction.show(fragmentRecord).commit();
            }
        } else if (view == findViewById(R.id.friends)) {
            textView_headname.setText("联系人");
            textView_yellowpages.setTextColor(0xFF999999);//0xFF999999白色
            textView_callRecord.setTextColor(0xFF999999);
            textView_friends.setTextColor(0xFF45C01A);
            textView_dial.setTextColor(0xFF999999);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            hideAll(beginTransaction);
            if (fragmentFriends==null){
                fragmentFriends = new FragmentFriends();
                beginTransaction.add(R.id.fragment_container, fragmentFriends).commit();
            }else {
                fragmentFriends.onResume();
                beginTransaction.show(fragmentFriends).commit();
            }
            /*beginTransaction.commit();*/
        } else if (view == findViewById(R.id.dial)) {
            textView_headname.setText("拨号");
            textView_yellowpages.setTextColor(0xFF999999);//0xFF999999白色
            textView_callRecord.setTextColor(0xFF999999);
            textView_friends.setTextColor(0xFF999999);
            textView_dial.setTextColor(0xFF45C01A);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            hideAll(beginTransaction);
            fragmentDial.onResume();
            beginTransaction.show(fragmentDial).commit();
        }
    }

    public void hideAll(FragmentTransaction beginTransaction){
        if (fragmentYellowPage!=null)
        {
            beginTransaction.hide(fragmentYellowPage);
        }
        if (fragmentFriends!=null)
        {
            beginTransaction.hide(fragmentFriends);
        }
        if(fragmentRecord!=null)
        {
            beginTransaction.hide(fragmentRecord);
        }
        beginTransaction.hide(fragmentDial);
    }

}
