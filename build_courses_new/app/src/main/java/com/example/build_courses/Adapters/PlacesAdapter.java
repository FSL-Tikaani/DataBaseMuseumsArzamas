package com.example.build_courses.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.build_courses.Models.PlacesModel;
import com.example.build_courses.R;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private PlacesAdapter.PlacesOnClickListener placesOnClickListener;
    private ArrayList<PlacesModel> dataSet;

    public PlacesAdapter(ArrayList<PlacesModel> objects) {
        this.dataSet= objects;
    }

    public void setDataSetPlaces(ArrayList<PlacesModel> dataSet){
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }


    public void setPlacesOnClickListener(PlacesOnClickListener placesOnClickListener) {
        this.placesOnClickListener = placesOnClickListener;
    }

    public interface PlacesOnClickListener{
        void PlacesOnExtraClick(View view, String museumID);
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_name_places;
        private final TextView tv_address_places;
        private final TextView tv_address_more;
        private final ImageView img_cardView;

        public TextView getTv_name_places() {
            return tv_name_places;
        }

        public TextView getTv_address_places() {
            return tv_address_places;
        }

        public TextView getTv_address_more() {
            return tv_address_more;
        }

        public ImageView getImg_cardView(){ return img_cardView; }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_places = itemView.findViewById(R.id.excursion_name);
            tv_address_places = itemView.findViewById(R.id.excursion_them);
            tv_address_more = itemView.findViewById(R.id.excursion_more);
            img_cardView = itemView.findViewById(R.id.places_img);

            tv_address_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String museumID = dataSet.get(getAdapterPosition()).getId();
                    placesOnClickListener.PlacesOnExtraClick(v, museumID);
                }
            });


        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.places_info_row, viewGroup, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlacesModel placesModel = dataSet.get(position);

        holder.getTv_name_places().setText(placesModel.getName_places());
        holder.getTv_address_places().setText(placesModel.getAddress_places());

        //подгружаем фотки для cardView
        Glide.with(holder.getImg_cardView().getContext())
                .load(placesModel.getUrl_img_cardView_places())
                .into(holder.getImg_cardView());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
