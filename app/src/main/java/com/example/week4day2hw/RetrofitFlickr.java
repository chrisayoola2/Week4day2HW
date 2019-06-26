package com.example.week4day2hw;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class RetrofitFlickr {
    public static final String BASE_URL = "https://api.flickr.com/";
    public static final String PATH = "services/";
    public static final String QUERY_ITEM = "items";
    public static final String QUERY_TITLE = "title";
    public static final String QUERY_DATE = "dateTaken";
    public static final String QUERY_ID = "authorId";


    public Retrofit getRetrofitInstance(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
    }



    public FlickrApiService getService(){
        return getRetrofitInstance().create(FlickrApiService.class);
    }

    public interface FlickrApiService{
        @GET
        Call<FlickerResponse> getFlickrResponse(@Url String myUrl);




    }

}