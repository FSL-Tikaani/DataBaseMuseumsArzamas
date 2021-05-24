package com.example.build_courses.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.build_courses.Models.ExcursionModel;
import com.example.build_courses.R;

import java.util.ArrayList;

public class ExcursionsAdapter extends RecyclerView.Adapter<ExcursionsAdapter.ViewHolder> {

    private ArrayList<ExcursionModel> dataSet;
    private ExcursionOnClickListener excursionOnClickListener;


    //onClick
    public interface ExcursionOnClickListener{
        void ExcursionOnExtrasClick(View view, String excursionID);
    }

    public void setExcursionOnClickListener(ExcursionOnClickListener excursionOnClickListener){
        this.excursionOnClickListener = excursionOnClickListener;
    }



    public void setDataSetExcursion(ArrayList<ExcursionModel> newDataSet) {
        this.dataSet = newDataSet;
        notifyDataSetChanged();
    }

    public ExcursionsAdapter(ArrayList<ExcursionModel> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img_background_cardView;
        private final TextView tv_name_excursion;
        private final TextView tv_theme_excursion;
        private final TextView tv_time_excursion;
        private final TextView tv_more_excursion;

        public ImageView getImg_background_cardView() {
            return img_background_cardView;
        }

        public TextView getTv_name_excursion() {
            return tv_name_excursion;
        }

        public TextView getTv_theme_excursion() {
            return tv_theme_excursion;
        }

        public TextView getTv_time_excursion() {
            return tv_time_excursion;
        }

        public TextView getTv_more_excursion(){return  tv_more_excursion;}

        public ViewHolder(View itemView) {
            super(itemView);
            img_background_cardView = itemView.findViewById(R.id.places_img);
            tv_name_excursion = itemView.findViewById(R.id.excursion_name);
            tv_theme_excursion = itemView.findViewById(R.id.excursion_them);
            tv_time_excursion = itemView.findViewById(R.id.excursion_time);
            tv_more_excursion = itemView.findViewById(R.id.excursion_more);


            tv_more_excursion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String excursionID = dataSet.get(getAdapterPosition()).getId();
                    excursionOnClickListener.ExcursionOnExtrasClick(v, excursionID);
                }
            });
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.excursions_info_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExcursionModel model = dataSet.get(position);

        holder.getTv_name_excursion().setText(model.getName());
        holder.getTv_theme_excursion().setText(model.getThem());
        holder.getTv_time_excursion().setText(model.getTime());

        Glide.with(holder.getImg_background_cardView().getContext())
                .load(model.getUrl_img_cardView())
                .into(holder.getImg_background_cardView());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
