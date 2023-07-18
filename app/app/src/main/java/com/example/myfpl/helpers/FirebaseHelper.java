package com.example.myfpl.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.animation.CycleInterpolator;

import com.example.myfpl.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseHelper {
    public static final int GoogleSignRequestCode = 12121;

    public static void logout(){
        FirebaseAuth.getInstance().signOut();
    }

    public static GoogleSignInOptions getGoogleSignInOptions(Context context){
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
    }

    public static GoogleSignInClient getSignClient(Context context){
        return GoogleSignIn.getClient(context, getGoogleSignInOptions(context));
    }

    public static Intent getSignIntent(Context context){
        return getSignIntent(context);
    }

    public interface FirebaseHandler{
        void onComplete(FirebaseUser user);
        void onError(Exception e);
    }
}
