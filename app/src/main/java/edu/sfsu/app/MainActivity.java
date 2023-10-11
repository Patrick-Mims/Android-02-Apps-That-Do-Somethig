package edu.sfsu.app;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;

import java.util.ArrayList;
import java.util.List;

import edu.sfsu.app.Task.DrinkThread;
import edu.sfsu.app.callback.CallBack;
import edu.sfsu.app.helper.Complex;
import edu.sfsu.app.model.DrinkModel;

public class MainActivity extends FragmentActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<DrinkModel> model;
    private final BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LOG", "onCreate(Bundle savedInstanceState) {} ");

        // Lazily initialize the CastContext in the Activity's onCreate method.
        //        CastContext castContext = CastContext.getSharedInstance(this);

        /*
        MediaRouteButton mMediaRouteButton = findViewById(R.id.media_route_button);
        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), mMediaRouteButton);

        CastContext castContext = CastContext.getSharedInstance(MainActivity.this);
        IntroductoryOverlay overlay = IntroductoryOverlay.Builder(MainActivity.this, mMediaRouteMenuItem)
                .setTitleText()
                .setOnDismissed()
                .setSingleTime()
                .build();
                overlay.show();
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.cast, menu);

        CastButtonFactory.setUpMediaRouteButton(MainActivity.this, menu, R.id.media_route_menu_item);

        return true;
    }

    public void onSendMessage(View view) {
        Spinner color = findViewById(R.id.beer_color);

        int position = color.getSelectedItemPosition();

        Log.v("LOG", "position => " + position);

        String beerColor = String.valueOf(color.getSelectedItem());

        ArrayList<String> data = new ArrayList<>();

        data.add("Amazon");
        data.add("Arm");
        data.add("Google");
        data.add("Intel");
        data.add("Microsoft");

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("message", beerColor);
        intent.putExtra("DATA", data);
        intent.putExtra("POSITION", position);

        startActivity(intent);
    }

    public void onClickFindBeer(View view) throws InterruptedException {
        // R is a special Java file that gets generated by
        // Android Studio whenever you create or build your app.
        Spinner color = findViewById(R.id.beer_color);

        String beerColor = String.valueOf(color.getSelectedItem());

        TextView tv_message = findViewById(R.id.tv_main);
        tv_message.setText(String.valueOf(color.getSelectedItem()));

        // new
        List<String> brandList = beerExpert.getBrands(beerColor);

        StringBuilder brandsFormatted = new StringBuilder();

        for(String brand: brandList) {
            brandsFormatted.append(brand).append('\n');
        }

        tv_message.setText(brandsFormatted);
        model = new ArrayList<>();

        // DataTask
        recyclerView = findViewById(R.id.recyclerView);

        Complex complex = new Complex();
        CallBack callBack = new CallBack(complex);

        DrinkThread drinkThread = new DrinkThread("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita", model);

        drinkThread.start();

        callBack.sampleFunc2("Winner");
    }
/*
    @NonNull
    @Override
    public CastOptions getCastOptions(@NonNull Context context) {
        CastOptions castOptions = new CastOptions.Builder()
                .setReceiverApplicationId(context.getString(R.string.app_name))
                .build();
        return castOptions;
    }

    @Nullable
    @Override
    public List<SessionProvider> getAdditionalSessionProviders(@NonNull Context context) {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.cast, menu);
        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), menu, R.id.media_route_menu_item);

        return true;
    }
 */
}