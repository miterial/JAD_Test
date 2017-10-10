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

/**
 * Created by Svetlana on 10.10.2017.
 */

public class CardAdapterLower extends RecyclerView.Adapter<CardAdapterLower.ViewHolderCardLower>  {

    private Button btnSend;
    private EditText et;

    private List<String> keyList;
    private List<String> valueList;
    private List<CardModel> models;
    private Context context;

    public class ViewHolderCardLower extends RecyclerView.ViewHolder {
        TextView tvTitle, tvValue;

        public ViewHolderCardLower(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleLower);
            tvValue = (TextView) itemView.findViewById(R.id.tvValueLower);
            btnSend = (Button) itemView.findViewById(R.id.btnSend);

            btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getLayoutPosition();
                    switch (position) {
                        case 0:
                            echoPerform();
                            break;
                        case 1:
                            validationPerform();
                            break;
                    }

                    notifyDataSetChanged();

                        tvTitle.setText(models.get(position).getCardTitle());
                        tvValue.setText(getResult(position));
                    }

            });

        }

        //Выполнение действия для первой карточки (Echo Request)
        private void echoPerform() {
            et = (EditText) itemView.findViewById(R.id.etRequest);
            String url = et.getText().toString();
            String[] tokens = url.split("[/]");

            keyList = new ArrayList<String>(tokens.length / 2 + 1);
            valueList = new ArrayList<String>(tokens.length / 2 + 1);
            for (String t : tokens) {
                if (!t.equals("")) {
                    int pos = Arrays.asList(tokens).indexOf(t);
                    if (pos % 2 == 0) {
                        keyList.add(t);
                    }
                }
            }
            try {
                String[] keyArr = new String[keyList.size()];
                keyList.toArray(keyArr);
                ParseJSON pj = new ParseJSON("http://echo.jsontest.com/" + url, keyArr, "EchoRes");
                pj.execute();
                models.set(0, pj.get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    //Выполнение действия
    private void validationPerform() {
        Toast.makeText(getContext(), "Validation",Toast.LENGTH_LONG).show();
    }

    public CardAdapterLower(Context context, List<CardModel> models) {
        this.models = models;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolderCardLower onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCardLower(inflater.inflate(R.layout.card_item_lower, parent, false));
    }

    @Override
    public void onBindViewHolder(CardAdapterLower.ViewHolderCardLower holder, int position) {
        CardModel card = models.get(position);

        TextView tv = holder.tvTitle;
        TextView tv2 = holder.tvValue;

        tv.setText(card.getCardTitle());
        tv2.setText(getResult(position));
    }

    //Вывод результата в виде, удобном для чтения
    private String getResult(int position) {
        String res = "";
        if (!models.isEmpty()) {
            for (int i = 0; i < models.get(position).getKeys().size(); i++) {
                res += models.get(position).getKeys().get(i) + " - "
                        + models.get(position).getValues().get(i) + "\n";
            }
        }
        return res;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
