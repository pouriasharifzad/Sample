package com.example.resume.download;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.resume.R;

    public class ShowPathActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.path_layout);
            TextView path = findViewById(R.id.path);
            Intent intent = getIntent();
            String pathh = intent.getStringExtra("path");
            path.setText(pathh.replaceAll("%20"," "));
        }

    }