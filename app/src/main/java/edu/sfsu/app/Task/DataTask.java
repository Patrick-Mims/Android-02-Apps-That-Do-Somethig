package edu.sfsu.app.Task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import edu.sfsu.app.model.DrinkModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataTask extends AsyncTask<String, Integer, String> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    @SuppressLint("StaticFieldLeak")
    RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    ProgressBar progressBar;
    ArrayList<DrinkModel> model;
    private final OkHttpClient client = new OkHttpClient();
    public DataTask(Context context, RecyclerView recyclerView, ProgressBar progressBar, ArrayList<DrinkModel> model) {
        Log.v("LOG", "DataTask");
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.model = model;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("LOG", "onPreExecute");
    }

    @Override
    protected String doInBackground(String... params) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v("LOG", "RUN()");
                Response body = null;
                Request request = new Request.Builder().url(params[0]).build();
                try {
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        Log.v("LOG", "Response => " + response.body().string());
                        response.body().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        return null;
    }
}