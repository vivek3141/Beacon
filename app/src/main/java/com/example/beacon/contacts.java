package com.example.beacon;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
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
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        ListView listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> contactList = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(name + "\n" + phoneNumber);

        }
        phones.close();

        Collections.sort(contactList);
        String list[] = contactList.toArray(new String[contactList.size()]);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lv_item, R.id.textView, list);
        listView.setAdapter(arrayAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> search = new ArrayList<>();
                for(String i: contactList){
                    if(i.substring(0,newText.length()).equals(newText)){
                        search.add(i);
                    }
                }
                String[] list = search.toArray(new String[search.size()]);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lv_item, R.id.textView, list);
                return false;
            }
        });
    }
}
