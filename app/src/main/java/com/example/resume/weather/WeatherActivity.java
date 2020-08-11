package com.example.resume.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.resume.R;
import com.example.resume.common.BaseActivity;
import com.example.resume.common.DynamicTypeChangeListener;
import com.example.resume.weather.models.WeatherResponse;

public class WeatherActivity extends BaseActivity {

    TextView tvTemperature, tvFeelsLike, tvHumidity;
    EditText etCity;
    ImageButton ibSearch;
    WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().hide();
        viewModel = new WeatherViewModel(this,this);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvFeelsLike = findViewById(R.id.tvFeelsLike);
        tvHumidity = findViewById(R.id.tvHumidity);
        etCity = findViewById(R.id.etCityName);
        ibSearch = findViewById(R.id.btnSearch);


        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.fetchInfo(etCity.getText().toString());
                viewModel.responseDynamicType.setListener(new DynamicTypeChangeListener<WeatherResponse>() {
                    @Override
                    public void onChanged(WeatherResponse value) {
                        if (value != null) {
                            tvFeelsLike.setText("Feels Like : " + value.getMain().getFeels_like());
                            tvTemperature.setText("Temperature : " + String.valueOf(value.getMain().getTemp()));
                            tvHumidity.setText("Humidity : " + String.valueOf(value.getMain().getHumidity()));
                        }

                    }
                });
            }
        });

    }
}