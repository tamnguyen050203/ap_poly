package com.example.myfpl.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.myfpl.R;
import com.example.myfpl.adapters.ListItemDialogAdapter;
import com.example.myfpl.databinding.ActivityLoginBinding;
import com.example.myfpl.helpers.FirebaseHelper;
import com.example.myfpl.models.DialogItemModel;
import com.example.myfpl.util.StringUtil;
import com.example.myfpl.util.ToastApp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private GoogleSignInClient client;
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
        client = FirebaseHelper.getSignClient(LoginActivity.this);

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
        if (requestCode == FirebaseHelper.GoogleSignRequestCode) {
            Task<GoogleSignInAccount> result = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = result.getResult(ApiException.class);

                Log.d(TAG, "onActivityResult: " + result.getResult().getEmail());
                String email = result.getResult().getEmail();

                if (StringUtil.isFPLDomain(email)) {
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        ToastApp.show(LoginActivity.this, "Đăng nhập thành công với tài khoản" + Objects.requireNonNull(task.getResult().getUser()).getDisplayName());
                                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                                    } else {
                                        ToastApp.show(LoginActivity.this, "Đăng nhập thất bại");
                                    }
                                }
                            });
                } else {
                    ToastApp.show(LoginActivity.this, "Bạn phải dùng mail FPT để Login");
                    client.signOut();
                }
            } catch (Exception e) {
                Log.e(TAG, "onActivityResult: " + e.getMessage());
//                ToastApp.show(LoginActivity.this, "Vui lòng kiểm tra lại kết nối mạng!");
            }
        }
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
                startActivityForResult(client.getSignInIntent(), FirebaseHelper.GoogleSignRequestCode);
                break;
            case R.id.choose_option:
                handleButtonOption();
                break;
        }
    }
}