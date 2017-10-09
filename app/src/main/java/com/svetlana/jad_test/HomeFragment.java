package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.svetlana.jad_test.JSON.ParseJSON;
import com.svetlana.jad_test.adapter.HomeAdapterUpper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Svetlana on 06.10.2017.
 */

public class HomeFragment extends Fragment {

    private List<CardItem> models;
    private ListView listView;
    private HomeAdapterUpper homeAdapter;
    private List<CardItem> titles;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);

        listView = (ListView) rootView.findViewById(R.id.upper_listView);
        models = new ArrayList<>();

        // Компоненты, значения которых нужно получить с сайта
        Map<String, String[]> cards = new HashMap<>();
        String[] ipTitle = {"ip"};
        String[] datetimeTitle = {"date", "time"};
        String[] headersTitle = {"X-Cloud-Trace-Context", "Upgrade-Insecure-Requests", "Accept-Language",
                "Host", "Referer", "DNT", "User-Agent", "Accept"};
        String[] keys = {"http://ip.jsontest.com/", "http://date.jsontest.com", "http://headers.jsontest.com/"};
        cards.put(keys[0], ipTitle);
        cards.put(keys[1], datetimeTitle);
        cards.put(keys[2], headersTitle);

        // Создание карточки
        for (int i = 0; i < cards.size(); i++) {
            models.add(getCardData(keys[i], cards.get(keys[i])));
        }

        homeAdapter = new HomeAdapterUpper(getActivity(), this.models, R.layout.card_item_upper);
        listView.setAdapter(homeAdapter);

        // Обработка нажатия
        //https://youtu.be/By_1tpPDt4g?t=18m15s

        return rootView;
    }

    private CardItem getCardData(String key, String[] titles) {

        try {
            ParseJSON pj = new ParseJSON(key, titles);
            pj.execute();
            return pj.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
