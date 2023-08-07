package com.example.myfpl.ui.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfpl.R;
import com.example.myfpl.adapters.ListItemDialogAdapter;
import com.example.myfpl.data.DTO.LoginDTO;
import com.example.myfpl.data.apis.AuthService;
import com.example.myfpl.databinding.ActivityLoginBinding;
import com.example.myfpl.helpers.FirebaseHelper;
import com.example.myfpl.helpers.retrofit.RetrofitHelper;
import com.example.myfpl.helpers.retrofit.TokenRepository;
import com.example.myfpl.models.DialogItemModel;
import com.example.myfpl.util.StringUtil;
import com.example.myfpl.util.ToastApp;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private GoogleSignInClient googleSignInClient;
    // login google with Google API
    private com.google.api.services.calendar.Calendar calendarService;
    private Dialog optionDialog;
    private DialogItemModel currentOption;
    private ListItemDialogAdapter adapter;
    private ArrayList<DialogItemModel> listItem = DialogItemModel.getExModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    public void init() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestIdToken(getString(R.string.web_client_id))
                .requestServerAuthCode(getString(R.string.web_client_id))
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        //setup adapter for list item
        adapter = new ListItemDialogAdapter(listItem, new ListItemDialogAdapter.HandleEvent() {
            @Override
            public void onItemClick(DialogItemModel dialogItemModel, int position) {
                adapter.setChooseOption(position);
                currentOption = dialogItemModel;
            }
        }, -1, LoginActivity.this);

        addEvent();
    }

    public void addEvent() {
        binding.buttonLogin.setOnClickListener(LoginActivity.this);
        binding.chooseOption.setOnClickListener(LoginActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 555) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                try {
                    GoogleSignInAccount account = result.getSignInAccount();

                    String email = account.getEmail();

                    if (StringUtil.isFPLDomain(email)) {
                        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                        FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            ToastApp.show(LoginActivity.this, "Đăng nhập thành công với tài khoản" + Objects.requireNonNull(task.getResult().getUser()).getDisplayName());
                                            String email = Objects.requireNonNull(task.getResult().getUser()).getEmail();
                                            String username = task.getResult().getUser().getDisplayName();
                                            String providerId = task.getResult().getUser().getProviderId();
                                            String avatar = task.getResult().getUser().getPhotoUrl().toString();
                                            login(email, username, providerId, avatar);
                                        } else {
                                            ToastApp.show(LoginActivity.this, "Đăng nhập thất bại");
                                        }
                                    }
                                });
                    } else {
                        ToastApp.show(LoginActivity.this, "Bạn phải dùng mail FPT để Login");
                        googleSignInClient.signOut();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onActivityResult: " + e.getMessage());
                }
            }
        }
    }

    public void login(String email, String username, String providerId, String avatar) {
        LoginDTO.LoginRequestDTO loginRequestDTO = new LoginDTO.LoginRequestDTO(email, username, providerId, avatar);
        RetrofitHelper.createService(AuthService.class, getApplication().getApplicationContext()).login(loginRequestDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginDTO.LoginResponseDTO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(LoginDTO.@io.reactivex.rxjava3.annotations.NonNull LoginResponseDTO loginResponseDTO) {
                        TokenRepository.getInstance(getApplication().getApplicationContext()).setToken(loginResponseDTO.getAccessToken());
                        TokenRepository.getInstance(getApplication().getApplicationContext()).setRefreshToken(loginResponseDTO.getRefreshToken());
                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                        Log.d(TAG, "onSuccess: " + loginResponseDTO);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    public void handleButtonOption() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this);

        View dialogView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_option_layout, null);
        builder.setView(dialogView);

        MaterialButton buttonConfirm = dialogView.findViewById(R.id.button_confirm);
        RecyclerView listOption = dialogView.findViewById(R.id.list_item);
        listOption.setAdapter(adapter);

        //handle dialog
        optionDialog = builder.create();
        optionDialog.show();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionDialog.dismiss();
                if (currentOption != null) {
                    binding.chooseOption.setText(currentOption.getTitle());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 555);
                break;
            case R.id.choose_option:
                handleButtonOption();
                break;
        }
    }
}