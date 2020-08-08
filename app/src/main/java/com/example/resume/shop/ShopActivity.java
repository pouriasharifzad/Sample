package com.example.resume.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.resume.R;
import com.example.resume.common.DynamicTypeChangeListener;
import com.example.resume.download.database.DBHelper;
import com.example.resume.shop.adapter.MyAdapter;
import com.example.resume.shop.models.Product;
import com.example.resume.shop.service.FetchService;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    ListView listView;
    GridView gridView;
    RadioButton gridbtn;
    RadioButton listbtn;
    SharedPreferences logincheckshrdpref,wificheckshrdpref;
    MyAdapter adapter;
    ArrayList<Product> list;
    ArrayList<Product> searchlist;
    TextView tvname;
    DBHelper db;
    EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        listView = findViewById(R.id.productslistview);
        db = new DBHelper(this);
        list = db.selectProducts();
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
        gridView = findViewById(R.id.productsgridview);
        gridView.setAdapter(adapter);
        gridbtn = findViewById(R.id.gridrbtn);
        listbtn = findViewById(R.id.listrbtn);
        logincheckshrdpref = getSharedPreferences("loginstate", MODE_PRIVATE);
        wificheckshrdpref = getSharedPreferences("wifi",MODE_PRIVATE);
        tvname = findViewById(R.id.tvname);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);


        View v = LayoutInflater.from(this).inflate(R.layout.actionbar_layout, null);
        getSupportActionBar().setCustomView(v);

        Button btn = v.findViewById(R.id.btn);
        et = v.findViewById(R.id.etsearch);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et.getText().toString().isEmpty()) {
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShopActivity.this, DetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());

                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShopActivity.this, DetailsActivity.class);
                intent.putExtra("id", list.get(position).getId());

                startActivity(intent);
            }
        });

    }

    public void viewtype(View view) {
        if (gridbtn.isChecked()) {
            gridView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        if (listbtn.isChecked()) {
            listView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.INVISIBLE);
        }

    }

    public void btnonclick(View view) {
        searchlist = new ArrayList<Product>();
        String search = et.getText().toString();
        for (Product d : list) {
            if (d.getTitle() != null && d.getTitle().contains(search)) {
                searchlist.add(d);
            }
        }
        adapter.setList(searchlist);
        adapter.notifyDataSetChanged();
    }
}