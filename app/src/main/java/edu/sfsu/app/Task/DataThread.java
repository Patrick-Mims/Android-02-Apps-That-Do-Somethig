package edu.sfsu.app.Task;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataThread extends Thread {
    String api;
    private static final OkHttpClient client = new OkHttpClient();
    public DataThread(String api) {
        this.api = api;
        Log.v("LOG", "DataThread()");
        /* The call to setDaemon(), with the argument true, makes the thread that is created a daemon thread.
           A Daemon thread is simple a background thread that is subordinate to the thread that created it. */
        setDaemon(true);
    }
    // Synchronous Request
    public void getData(String api) throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            // The run() method contains the code for thread execution
            @Override
            public void run() {
                Log.v("LOG", "run()");

                Request request = new Request.Builder().url(api).build();

                try {
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        assert response.body() != null;
                        Log.v("LOG", "Response => " + response.body().string());
                        response.body().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }
}