package com.example.week4day2hw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.ViewHolder> {
    List<Item> listOfItems;

    private OnItemLongClickListener listener; //Second Intialize


    public FlickrRecyclerViewAdapter(List<Item> items){
        this.listOfItems = items;
    }

    @NonNull
    @Override
    public FlickrRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flickr_list_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrRecyclerViewAdapter.ViewHolder holder, int position) {
        final Item listItems = listOfItems.get(position);
        final String title = listItems.getTitle();
        final String date = listItems.getDateTaken();
        final String authorId = listItems.getAuthorId();
        final String imageUrl = listItems.getMedia().getM();
        holder.tvTitle.setText("Title:  " + title);
        holder.tvAuthorId.setText("ID:  " + authorId);
        holder.tvDateTaken.setText("Taken Date:  " + date);
        Glide.with(holder.imgFlickrImage).load(imageUrl).into(holder.imgFlickrImage);


    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFlickrImage;
        TextView tvTitle;
        TextView tvAuthorId;
        TextView tvDateTaken;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFlickrImage = itemView.findViewById(R.id.imgFlickrImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthorId = itemView.findViewById(R.id.tvAuthorId);
            tvDateTaken = itemView.findViewById(R.id.tvDateTaken);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onLongItemClick(listOfItems.get(position).getMedia().getM());
                    }

                    return true;
                }
            });
        }




    }
    public interface OnItemLongClickListener { //First set On Itemclick interface
        void onLongItemClick(String mediaLink);
    }

    public  void setOnItemLongClickListener(OnItemLongClickListener listener){ //Third Set constructor
        this.listener = listener;
    }

}
