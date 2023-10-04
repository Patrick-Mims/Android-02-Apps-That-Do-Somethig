package edu.sfsu.app.Task;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import edu.sfsu.app.model.DrinkModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DrinkThread extends Thread {
    private static final OkHttpClient client = new OkHttpClient();
    private String api;
    private String result = null;
    final ObjectMapper mapper = new ObjectMapper();

    public DrinkThread(String api) {
        this.api = api;
    }
    public void run() {
        Request request = new Request.Builder().url(api).build();

        try {
            Log.v("LOG", "try MEOW");
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()) {
//                        assert response.body() != null;
                result = response.body().string();

                Log.v("LOG", "=> " + result);

                DrinkModel drinkModel = mapper.readValue(result, DrinkModel.class);

                Log.v("LOG", "drinkModel => ");

                response.body().close();
            } else {
                Log.v("LOG", "SOMETHING ISN'T RIGHT");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}