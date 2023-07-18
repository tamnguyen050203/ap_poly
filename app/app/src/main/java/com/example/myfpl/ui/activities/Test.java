package com.example.myfpl.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myfpl.adapters.TestAdapter;
import com.example.myfpl.databinding.ActivityTestBinding;
import com.example.myfpl.helpers.FirebaseHelper;
import com.example.myfpl.util.StringUtil;
import com.example.myfpl.util.ToastApp;
import com.example.myfpl.viewmodels.TestViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class Test extends AppCompatActivity {
    private static final String TAG = Test.class.getSimpleName();
    private ActivityTestBinding binding;
    private TestViewModel testViewModel;
    private GoogleSignInClient client;

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
        client = FirebaseHelper.getSignClient(Test.this);

        binding.buttonLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(client.getSignInIntent(), FirebaseHelper.GoogleSignRequestCode);
            }
        });

        binding.buttonSignoutGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.signOut();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FirebaseHelper.GoogleSignRequestCode){
            Task<GoogleSignInAccount> result = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = result.getResult(ApiException.class);

                Log.d(TAG, "onActivityResult: " + result.getResult().getEmail());
                String email = result.getResult().getEmail();

                if(StringUtil.isFPLDomain(email)){
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        ToastApp.show(Test.this, "Đăng nhập thành công với tài khoản " + Objects.requireNonNull(task.getResult().getUser()).getDisplayName());
                                    }else{
                                        ToastApp.show(Test.this, "Đăng nhập thất bại");
                                    }
                                }
                            });
                }else{
                    ToastApp.show(Test.this, "Bạn phải dùng mail FPT để Login");
                    client.signOut();
                }
            }catch (Exception e){
                Log.e(TAG, "onActivityResult: " + e.getMessage());
                ToastApp.show(Test.this, "Vui lòng kiểm tra lại kết nối mạng!");
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