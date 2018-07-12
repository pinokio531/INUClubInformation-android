package com.ourincheon.app_center.list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ourincheon.app_center.R;

import java.util.ArrayList;

public class ListViewAdaptor extends BaseAdapter {

    private LayoutInflater layoutInflater = null;
    private ArrayList<ListViewItem> itemCategory = null;
    private int layout;

    public ListViewAdaptor(ArrayList<ListViewItem> itemCategory){
        this.itemCategory = itemCategory;
        this.layout = itemCategory.size();
    }

    @Override
    public int getCount() {
        return layout;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            final Context context = parent.getContext();
            if(layoutInflater == null){
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = layoutInflater.inflate(R.layout.club_list_item, parent, false);
        }

        TextView category = (TextView) convertView.findViewById(R.id.clubExplain);
        TextView name = (TextView) convertView.findViewById(R.id.clubName);
        TextView location = (TextView) convertView.findViewById(R.id.clubPlace);

        category.setText(itemCategory.get(position).clubCategory);
        name.setText(itemCategory.get(position).clubName);
        location.setText(itemCategory.get(position).clubLocation);

        return convertView;
    }

}
