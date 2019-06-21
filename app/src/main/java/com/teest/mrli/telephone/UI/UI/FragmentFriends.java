package com.teest.mrli.telephone.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.teest.mrli.telephone.R;
import com.teest.mrli.telephone.UI.Controller.Friends;
import com.teest.mrli.telephone.UI.Controller.FriendsAdapter;
import com.teest.mrli.telephone.UI.Model.GetPhoneNumberFromMobile;
import com.teest.mrli.telephone.UI.Controller.SideBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mr.Li on 2018/11/12.
 */

public class FragmentFriends extends Fragment {
    private ListView list_friend;//ListView控件
    private SideBar sideBar;//侧边栏控件
    ArrayList<Friends> friendsList = new ArrayList<>();//存储用户信息的数组
    private FriendsAdapter friendsAdapter;//friendAdapter类
    private EditText editText;//搜索框
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;//getPhoneNumberFromMobile类

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            Bundle bundle = new Bundle();
            list_friend = null;
            sideBar = null;
            editText = null;
            friendsList.clear();
            onActivityCreated(bundle);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_friends, container, false);
        return view;
//        return inflater.inflate(R.layout.fragment_friends,)
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list_friend =(ListView) getView().findViewById(R.id.friends_list);
        sideBar = (SideBar) getView().findViewById(R.id.sideBar);
        editText = (EditText) getView().findViewById(R.id.friends_search);

        list_friend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),FriendInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("Name",friendsList.get(position).getName());
                bundle.putString("Tel",friendsList.get(position).getTelephone());
                bundle.putString("email",friendsList.get(position).getEmail());
                bundle.putString("id",friendsList.get(position).getId());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < friendsList.size(); i++) {
                    if (selectStr.equalsIgnoreCase(friendsList.get(i).getFirstLetter())) {
                        list_friend.setSelection(i); // 选择到首字母出现的位置
                    }
                }
            }
        });
        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();

        friendsList = getPhoneNumberFromMobile.getPhoneNumberFromMobile(getActivity());

        Collections.sort(friendsList);

        friendsAdapter =new FriendsAdapter(getActivity(), R.layout.friend, friendsList);
        list_friend.setAdapter(friendsAdapter);
        //搜索框
        Search(friendsList);
    }

    public void Search(final ArrayList<Friends> list) {
        final String[] pinYin = new String[list.size()];
        final String[] firstLetter = new String[list.size()];
        final String[] everyfirstLetter = new String[list.size()];
        final int size = list.size();
        for (int i=0;i<size;i++)
        {
            pinYin[i]=list.get(i).getPinyin();
            firstLetter[i]=list.get(i).getFirstLetter();
            everyfirstLetter[i]=list.get(i).getEverfirstLetter();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = editText.getText().toString().trim();
                if(!input.isEmpty())
                {
                    for (int i=0;i<size;i++)
                    {
                        if (input.equals(pinYin[i])||input.equals(firstLetter[i].toLowerCase())||input.equals(everyfirstLetter[i]))
                        {
                            list_friend.setSelection(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}
