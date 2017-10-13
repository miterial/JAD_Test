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

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
*   Класс-адаптер к двум видам карточек
*/
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolderCard>{

    //Список карточек
    List<CardModel> models;
    private Context context;

    //Строка для значения из EditText
    private String data = "";

    CardAdapter(List<CardModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    //Установить значение для пользовательского запроса
    public void setData(CharSequence data) {
        this.data = data.toString().trim();
    }

    class ViewHolderCard extends RecyclerView.ViewHolder {
        TextView tvTitle, tvValue;
        private Button btnSend;

        ViewHolderCard(View itemView) {
            super(itemView);

            //Инициализация объектов view в зависимости от того, к какому виду принадлежит layout
            if(itemView.getId() == R.id.upper_card) {
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitleUpper);
                tvValue = (TextView) itemView.findViewById(R.id.tvValueUpper);
            }

            else if(itemView.getId() == R.id.lower_card) {
                tvTitle = (TextView) itemView.findViewById(R.id.tvTitleLower);
                tvValue = (TextView) itemView.findViewById(R.id.tvValueLower);
                btnSend = (Button) itemView.findViewById(R.id.btnSend);

                //Назначение обработчика кнопке по нажатию
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Если текстовое поле пустое
                        if(data.equals("")) {
                            Toast.makeText(context, "Введите данные", Toast.LENGTH_LONG).show();
                            return;
                        }

                        //Выполнение действия в зависимости от номера карточки в списке
                        int position = getLayoutPosition();
                        switch (position) {
                            case 0:
                                //Выполнение действия для первой карточки (Echo Request)
                                performClick(0, "echo", data, models.get(0).getCardTitle());
                                break;
                            case 1:
                                //Выполнение действия валидации введённой строки в соответствии с регулярным выражением
                                performClick(1, "validate", "?json=" + data, models.get(1).getCardTitle());
                                break;
                        }

                        notifyDataSetChanged();

                        tvTitle.setText(models.get(position).getCardTitle());
                        tvValue.setText(getResult(position));
                    }

                });
            }
        }

        private void performClick(int pos, String service, String data, String cardTitle) {
            try {
                //Получение результата
                ParseJSON pj = new ParseJSON(service, data, cardTitle);
                pj.execute();
                models.set(pos, pj.get());
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

    //Построчный вывод в TextView теста из файла json в виде "ключ" - "значение"
    private String getResult(int position) {
        String res = "";
        if (!models.isEmpty())
            for (int i = 0; i < models.get(position).getKeys().size(); i++) {
                res += models.get(position).getKeys().get(i) + " - "
                        + models.get(position).getValues().get(i) + "\n";
            }
        return res;
    }
}
