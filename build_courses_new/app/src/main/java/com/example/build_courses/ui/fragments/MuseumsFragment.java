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

import com.example.build_courses.Activity.AddressActivity;
import com.example.build_courses.Activity.ContactsActivity;
import com.example.build_courses.Activity.MuseumInfoActivity;
import com.example.build_courses.Adapters.MuseumsAdapter;
import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MuseumsFragment extends Fragment {

    RecyclerView museumsRecyclerView;
    MuseumsAdapter museumsAdapter;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_museums, container, false);

        progressDialog = new ProgressDialog(getContext());

        museumsRecyclerView = root.findViewById(R.id.museums_recycler_view);
        initializeAdapter();
        initializeRecyclerView();
        //setDataFromDataBase();
        return root;
    }


    //заполяем данными список
    private void initializeAdapter() {
        progressDialog.setMessage("Загрузка...");
        progressDialog.show();

        ArrayList<MuseumModel> museums = new ArrayList<>();
        museumsAdapter = new MuseumsAdapter(new ArrayList<>());
        databaseReference = FirebaseDatabase.getInstance().getReference("Museums");

        //записываем данные в массив из БД

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    MuseumModel DB = ds.getValue(MuseumModel.class);
                    if (DB != null) {
                        museums.add(DB);
                        museumsAdapter.setDataSet(museums);
                        museumsAdapter.setMuseumsOnClickListener(new MuseumsAdapter.MuseumsOnClickListener() {
                            @Override
                            public void MuseumOnAddressClick(View view, String museumID) {
                                Intent intent = new Intent(getContext(), AddressActivity.class);
                                intent.putExtra("museumID", museumID);
                                startActivity(intent);
                            }

                            @Override
                            public void MuseumsOnExtrasClick(View view, String museumID) {
                                Intent intent = new Intent(getContext(), MuseumInfoActivity.class);
                                intent.putExtra("museumID", museumID);
                                startActivity(intent);
                            }
                        });
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        hideProgress(progressDialog);
        databaseReference.addValueEventListener(vListener);


        /*museums.add(new MuseumModel("", "Название 1", "Адресс: адрес1", "описание1 описание1 описание 1описание описание описание описание описание описание описание описание ", "contacts1"));
        museums.add(new MuseumModel("", "Название 2", "Адресс: адрес2", "описаниепрыапроаыпоыа2 описание 2описание описание описание описание описание описание описание", "contacts2"));
        museums.add(new MuseumModel("", "Название 3", "Адресс: адрес3", "описание описание3 описание 3", "contacts3"));
        */

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
        museumsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        museumsRecyclerView.setAdapter(museumsAdapter);
    }
}