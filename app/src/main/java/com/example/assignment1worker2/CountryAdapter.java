package com.example.assignment1worker2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1worker2.databinding.CountryItemBinding;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Country> countries;
    private ClickHandle clickHandle;
    CountryItemBinding binding;
    public CountryAdapter(Context context, List<Country> countries, ClickHandle clickHandle) {
        this.context = context;
        this.countries = countries;
        this.clickHandle = clickHandle;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CountryItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyviewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyviewHolder myviewHolder = (MyviewHolder) holder;

        myviewHolder.binding.tvName.setText(countries.get(position).getName());
        myviewHolder.binding.tvCapital.setText(countries.get(position).getCapital());
        myviewHolder.binding.countryImage.setImageResource(countries.get(position).getImage());
        myviewHolder.binding.btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickHandle.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {

        CountryItemBinding binding;

        public MyviewHolder(CountryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface ClickHandle {
        void onItemClick(int position);

    }


}
