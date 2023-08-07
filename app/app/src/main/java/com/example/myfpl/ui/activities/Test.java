package com.example.myfpl.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.adapters.TestAdapter;
import com.example.myfpl.databinding.ActivityTestBinding;
import com.example.myfpl.helpers.FirebaseHelper;
import com.example.myfpl.util.StringUtil;
import com.example.myfpl.util.ToastApp;
import com.example.myfpl.viewmodels.TestViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import pub.devrel.easypermissions.EasyPermissions;

public class Test extends AppCompatActivity {
    private static final String TAG = Test.class.getSimpleName();
    private ActivityTestBinding binding;
    private TestViewModel testViewModel;

    // login google with Google API
    private GoogleSignInClient mGoogleSignInClient;
    private com.google.api.services.calendar.Calendar calendarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init() {
        testViewModel = new TestViewModel(getApplication(), "ss");
        binding.setMainViewModel(testViewModel);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestIdToken(getString(R.string.web_client_id))
                .requestServerAuthCode(getString(R.string.web_client_id))
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.buttonLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult(client.getSignInIntent(), FirebaseHelper.GoogleSignRequestCode);
                signWithGoogle();
            }
        });

        binding.buttonSignoutGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleSignInClient.signOut();
            }
        });
    }

    public void signWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 555);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 555) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult:GET_AUTH_CODE:success:" + result.getStatus().isSuccess());
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                if (StringUtil.isFPLDomain(Objects.requireNonNull(acct.getEmail()))) {

                    HttpTransport transport = AndroidHttp.newCompatibleTransport();
                    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
                    GoogleAccountCredential googleAccountCredential = GoogleAccountCredential.usingOAuth2(
                                    Test.this,
                                    Collections.singletonList(CalendarScopes.CALENDAR)
                            ).setBackOff(new ExponentialBackOff())
                            .setSelectedAccountName(Objects.requireNonNull(acct.getAccount()).name);

                    calendarService = new com.google.api.services.calendar.Calendar.Builder(
                            transport, jsonFactory, googleAccountCredential)
                            .setApplicationName("MyFPL")
                            .build();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Calendar.CalendarList feed = calendarService.calendarList();
                                Log.d(TAG, "run: " + feed.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    ToastApp.show(Test.this, "Vui lòng đăng nhập bằng mail FPT`");
                }
            } else {
                ToastApp.show(Test.this, "Kiểm tra lại kết nối mạng của bạn");
            }
        }
    }

    @BindingAdapter({"list_data", "is_success"})
    public static void loadData(RecyclerView view, ObservableArrayList<com.example.myfpl.models.Test> list, ObservableBoolean isSuccess) {
        if (isSuccess.get()) {
            ToastApp.show(view.getContext(), "Success");
        } else {
            ToastApp.show(view.getContext(), "Failure");
        }

        TestAdapter testAdapter = new TestAdapter(list);
        view.setAdapter(testAdapter);
    }
}