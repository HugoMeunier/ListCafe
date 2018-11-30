package com.example.meuni.cafeeuro.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meuni.cafeeuro.CafeAdapter;
import com.example.meuni.cafeeuro.R;
import com.example.meuni.cafeeuro.models.Cafe;

import java.util.ArrayList;


public class ListFragment extends android.support.v4.app.Fragment {

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

        //get a linearlayout and assign it to the recyclerview
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



