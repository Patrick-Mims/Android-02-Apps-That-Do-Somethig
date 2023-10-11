package edu.sfsu.app.cast;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;

import java.util.List;

import edu.sfsu.app.R;

public class CastOptionsProvider implements OptionsProvider {
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
}

/*
* Project Comments
* Initialize the Cast Context
* The framework has a global singleton object, the CastContext, that coordinates all the frameworks interactions.
*
* (Cast Context is initialized when the CastContext.getSharedInstance(this) is called).
*
*  # MainActivity
*  public void onCreate(Bundle savedInstanceState) {
*       CastContext castContext = CastContext.getSharedInstance(this);
*  }
* */