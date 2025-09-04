package com.example.assignment1worker2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment1worker2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CountryAdapter adapter;
    CountryViewModel countryViewModel;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    List<Country>countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = preferences.edit();

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countries = new ArrayList<>();

        adapter = new CountryAdapter(this, countries, position -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("id", countries.get(position).getId());
            startActivity(intent);
        });
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));
        binding.recycle.setAdapter(adapter);
        countryViewModel.getAllCountries().observe(this, list -> {
            Log.d("MainActivity", "size of list" + list.size());
            countries.clear();
            countries.addAll(list);
            adapter.notifyDataSetChanged();
        });
        if (!preferences.contains("savedName")) {
            Executors.newSingleThreadExecutor().execute(() -> {
                Country usa = new Country(
                        R.drawable.usa, "United States", "Washington, DC.", "North America",
                        "331 million", "9,525,067 km2",
                        "The United States is a country primarily located in North America..."
                );



                Country uk = new Country(
                        R.drawable.united_kigdom, "United Kingdom", "London", "Europe",
                        "67.22 million", "242,495 km2",
                        "The United Kingdom, made up of England, Scotland, Wales..."
                );

                Country france = new Country(
                        R.drawable.france, "France", "Paris", "Europe",
                        "65 million", "643,801 km2",
                        "France, located in Western Europe, is famous for its cuisine..."
                );

                Country italy = new Country(
                        R.drawable.italy, "Italy", "Rome", "Europe",
                        "59 million", "301,340 km2",
                        "Italy, located in southern Europe, is known for its rich history..."
                );
                countryViewModel.insert(usa);
                countryViewModel.insert(uk);
                countryViewModel.insert(france);
                countryViewModel.insert(italy);

                editor.putString("savedName", "Added");
                editor.apply();
            });
        }

    }


}