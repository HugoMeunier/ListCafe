package com.example.meuni.cafeeuro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meuni.cafeeuro.models.Cafe;

import java.util.ArrayList;


public class ListFragment extends  android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "param1";

    private ArrayList<Cafe> cafes = new ArrayList<>();

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cafes = (ArrayList<Cafe>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //get the recyclerview
        RecyclerView rcvBottles = view.findViewById(R.id.recyclerViewCafe);
        rcvBottles.setHasFixedSize(true);

        //get a linearlayout and assign it to the recyclerv
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvBottles.setLayoutManager(layoutManager);

        //get an adapter and assign it to the recycle view
        CafeAdapter cafeAdapter = new CafeAdapter(cafes);
        rcvBottles.setAdapter(cafeAdapter);


        return view;
    }

    public static ListFragment newInstance(ArrayList<Cafe> CafeToDisplay) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, CafeToDisplay);
        fragment.setArguments(args);
        return fragment;
    }

}



