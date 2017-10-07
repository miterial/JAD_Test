package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Svetlana on 06.10.2017.
 */

public class HomeFragment extends Fragment {

    private ArrayList<CardListModel> models;
    private ListView listView;
    private HomeAdapter homeAdapter;

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

        listView = (ListView) rootView.findViewById(R.id.home_listView);
        models = new ArrayList<>();
        // TODO: Динамическое получение ip адреса, json
        models.add(new CardListModel("IP-Address", "127.0.0.1"));
        models.add(new CardListModel("DateTime", "07.10.2017 21:45"));
        homeAdapter = new HomeAdapter(getActivity(), this.models);
        listView.setAdapter(homeAdapter);

        // Обработка нажатия
        //https://youtu.be/By_1tpPDt4g?t=18m15s

        return rootView;
    }

}
