package com.example.week4day2hw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.ViewHolder> {
    List<Item> listOfItems;



    public FlickrRecyclerViewAdapter(List<Item> items) {
        this.listOfItems = items;
    }

    @NonNull
    @Override
    public FlickrRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flickr_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrRecyclerViewAdapter.ViewHolder holder, int position) {

        final Item listItems = listOfItems.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { //
            @Override
            public boolean onLongClick(View view) { //creates first dialog box
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("Select Size");
                alertDialog.setPositiveButton("Show Full Image", new DialogInterface.OnClickListener() {  //Sets what happens when you click Large image
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(view.getContext(), ImageActivity.class);
                        intent.putExtra("imageurl", listItems.getMedia().getM());
                        view.getContext().startActivity(intent);
                        Log.d("TAG", "onClick: ");
                    }
                });

                alertDialog.setNegativeButton("Show Small Image", new DialogInterface.OnClickListener() { // SETS WHAT HAPPENS WHEN YOU CLICK SHOW SMALL IMAGE
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Dialog builder = new Dialog(view.getContext());
                        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        builder.getWindow().setBackgroundDrawable(
                                new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                //nothing;
                            }
                        });

                        ImageView imageView = new ImageView(view.getContext());
                        String imageUrl = listItems.getMedia().getM();
                        Glide.with(imageView).load(imageUrl).into(imageView);
                        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        builder.show();
                        Log.d("TAG", "Cancel onClick: ");

                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;
            }
        });
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

        }


    }
}
