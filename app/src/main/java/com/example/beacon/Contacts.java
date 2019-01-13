package com.example.beacon;

import android.content.Context;
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

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FileInputStream;

import android.content.Intent;


public class Contacts extends AppCompatActivity {

    private CustomAdapter arrayAdapter;
    private String file_name;
    private String to_write;
    private FileOutputStream outputStream;
    private String file;
    private FileInputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        SearchView searchView = (SearchView) findViewById(R.id.search);
        final ListView listView = (ListView) findViewById(R.id.listview);
        final ArrayList<Model> items = new ArrayList<>();
        file_name = getIntent().getStringExtra("type") + ".txt";
        file = "";
        try {
            inputStream = openFileInput(file_name);

            int content;
            while ((content = inputStream.read()) != -1) {
                file = file + ((char) content);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] read = file.split("\n");

        Button button = (Button) findViewById(R.id.button2);
        Model add;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones != null && phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            add = new Model(name + "\n" + phoneNumber);
            if(Arrays.asList(read).contains(name+":"+phoneNumber)){
                add.setSelected(true);
            }
            items.add(add);
        }
        Collections.sort(items, new Comparator<Model>() {
            public int compare(Model m1, Model m2) {
                return m1.getAnimal().compareTo(m2.getAnimal());
            }
        });
        phones.close();
        setContacts(listView, items);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_write = "";
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getSelected()) {
                        to_write = to_write + items.get(i).getAnimal().replace("\n", ":") + "\n";
                    }
                }
                try {
                    outputStream = openFileOutput(file_name, Context.MODE_PRIVATE);
                    outputStream.write(to_write.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Contacts.this, MainActivity.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Model> search = new ArrayList<>();
                for (Model i : items) {
                    if (i.getAnimal().substring(0, newText.length()).toUpperCase().equals(newText.toUpperCase())) {
                        search.add(new Model(i.getAnimal()));
                    }
                }
                setContacts(listView, search);
                return false;
            }
        });

    }

    protected void setContacts(ListView listView, ArrayList<Model> flags) {

        arrayAdapter = new CustomAdapter(getApplicationContext(), flags);
        listView.setAdapter(arrayAdapter);
    }
}
