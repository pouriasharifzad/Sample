package com.example.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.resume.download.DownloadActivity;
import com.example.resume.location.MapsActivity;
import com.example.resume.shop.ShopActivity;
import com.example.resume.weather.WeatherActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {
    ImageButton btnWeather,btnLocation,btnDownload,btnShop;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton btnLinkdin, btnEmail, btnCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnWeather = findViewById(R.id.btnWeather);
        btnLocation = findViewById(R.id.btnLocation);
        btnDownload = findViewById(R.id.btnDownload);
        btnShop = findViewById(R.id.btnShop);
        materialDesignFAM = findViewById(R.id.material_design_android_floating_action_menu);
        btnLinkdin = findViewById(R.id.linkdin);
        btnEmail = findViewById(R.id.email);
        btnCall =  findViewById(R.id.call);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = "tel:09365459401";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(address));
                startActivity(intent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, "pouria.sharifzad@gmail.com");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        btnLinkdin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pouria-sharifzad-79343bb9/"));
                startActivity(browserIntent);
            }
        });
    }




}