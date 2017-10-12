package com.svetlana.jad_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolderCard>{

    protected List<CardModel> models;
    protected Context context;

    String data = "";

    public CardAdapter(List<CardModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public void setData(CharSequence data) {
        this.data = data.toString().trim();
    }

    class ViewHolderCard extends RecyclerView.ViewHolder {
        TextView tvTitle, tvValue;
        private Button btnSend;

        ViewHolderCard(View itemView) {
            super(itemView);

            if(itemView.getId() == R.id.listview_upper) {
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitleUpper);
                tvValue = (TextView) itemView.findViewById(R.id.tvValueUpper);
            }

            else if(itemView.getId() == R.id.listview_lower) {
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitleLower);
                tvValue = (TextView) itemView.findViewById(R.id.tvValueLower);
                btnSend = (Button) itemView.findViewById(R.id.btnSend);

                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(data.equals("")) {
                            Toast.makeText(context, "Введите данные", Toast.LENGTH_LONG).show();
                            return;
                        }

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
        }

        //Выполнение действия для первой карточки (Echo Request)
        private void echoPerform() {
            String[] tokens = data.split("[/]");

            //Получение списка ключей, которые нужно искать в файле json
            List<String> keyList = new ArrayList<String>(tokens.length / 2 + 1);
            for (String t : tokens) {
                if (!t.equals("")) {
                    int pos = Arrays.asList(tokens).indexOf(t);
                    if (pos % 2 == 0) {
                        keyList.add(t);
                    }
                }
            }
            try {
                //Получение результата
                String[] keyArr = new String[keyList.size()];
                keyList.toArray(keyArr);
                ParseJSON pj = new ParseJSON("http://echo.jsontest.com/" + data, keyArr, models.get(0).getCardTitle());
                pj.execute();
                models.set(0, pj.get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        //Выполнение действия валидации введённой строки в соответствии с
        private void validationPerform() {

            String[] keyArr;
            String url = "http://validate.jsontest.com/?json=" + data;

            Pattern p = Pattern.compile("^\\{\"[a-zA-Z]+\":\"[a-zA-Z]+\"\\}");
            Matcher m = p.matcher(data);

            //Назначение ключей в зависимости от корректности введёного запроса
            if(m.matches())
                keyArr = new String[] {"object_or_array", "empty", "parse_time_nanoseconds",
                        "validate", "size"};
            else keyArr = new String[] {"error", "object_or_array", "error_info", "validate"};

            try {
                ParseJSON pj = new ParseJSON(url, keyArr, models.get(1).getCardTitle());
                pj.execute();
                models.set(1, pj.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ViewHolderCard(inflater.inflate(null, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderCard holder, int position) {
        CardModel card = models.get(position);

        TextView tv = holder.tvTitle;
        TextView tv2 = holder.tvValue;

        tv.setText(card.getCardTitle());
        tv2.setText(getResult(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

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
}
