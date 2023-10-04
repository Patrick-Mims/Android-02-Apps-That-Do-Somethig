package edu.sfsu.app.Task;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import edu.sfsu.app.model.DrinkModel;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataThread extends Thread {
    String api;
    private static final OkHttpClient client = new OkHttpClient();
    public DataThread(String api, ArrayList<DrinkModel> drinkModel) {
        this.api = api;
        Log.v("LOG", "DataThread()");
        setDaemon(true);
        // The call to setDaemon(), with the argument true, makes the thread that is created a daemon thread.
        // A Daemon thread is simple a background thread that is subordinate to the thread that created it.
    }
    // Synchronous Request
    public void getData(String api) throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            final ObjectMapper mapper = new ObjectMapper();
            String result = null;
            // The run() method contains the code for thread execution
            @Override
            public void run() {
                Log.v("LOG", "run()");

                Request request = new Request.Builder().url(api).build();

                DrinkModel drinkModel;
                try {
                    Response response = client.newCall(request).execute();

                    if(response.isSuccessful()) {
//                        assert response.body() != null;
                        result = response.body().string();

                        Log.v("LOG", "=> " + result);

                        drinkModel = mapper.readValue(result, DrinkModel.class);

                        Log.v("LOG", "drinkModel => ");

                        response.body().close();
                    } else {
                        Log.v("LOG", "SOMETHING ISN'T RIGHT");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
        thread.start();
        thread.join();
        */
    }
}