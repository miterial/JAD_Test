package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.svetlana.jad_test.CardModel;
import com.svetlana.jad_test.JSON.ParseJSON;
import com.svetlana.jad_test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Svetlana on 10.10.2017.
 */

public class CardAdapterLower extends CardAdapter  {

    public CardAdapterLower(List<CardModel> models, Context context) {
        super(models, context);
    }

    @Override
    public ViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCard(inflater.inflate(R.layout.card_item_lower, parent, false));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
