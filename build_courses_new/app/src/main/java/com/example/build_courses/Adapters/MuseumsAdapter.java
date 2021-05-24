package com.example.build_courses.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MuseumsAdapter extends RecyclerView.Adapter<MuseumsAdapter.ViewHolder> {

    private ArrayList<MuseumModel> dataSet = new ArrayList<>();
    private MuseumsOnClickListener museumsOnClickListener;

    public void setDataSet(ArrayList<MuseumModel> newDataSet) {
        this.dataSet = newDataSet;
        notifyDataSetChanged();
    }



    public void setMuseumsOnClickListener(MuseumsOnClickListener museumsOnClickListener) {
        this.museumsOnClickListener = museumsOnClickListener;
    }

    public interface MuseumsOnClickListener{
        void MuseumOnAddressClick(View view, String museumID);
        void MuseumsOnExtrasClick(View view, String museumID);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView museum_picture;
        private final TextView museum_name;
        private final TextView museum_address;
        //private final TextView museum_description;
        private final TextView museum_contacts;
        private final TextView museum_work_time;
        private final TextView museum_more;

        public ViewHolder(View view) {
            super(view);
            museum_picture = view.findViewById(R.id.news_img);
            museum_name = view.findViewById(R.id.news_name);
            museum_address = view.findViewById(R.id.museum_address);
            museum_contacts = view.findViewById(R.id.museum_contacts);
            museum_work_time = view.findViewById(R.id.tv_work_time);
            museum_more = view.findViewById(R.id.news_more);

            museum_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String museumID = dataSet.get(getAdapterPosition()).getId();
                    museumsOnClickListener.MuseumOnAddressClick(v, museumID);
                }
            });

            museum_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String museumID = dataSet.get(getAdapterPosition()).getId();
                    museumsOnClickListener.MuseumsOnExtrasClick(v, museumID);
                }
            });
        }

        public ImageView getMuseum_picture() {
            return museum_picture;
        }

        public TextView getMuseum_name() {
            return museum_name;
        }

        public TextView getMuseum_address() {
            return museum_address;
        }

        public TextView getMuseum_contacts() {
            return museum_contacts;
        }

        public TextView getMuseum_work_time() {
            return museum_work_time;
        }

        public TextView getMuseum_more() {
            return museum_more;
        }
    }


    public MuseumsAdapter(ArrayList<MuseumModel> dataSet) {
        this.dataSet = dataSet;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.museum_info_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        MuseumModel museum = dataSet.get(position);

        viewHolder.getMuseum_name().setText(museum.getName());
        viewHolder.getMuseum_address().setText(museum.getAddress());
        viewHolder.getMuseum_contacts().setText(museum.getContacts());
        viewHolder.getMuseum_work_time().setText(museum.getWork_time());

        //подгружаем фотки для cardView
        Glide.with(viewHolder.getMuseum_picture().getContext())
                .load(museum.getBackgroundImageURL())
                .into(viewHolder.getMuseum_picture());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}