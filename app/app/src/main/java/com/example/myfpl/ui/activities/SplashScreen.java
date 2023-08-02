package com.example.myfpl.ui.activities;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.example.myfpl.databinding.ActivitySplashScreenBinding;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    @SuppressLint("DiscouragedApi")
    public void init() {

        //handle share element
        //fake fetch data
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // and adding animation between this two activities.
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                // below method is used to make scene transition
                // and adding fade animation in it.
                ActivityOptionsCompat options = makeSceneTransitionAnimation(
                        SplashScreen.this, binding.imageLogo, Objects.requireNonNull(ViewCompat.getTransitionName(binding.imageLogo)));
                // starting our activity with below method.
                startActivity(intent, options.toBundle());
            }
//            binding.imageLogo, Objects.requireNonNull(ViewCompat.getTransitionName(binding.imageLogo))
        }, 2000);
    }
}