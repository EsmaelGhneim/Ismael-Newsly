package com.example.ismael.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismael.database.DBHelperNews;
import com.example.ismael.item.Item;
import com.example.ismael.adapters.ItemAdapter;
import com.example.ismael.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteItemActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button deleteButton;

    private List<Item> itemList;
    private List<Item> filteredList;
    private String query;

    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        searchEditText = findViewById(R.id.searchEditText);
        deleteButton = findViewById(R.id.deleteButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Retrieve all items from DBHelperNews
        DBHelperNews dbHelper = new DBHelperNews(this);
        itemList = dbHelper.getAllItems();
        dbHelper.close();

        if(itemList!=null && !itemList.isEmpty()) {

            filteredList = new ArrayList<>(itemList);

            System.out.println(itemList.get(0).getImagePath());

            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    query = s.toString();
                    filterItems(query);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteVisibleItems();
                }
            });

            // Set up RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ItemAdapter(filteredList);
            recyclerView.setAdapter(adapter);
        }
    }

    private void filterItems(String query) {
        //Filters the list of items based on the search query
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(itemList);
            //If the query is empty, add all items to the filtered list.
        } else {
            for (Item item : itemList) {
                if (item.getTitle().toLowerCase().contains(query.toLowerCase()) ) {
                    filteredList.add(item);
                }
            }
            //Otherwise, loop through all items and add those whose title contains the query.
        }

        // Notify the adapter of the data changes so it can update the RecyclerView.
        adapter.notifyDataSetChanged();

    }

    private void deleteVisibleItems() {
        //Deletes the items that are currently visible in the filtered list.
        Iterator<Item> iterator = filteredList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                iterator.remove();
                DBHelperNews dbHelper = new DBHelperNews(this);
                dbHelper.deleteItem(item.getId());
                dbHelper.close();
            }
        }

        Toast.makeText(this, "Visible items deleted", Toast.LENGTH_SHORT).show();
        // Update the RecyclerView or refresh the data
        filterItems(query);
        finish();
        //a toast message is shown to the user indicating that the visible items have been deleted.
        //The comment suggests that you should update the RecyclerView or refresh the data to reflect the changes.
    }
}
 // extra explanation:
//An Iterator in Java is an object that allows you to traverse through a collection (like an ArrayList, HashSet, etc.) one element at a time.
// It provides methods to check if there are more elements (hasNext()),
// retrieve the next element (next()), and remove elements from the collection (remove()).