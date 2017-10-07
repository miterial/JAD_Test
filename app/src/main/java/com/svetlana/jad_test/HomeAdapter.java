package com.svetlana.jad_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CardListModel> models;

    public HomeAdapter(Context context, ArrayList<CardListModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(context, R.layout.list_cards, null);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView);
        TextView tvValue = (TextView) convertView.findViewById(R.id.textView2);

        CardListModel model = models.get(position);
        tvTitle.setText(model.title);
        tvValue.setText(model.value);

        return convertView;
    }
}
