package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Svetlana on 06.10.2017.
 */

public class ContactsFragment extends Fragment {

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
        return inflater.inflate(R.layout.contacts, container, false);
    }
}
