package com.example.assignment1worker2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository repository;
    private LiveData<List<Country>> allCountries;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        repository = new CountryRepository(application);
        allCountries = repository.getAllCountries();
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }

    public LiveData<Country> getById(int id) {
        return repository.getById(id);
    }

    public void insert(Country country) {
        repository.insert(country);
    }

    public void update(Country country) {
        repository.update(country);
    }

    public void delete(Country country) {
        repository.delete(country);
    }
}
