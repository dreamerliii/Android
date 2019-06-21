package com.teest.mrli.telephone.UI.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Controller.Friends;
import com.teest.mrli.telephone.UI.Model.GetPhoneNumberFromMobile;

/**
 * Created by Mr.Li on 2018/12/11.
 */


public class EditUserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView newbuilt_cancel;//取消按钮
    private TextView newbuilt_save;//保存按钮
    private EditText newbuild_name;//联系人名字
    private EditText newbuilt_main_tel;//联系人电话
    private EditText newbuild_email;//联系人邮箱
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    String id = "";//全局变量，用于存储用户的id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbuilt);
        Bundle bundle=getIntent().getExtras();

        newbuilt_cancel = (TextView)findViewById(R.id.newbuilt_cancel);
        newbuilt_save = (TextView)findViewById(R.id.newbuilt_save);
        newbuild_name = (EditText)findViewById(R.id.newbuild_name);
        newbuilt_main_tel = (EditText)findViewById(R.id.newbuilt_main_tel);
        newbuild_email = (EditText)findViewById(R.id.newbuild_email);
        newbuilt_cancel.setOnClickListener(this);
        newbuilt_save.setOnClickListener(this);

        newbuild_name.setText(bundle.getString("name"));
        newbuilt_main_tel.setText(bundle.getString("Tel"));
        newbuild_email.setText(bundle.getString("email"));
        id = bundle.getString("id");

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

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.newbuilt_save)){
            Friends friends = new Friends(id,newbuild_name.getText().toString(),newbuilt_main_tel.getText().toString(),newbuild_email.getText().toString());
            getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
            try {
                getPhoneNumberFromMobile.updataCotact(friends,this);
                Intent intent = new Intent();
                intent.setAction("action.refreshFriend");
                Bundle bundle = new Bundle();
                bundle.putString("Tel",friends.getTelephone());
                bundle.putString("name", friends.getName());
                bundle.putString("email",friends.getEmail());
                intent.putExtras(bundle);
                sendBroadcast(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (v == findViewById(R.id.newbuilt_cancel)){
            showDialog();
        }
    }
}
