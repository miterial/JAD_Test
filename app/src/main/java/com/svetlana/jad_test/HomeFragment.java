package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svetlana.jad_test.JSON.ParseJSON;
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

    private List<CardModel> modelsUpper, modelsLower;
    Map<String, String[]> items;
    String URL;

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
        items = new HashMap<>();

        initUpperCards(rootView);

        initLowerCards(rootView);

        // Обработка нажатия

        return rootView;
    }

    public void setUrl(String u) {
        URL = "http://http://echo.jsontest.com/" + u;
    }

    private void initLowerCards(View rootView) {
        RecyclerView recViewLower = (RecyclerView) rootView.findViewById(R.id.lower_recyclerV);

        modelsLower = new ArrayList<>();

        List<String> keys = new ArrayList<>();
        keys.add("k1");
        keys.add("k1");
        List<String> values = new ArrayList<>();
        values.add("v1");
        values.add("v1");

        modelsLower.add(new CardModel(keys, values, "Echo"));
        modelsLower.add(new CardModel(keys, values, "Validation"));

        CardAdapterLower hAdap2 = new CardAdapterLower(getContext(), modelsLower);
        recViewLower.setAdapter(hAdap2);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recViewLower.setLayoutManager(llm);

    }

    //Заполнение верхнего списка
    private void initUpperCards(View rootView) {
        RecyclerView recViewUpper = (RecyclerView) rootView.findViewById(R.id.upper_recyclerV);
        modelsUpper = new ArrayList<>();

        // Компоненты, значения которых нужно получить с сайта
        String[] ipTitle = {"ip"};
        String[] datetimeTitle = {"date", "time"};
        String[] headersTitle = {"X-Cloud-Trace-Context", "Upgrade-Insecure-Requests", "Accept-Language",
                "Host", "Referer", "DNT", "User-Agent", "Accept"};
        String[] keys = {"http://ip.jsontest.com/", "http://date.jsontest.com", "http://headers.jsontest.com/"};
        items.put(keys[0], ipTitle);
        items.put(keys[1], datetimeTitle);
        items.put(keys[2], headersTitle);

        String[] cardTitles = {"IP-Address", "Date & Time", "Headers"};

        // Создание карточки
        for (int i = 0; i < items.size(); i++) {
            modelsUpper.add(getCardData(keys[i], items.get(keys[i]), cardTitles[i]));
        }

        //Присвоение адаптера
        CardAdapterUpper hAdap1 = new CardAdapterUpper(getContext(), modelsUpper);

        recViewUpper.setAdapter(hAdap1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recViewUpper.setLayoutManager(llm);
    }

    private CardModel getCardData(String key, String[] titles, String cardTitle) {

        try {
            ParseJSON pj = new ParseJSON(key, titles, cardTitle);
            pj.execute();
            return pj.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

