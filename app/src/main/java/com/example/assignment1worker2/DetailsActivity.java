package com.example.assignment1worker2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment1worker2.databinding.ActivityDetailsBinding;
import com.example.assignment1worker2.databinding.EditCountryItemBinding;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    int country_id;
    CountryViewModel countryViewModel;
    Country currentCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        country_id=getIntent().getIntExtra("id",0);

        countryViewModel.getById(country_id).observe(this, country -> {
            if (country != null) {
                currentCountry = country;
                binding.tvCountryName.setText(country.getName());
                binding.tvCapital.setText(country.getCapital());
                binding.tvAbout.setText(country.getAbout());
                binding.tvArea.setText(country.getArea());
                binding.tvContinent.setText(country.getContinent());
                binding.tvPopulation.setText(country.getPopulation());
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAlertDialgo();
            }
        });
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCoustemAlertDialgo();
            }
        });
    }

    private void ShowAlertDialgo(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning?!");
        builder.setMessage("The Country Will Deleted");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentCountry!=null){
                    countryViewModel.delete(currentCountry);
                    finish();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
    private void ShowCoustemAlertDialgo(){

        EditCountryItemBinding itemBinding = EditCountryItemBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(itemBinding.getRoot());

        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        if (currentCountry != null) {
            itemBinding.edArea.setText(currentCountry.getArea());
            itemBinding.edPopulation.setText(currentCountry.getPopulation());
            itemBinding.edAbout.setText(currentCountry.getAbout());
        }


        itemBinding.btnSave.setOnClickListener(v -> {
            if (currentCountry!=null){
                currentCountry.setAbout(itemBinding.edAbout.getText().toString());
                currentCountry.setPopulation(itemBinding.edPopulation.getText().toString());
                currentCountry.setArea(itemBinding.edArea.getText().toString());
                countryViewModel.update(currentCountry);

                binding.tvAbout.setText(currentCountry.getAbout());
                binding.tvPopulation.setText(currentCountry.getPopulation());
                binding.tvArea.setText(currentCountry.getArea());
            }

            dialog.dismiss();
        });





    }

}