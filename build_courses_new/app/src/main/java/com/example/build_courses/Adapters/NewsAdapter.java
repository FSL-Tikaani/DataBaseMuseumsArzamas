package com.example.build_courses.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.build_courses.Models.NewsModel;
import com.example.build_courses.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> dataSet = new ArrayList<>();
    private NewsAdapter.NewsOnClickListener newsOnClickListener;

    public NewsAdapter(ArrayList<NewsModel> news){
        this.dataSet = news;
    }

    public void setDataSetNews(ArrayList<NewsModel> newDataSet){
        this.dataSet = newDataSet;
        notifyDataSetChanged();
    }

    public interface NewsOnClickListener{
        void NewsOnExtraClick(View view, String newsID);
    }

    public void setNewsOnClickListener(NewsOnClickListener newsOnClickListener){
        this.newsOnClickListener = newsOnClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv_name;
        private final TextView tv_description;
        private final TextView tv_more;
        private final ImageView img_cardView_news;

        public ImageView getImg_cardView_news(){return  img_cardView_news;}
        public TextView getTv_name() {
            return tv_name;
        }
        public TextView getTv_description() {
            return tv_description;
        }
        public TextView getTv_more() {
            return tv_more;
        }



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_cardView_news = itemView.findViewById(R.id.news_img);
            tv_name = itemView.findViewById(R.id.news_name);
            tv_description = itemView.findViewById(R.id.tv_news_description);
            tv_more = itemView.findViewById(R.id.news_more);

            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newsID = dataSet.get(getAdapterPosition()).getId();
                    newsOnClickListener.NewsOnExtraClick(v, newsID);
                }
            });

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_info_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        NewsModel newsModel = dataSet.get(position);

        holder.getTv_name().setText(newsModel.getTitle());
        holder.getTv_description().setText(newsModel.getContent());

        Glide.with(holder.getImg_cardView_news().getContext())
                .load(newsModel.getImages().get(0))
                .into(holder.getImg_cardView_news());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
