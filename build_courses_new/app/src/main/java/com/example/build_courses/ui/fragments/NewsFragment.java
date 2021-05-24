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

import com.example.build_courses.Activity.NewsInfoActivity;
import com.example.build_courses.Adapters.NewsAdapter;
import com.example.build_courses.Adapters.PlacesAdapter;
import com.example.build_courses.Models.NewsModel;
import com.example.build_courses.Models.PlacesModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    ProgressDialog progressDialog;
    RecyclerView newsRecyclerView;
    NewsAdapter newsAdapter;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);


        init(root);
        initializeAdapter();

        return root;
    }

    private void initializeAdapter() {
        //показываем progressDialog
        progressDialog.setMessage("Загрузка...");
        progressDialog.show();

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecyclerView.setAdapter(newsAdapter);

        ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();

        //получаем данные с бд
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);

                    if (newsModel != null){
                        newsModel.setId(dataSnapshot.getKey());
                        newsModelArrayList.add(newsModel);
                        newsAdapter.setDataSetNews(newsModelArrayList);
                        newsAdapter.setNewsOnClickListener(new NewsAdapter.NewsOnClickListener() {
                            @Override
                            public void NewsOnExtraClick(View view, String newsID) {
                                Intent intent = new Intent(getContext(), NewsInfoActivity.class);
                                intent.putExtra("newsID", newsID);
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

    private void init(View root) {
        progressDialog = new ProgressDialog(getContext());
        newsRecyclerView = root.findViewById(R.id.recycler_view_news);
        newsAdapter = new NewsAdapter(new ArrayList<>());
        databaseReference = FirebaseDatabase.getInstance().getReference("news");
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


}