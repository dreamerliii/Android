package com.teest.mrli.telephone.UI.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teest.mrli.telephone.R;

/**
 * Created by Mr.Li on 2018/11/12.
 */

public class FragmentYellowPage extends Fragment {
    private TextView fire;//火警
    private TextView doctor;//救护车
    private TextView police;//警察
    private TextView sf;//顺丰
    private TextView zt;//中通
    private TextView yt;//圆通
    private TextView Mcdonald;//麦当劳
    private TextView KFC;//肯德基
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_yellowpage, container, false);
        return view;
//        return inflater.inflate(R.layout.fragment_friends,)
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fire = (TextView) getView().findViewById(R.id.fire);
        doctor = (TextView) getView().findViewById(R.id.doctor);
        police = (TextView) getView().findViewById(R.id.police);
        sf = (TextView) getView().findViewById(R.id.sf);
        zt = (TextView) getView().findViewById(R.id.zt);
        yt = (TextView) getView().findViewById(R.id.yt);
        Mcdonald = (TextView) getView().findViewById(R.id.Mcdonald);
        KFC = (TextView) getView().findViewById(R.id.KFC);

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "119");
                intent.setData(data);
                startActivity(intent);
            }
        });
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "120");
                intent.setData(data);
                startActivity(intent);
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "110");
                intent.setData(data);
                startActivity(intent);
            }
        });
        sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "95338");
                intent.setData(data);
                startActivity(intent);
            }
        });
        zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "95311");
                intent.setData(data);
                startActivity(intent);
            }
        });
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "95554");
                intent.setData(data);
                startActivity(intent);
            }
        });
        Mcdonald.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "4008517517");
                intent.setData(data);
                startActivity(intent);
            }
        });
        KFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "4008823823");
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
