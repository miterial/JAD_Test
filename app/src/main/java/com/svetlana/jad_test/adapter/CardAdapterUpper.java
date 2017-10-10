package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svetlana.jad_test.CardModel;
import com.svetlana.jad_test.R;

import java.util.List;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class CardAdapterUpper extends RecyclerView.Adapter<CardAdapterUpper.ViewHolderCardUpper> {

    public class ViewHolderCardUpper extends RecyclerView.ViewHolder {

        TextView tvTitle, tvValue;

        public ViewHolderCardUpper(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleUpper);
            tvValue = (TextView) itemView.findViewById(R.id.tvValueUpper);
        }
    }

    private List<CardModel> models;
    private Context context;

    public CardAdapterUpper(Context context, List<CardModel> models) {
        this.models = models;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolderCardUpper onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCardUpper(inflater.inflate(R.layout.card_item_upper, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderCardUpper holder, int position) {
        CardModel card = models.get(position);

        TextView tv = holder.tvTitle;

        TextView tv2 = holder.tvValue;

        String res = "";
        if(!models.isEmpty()) {
            for (int i = 0; i < models.get(position).getKeys().size(); i++) {
                res += models.get(position).getKeys().get(i) + " - "
                        + models.get(position).getValues().get(i) + "\n";
            }

            tv.setText(card.getCardTitle());
            tv2.setText(res);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }



    /*
    private Context context;
    private List<CardModel> models;
    TextView tvTitle, tvResult;
    private int layoutID, titleID, resultID;

    public CardAdapterUpper(Context context, List<CardModel> models, int layout, int titleID, int resultID) {
        this.context = context;
        this.models = models;
        this.layoutID = layout;
        this.titleID = titleID;
        this.resultID = resultID;
    }

    public CardAdapterUpper(Context context, int layoutID, int titleID, int resultID) {
        this.context = context;
        this.layoutID = layoutID;
        this.titleID = titleID;
        this.resultID = resultID;
        this.models = new ArrayList<>();
    }

    public void setModels(List<CardModel> models) {
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
    public CardAdapterUpper.ViewHolderCardUpper onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardAdapterUpper.ViewHolderCardUpper holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(context, layoutID, null);
        }

        tvTitle = (TextView) convertView.findViewById(titleID);
        tvResult = (TextView) convertView.findViewById(resultID);

        // Запись информации в карточки
        // Создание строки-результата
        String res = "";
        if(!models.isEmpty()) {
            for (int i = 0; i < models.get(position).getKeys().size(); i++) {
                res += models.get(position).getKeys().get(i) + " - "
                        + models.get(position).getValues().get(i) + "\n";
            }

            tvTitle.setText(models.get(position).getCardTitle());
            tvResult.setText(res);
        }


        return convertView;
    }*/

}
