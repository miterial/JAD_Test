package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.svetlana.jad_test.CardItem;
import com.svetlana.jad_test.R;

import java.util.List;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class HomeAdapterUpper extends BaseAdapter {

    private Context context;
    private List<CardItem> models;
    private final int LAYOUT;

    public HomeAdapterUpper(Context context, List<CardItem> models, int layout) {
        this.context = context;
        this.models = models;
        LAYOUT = layout;
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
            convertView = View.inflate(context, R.layout.card_item_upper, null);
        }

        TextView tvData = (TextView) convertView.findViewById(R.id.tvUpperValue);

        // Запись информации в карточки
        // Создание строки-результата
        String res = "";
        for(int i = 0; i < models.get(position).getKeys().size(); i++){
            res += models.get(position).getKeys().get(i) + " - "
                    + models.get(position).getValues().get(i) + "\n";
        }

        tvData.setText(res);

        return convertView;
    }
}
