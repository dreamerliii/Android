package com.teest.mrli.telephone.UI.UI;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Controller.Friends;
import com.teest.mrli.telephone.UI.Model.GetPhoneNumberFromMobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by Mr.Li on 2018/11/14.
 */

public class FriendInfo extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private TextView nameText;//联系人姓名
    private boolean collected=false;//是否被收藏
    private TextView phoneText;//联系人电话
    private ToggleButton toggleButton;//点击收藏按钮
    private TextView emailText;//联系人邮箱
    private Button deleteButton;//删除按钮
    private TextView call;//打电话按钮
    
    private TextView backto_friend;//返回按钮
    private TextView edit;//编辑按钮


    FragmentFriends fragmentFriends = new FragmentFriends();

    Friends friends = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_info);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshFriend");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);

        Bundle bundle=getIntent().getExtras();
        nameText = (TextView) findViewById(R.id.friend_info_name);
        toggleButton = (ToggleButton) findViewById(R.id.tb1);
        phoneText = (TextView) findViewById(R.id.friend_info_tel);
        emailText = (TextView) findViewById(R.id.friend_info_email);
        backto_friend = (TextView) findViewById(R.id.back_to_friend);
        deleteButton = (Button) findViewById(R.id.delete);
        call = (TextView) findViewById(R.id.call);
        edit = (TextView) findViewById(R.id.edit);

        nameText.setText(bundle.getString("Name"));
        phoneText.setText(bundle.getString("Tel"));
        emailText.setText(bundle.getString("email"));

        friends = new Friends(bundle.getString("Name"));
        friends.setTelephone(bundle.getString("Tel"));
        friends.setEmail(bundle.getString("email"));
        friends.setId(bundle.getString("id"));


        backto_friend.setOnClickListener(this);
        edit.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        call.setOnClickListener(this);
        ToggleButton();
    }

    @Override
    public void onClick(View v) {
        if (v==findViewById(R.id.back_to_friend))
        {
            fragmentFriends.onResume();
            finish();
            //beginTransaction.show(fragmentFriends).commit();

        }else if (v==findViewById(R.id.edit)) {
            Intent intent = new Intent(FriendInfo.this, EditUserActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("id",friends.getId());
            bundle.putString("Tel",phoneText.getText().toString());
            bundle.putString("name",nameText.getText().toString());
            bundle.putString("email",emailText.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }else if (v==findViewById(R.id.delete)) {
            GetPhoneNumberFromMobile getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
            try {
                getPhoneNumberFromMobile.DeleteCotact(friends,this);
                fragmentFriends.onResume();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (v == findViewById(R.id.call)){
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + phoneText.getText().toString());
            intent.setData(data);
            startActivity(intent);
        }
    }
    //设置toggleButton
    void ToggleButton(){
        if (collected==true)
        {
            toggleButton.setChecked(true);
        }
        else
        {
            toggleButton.setChecked(false);
        }
        toggleButton.setOnCheckedChangeListener(this);//设置toggleButton监听事件
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        toggleButton.setChecked(isChecked);
    }

    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshFriend"))
            {
                nameText.setText(intent.getExtras().getString("name"));
                phoneText.setText(intent.getExtras().getString("Tel"));
                emailText.setText(intent.getExtras().getString("email"));
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }
}
