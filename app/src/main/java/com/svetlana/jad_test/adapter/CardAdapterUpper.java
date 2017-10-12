package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.svetlana.jad_test.CardModel;
import com.svetlana.jad_test.R;

import java.util.List;

/**
 * Created by Svetlana on 07.10.2017.
 */

public class CardAdapterUpper extends CardAdapter {

    public CardAdapterUpper(List<CardModel> models, Context context) {
        super(models, context);
    }

    @Override
    public ViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCard(inflater.inflate(R.layout.homecard_item_upper, parent, false));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
