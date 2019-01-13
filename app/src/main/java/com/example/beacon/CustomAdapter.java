package com.example.beacon;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String countryList[];
    private ArrayList<Integer> flags = new ArrayList<Integer>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] countryList, ArrayList<Integer> flags) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public String getItem(int i) {
        return countryList[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.lv_item, null);
        TextView country = (TextView) view.findViewById(R.id.textView);
        CheckBox icon = (CheckBox) view.findViewById(R.id.icon);
        country.setText(countryList[i]);
        boolean c = true;
        Integer n = flags.get(i);
        if (n == 0) {
            c = false;
        }
        icon.setChecked(c);
        return view;
    }
}