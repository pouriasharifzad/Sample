package com.example.resume.shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.resume.R;
import com.example.resume.download.database.DBHelper;
import com.example.resume.shop.models.Product;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView img;
    TextView tvtitle, tvprice;
    RatingBar ratings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        final DBHelper db = new DBHelper(this);

        final Product product = db.select(id);
        img = findViewById(R.id.detailimage);
        tvtitle = findViewById(R.id.detailtitle);
        tvprice = findViewById(R.id.detailprice);
        Picasso.get().load(product.getImgresource()).into(img);
        tvtitle.setText(product.getTitle());
        tvprice.setText(String.valueOf(product.getPrice()));
        ratings = findViewById(R.id.rating);
        ratings.setRating(product.getRating());
        ratings.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratings.setRating(rating);
                product.setRating(rating);
                db.updateproduct(product);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void smsOnclick(View view) {
        String address = "sms:0000";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(address));
        startActivity(intent);
    }

    public void callOnClick(View view) {

        String address = "tel:0000";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(address));
        startActivity(intent);
    }

}