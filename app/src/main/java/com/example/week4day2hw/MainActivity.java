package com.example.week4day2hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvRecyclerView = findViewById(R.id.rvRecyclerView);

        RetrofitFlickr retrofitFlickr = new RetrofitFlickr();
        retrofitFlickr.getService().getFlickrResponse("https://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1").enqueue(new Callback<FlickerResponse>() {
            @Override
            public void onResponse(Call<FlickerResponse> call, Response<FlickerResponse> response) {
                FlickerResponse flickerResponse = response.body();
                populateFlickrRecyclerView(flickerResponse);

                Log.d("TAG", flickerResponse.getItems().get(1).getTitle());
            }

            @Override
            public void onFailure(Call<FlickerResponse> call, Throwable t) {
                Log.d("TAG", t.getMessage() + "ettttffffff");
            }
        });
    }
    private void populateFlickrRecyclerView(FlickerResponse flickerResponse){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        FlickrRecyclerViewAdapter adapter = new FlickrRecyclerViewAdapter(flickerResponse.getItems());
        rvRecyclerView.setLayoutManager(layoutManager);
        rvRecyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new FlickrRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onLongItemClick(String mediaLink) {
                Log.d("TAG", "onLongItemClick: ");
            }
        });



    }




}
