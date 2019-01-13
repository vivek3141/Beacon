package com.example.beacon;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.ListView;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.content.ContentResolver;
import java.util.Arrays;
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
        ArrayList<String> phoneList = new ArrayList<>();
        ArrayList<String> contactList = new ArrayList<>();


        //listView.setAdapter(adapter);
        //searchEditText.setTextColor(getResources().getColor(R.color.white));
        //searchEditText.setHintTextColor(getResources().getColor(R.color.white));

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactList.add(name + "\n" + phoneNo);
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
        phones.close();
        for(String i: contactList){
            Log.i("",i);
        }
        Collections.sort(contactList);
        String list[] = contactList.toArray(new String[contactList.size()]);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.lv_item, R.id.textView, list);
        listView.setAdapter(arrayAdapter);
        Log.i("", Arrays.toString(list));
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(query);
        }

    }
}
