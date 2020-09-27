package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.myproject.databinding.ActivityMainBinding;
import com.example.myproject.retrofit.ModelBook;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    List<ModelBook> books = new ArrayList<>();
    AdapterRecycler adapterRecycler;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        adapterRecycler = new AdapterRecycler(this, books);
        binding.setMyAdapter(adapterRecycler);
        binding.setViewModel(mainViewModel);

//      Достаем liveData из класса VieModel
        MutableLiveData<List<ModelBook>> listBook = mainViewModel.books;

        listBook.observe(this, new Observer<List<ModelBook>>() {
            @Override
            public void onChanged(List<ModelBook> modelBooks) {
                adapterRecycler.onChange(modelBooks);
            }
        });
    }

}
