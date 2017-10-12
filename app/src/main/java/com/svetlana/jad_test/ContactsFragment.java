package com.svetlana.jad_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Svetlana on 06.10.2017.
 */

public class ContactsFragment extends Fragment {
    String[] data = {"E-mail", "Phone", "Projects"};

    GridView gvMain;
    ArrayAdapter<String> adapter;

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
