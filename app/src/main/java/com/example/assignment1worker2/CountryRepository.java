package com.example.assignment1worker2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountryRepository {
    private CountryDao countryDao;
    private LiveData<List<Country>> allCountries;
    private ExecutorService executorService;

    public CountryRepository(Application application) {
        AppDataBase db = AppDataBase.getInstance(application);
        countryDao = db.countryDao();
        allCountries = countryDao.getAllCountries();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }

    public LiveData<Country> getById(int id) {
        return countryDao.getById(id);
    }

    public void insert(Country country) {
        executorService.execute(() -> countryDao.insert(country));
    }

    public void update(Country country) {
        executorService.execute(() -> countryDao.update(country));
    }

    public void delete(Country country) {
        executorService.execute(() -> countryDao.delete(country));
    }
}
