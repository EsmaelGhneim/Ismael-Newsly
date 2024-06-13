package com.example.ismael.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismael.database.DBHelperNews;
import com.example.ismael.item.Item;
import com.example.ismael.adapters.ItemAdapter;
import com.example.ismael.R;

import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Item> itemList;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Retrieve the news items from DBHelperNews
        DBHelperNews dbHelper = new DBHelperNews(getContext());
        itemList = dbHelper.getAllItems();
        dbHelper.close();

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
//extra explanation
//This fragment is responsible for displaying a list of news items in a RecyclerView.
// It fetches the news items from the local database using the DBHelperNews class and
// populates the RecyclerView with them using an adapter(item adapter)