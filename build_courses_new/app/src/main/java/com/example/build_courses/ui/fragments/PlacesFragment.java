package com.example.build_courses.ui.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.build_courses.Activity.PlacesInfoActivity;
import com.example.build_courses.Adapters.MuseumsAdapter;
import com.example.build_courses.Adapters.PlacesAdapter;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.Models.PlacesModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {

    ProgressDialog progressDialog;
    RecyclerView placesRecyclerView;
    PlacesAdapter placesAdapter;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_places, container, false);

        progressDialog = new ProgressDialog(getContext());
        placesRecyclerView = root.findViewById(R.id.recycler_view_places);


        initializeAdapter();
        initializeRecyclerView();
        return root;

    }

    private void initializeAdapter(){
        progressDialog.setMessage("Загрузка...");
        progressDialog.show();

        ArrayList<PlacesModel> placesModelArrayList = new ArrayList<>();
        placesAdapter = new PlacesAdapter(new ArrayList<>());
        databaseReference = FirebaseDatabase.getInstance().getReference("Places");


        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PlacesModel placesModel = dataSnapshot.getValue(PlacesModel.class);

                    if(placesModel != null){
                        placesModelArrayList.add(placesModel);
                        placesAdapter.setDataSetPlaces(placesModelArrayList);
                        placesAdapter.setPlacesOnClickListener(new PlacesAdapter.PlacesOnClickListener() {
                            @Override
                            public void PlacesOnExtraClick(View view, String placesID) {
                                Intent intent = new Intent(getContext(), PlacesInfoActivity.class);
                                intent.putExtra("placesID", placesID);
                                startActivity(intent);
                            }

                        });

                    }
                }
                hideProgress(progressDialog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }

    public void hideProgress(ProgressDialog mProgressDialog) {
        if(mProgressDialog != null) {
            if(mProgressDialog.isShowing()) { //check if dialog is showing.

                //get the Context object that was used to great the dialog
                Context context = ((ContextWrapper)mProgressDialog.getContext()).getBaseContext();

                //if the Context used here was an activity AND it hasn't been finished or destroyed
                //then dismiss it
                if(context instanceof Activity) {
                    if(!((Activity)context).isFinishing() && !((Activity)context).isDestroyed())
                        mProgressDialog.dismiss();
                } else //if the Context used wasn't an Activity, then dismiss it too
                    mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }

    private void initializeRecyclerView() {
        placesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        placesRecyclerView.setAdapter(placesAdapter);
    }
}