package com.ourincheon.app_center.mainActivity.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ourincheon.app_center.etc.Loading;
import com.ourincheon.app_center.R;

import java.util.ArrayList;

public class Setting extends Fragment {

    public Setting(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_profile, container, false);

        ListView listView = (ListView) layout.findViewById(R.id.setting_item);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager touch_hide = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                touch_hide.hideSoftInputFromWindow(container.getWindowToken(), 0);
            }
        });

        Intent intent = getActivity().getIntent();
        final String clubnum = intent.getStringExtra("clubIdNumber");

        TextView clubNB = (TextView) layout.findViewById(R.id.clubID);
        clubNB.setText(clubnum);


        final ArrayList<String> settingList = new ArrayList<String>();
        settingList.add("행사 등록");
        settingList.add("행사 수정");
        settingList.add("동아리 내용 수정");
        settingList.add("동아리 사진 수정");
        settingList.add("로그아웃");


        ArrayAdapter<String> listviewapater = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, settingList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textview = (TextView) view.findViewById(android.R.id.text1);
                textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                textview.setPadding(10, 70, 10, 50);

                return view;
            }
        };

        listView.setAdapter(listviewapater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Loading.class);
                intent.putExtra("listValue", settingList.get(i).toString());
                intent.putExtra("clubIdNumber", clubnum);
                startActivity(intent);
            }
        });


        return layout;
    }
}
