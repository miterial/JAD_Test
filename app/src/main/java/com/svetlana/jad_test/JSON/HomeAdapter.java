package com.svetlana.jad_test.JSON;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.svetlana.jad_test.R;

import java.util.ArrayList;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> models;

    public HomeAdapter(Context context, ArrayList<String> models) {
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

        TextView tvData = (TextView) convertView.findViewById(R.id.tvCardData);

        // Запись информации в карточки
        String modelData = models.get(position);
        tvData.setText(modelData);

        return convertView;
    }
}
