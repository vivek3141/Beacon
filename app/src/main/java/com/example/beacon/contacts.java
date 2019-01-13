package com.example.beacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.ListView;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.app.SearchManager;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.content.ContentResolver;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Intent;


public class contacts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        SearchView searchView = (SearchView) findViewById(R.id.search);
        final ListView listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> contactList = new ArrayList<>();
        Button button = (Button) findViewById(R.id.button2);

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(name + "\n" + phoneNumber);

        }
        phones.close();

        Collections.sort(contactList);
        final String list[] = contactList.toArray(new String[contactList.size()]);
        setContacts(list, listView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("","IN THE FUNC");
                ListAdapter adapter = listView.getAdapter();
                CheckBox cb;
                for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                    cb = (CheckBox)listView.getChildAt(i).findViewById(R.id.icon);
                    if (cb.isChecked()) {
                        Log.i("", adapter.getItem(i).toString());
                    }
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> search = new ArrayList<>();
                for (String i : contactList) {
                    if (i.substring(0, newText.length()).toUpperCase().equals(newText.toUpperCase())) {
                        search.add(i);
                    }
                }
                String[] list = search.toArray(new String[search.size()]);
                setContacts(list, listView);
                return false;
            }
        });

    }

    protected void setContacts(String[] list, ListView listView) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.lv_item, R.id.textView, list);
        listView.setAdapter(arrayAdapter);
    }
}
