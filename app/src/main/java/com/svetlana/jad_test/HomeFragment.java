package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.svetlana.jad_test.JSON.ParseJSON;
import com.svetlana.jad_test.adapter.CardAdapter;
import com.svetlana.jad_test.adapter.CardAdapterLower;
import com.svetlana.jad_test.adapter.CardAdapterUpper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Svetlana on 06.10.2017.
 */

public class HomeFragment extends Fragment {

    List<String> items;

    //Создание нового экземпляра фрагмента Главного меню
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home, container, false);

        initUpperCards(rootView);

        initLowerCards(rootView);

        return rootView;
    }

    //Заполнение верхнего списка карточек
    private void initUpperCards(View rootView) {
        // Список карточек
        RecyclerView recViewUpper = (RecyclerView) rootView.findViewById(R.id.upper_recyclerV);
        List<CardModel> modelsUpper = new ArrayList<>();

        // Servicenames
        String[] urls = {"ip", "date", "headers"};

        //Заголовки карточек
        String[] cardTitles = {"IP-Address", "Date & Time", "Headers"};

        // Создание карточки
        for (int i = 0; i < urls.length; i++) {
            modelsUpper.add(getCardData(urls[i], cardTitles[i]));
        }

        //Присвоение адаптера
        CardAdapter hAdap1 = new CardAdapterUpper(modelsUpper, getContext());

        recViewUpper.setAdapter(hAdap1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recViewUpper.setLayoutManager(llm);
    }

    //Инициализация нижнего списка карточек
    private void initLowerCards(View rootView) {
        RecyclerView recViewLower = (RecyclerView) rootView.findViewById(R.id.lower_recyclerV);

        List<CardModel> modelsLower = new ArrayList<>();

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        modelsLower.add(new CardModel(keys, values, "Echo"));
        modelsLower.add(new CardModel(keys, values, "Validation"));

        final CardAdapter hAdap2 = new CardAdapterLower(modelsLower, getContext());
        recViewLower.setAdapter(hAdap2);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recViewLower.setLayoutManager(llm);

        EditText editText = (EditText) rootView.findViewById(R.id.etRequest);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hAdap2.setData(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    private CardModel getCardData(String key, String cardTitle) {

        try {
            ParseJSON pj = new ParseJSON(key, cardTitle);
            pj.execute();
            return pj.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

