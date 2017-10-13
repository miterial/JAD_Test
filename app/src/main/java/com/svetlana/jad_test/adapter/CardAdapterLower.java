package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.svetlana.jad_test.CardModel;
import com.svetlana.jad_test.R;

import java.util.List;

/**
 * Класс предоставляет xml второго вида карточек для размещения во фрагменте HomeFragment
 */

public class CardAdapterLower extends CardAdapter  {

    public CardAdapterLower(List<CardModel> models, Context context) {
        super(models, context);
    }

    @Override
    public ViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCard(inflater.inflate(R.layout.homecard_item_lower, parent, false));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
