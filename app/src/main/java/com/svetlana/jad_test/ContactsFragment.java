package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Класс представляет фрагмент страницы контактов
 */

public class ContactsFragment extends Fragment {

    //Создание нового экземпляра фрагмента Главного меню
    public static ContactsFragment newInstance() {
        ContactsFragment contactsFragment = new ContactsFragment();
        return contactsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contacts, container, false);

        return rootView;
    }
}
