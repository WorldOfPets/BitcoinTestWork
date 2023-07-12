package com.example.bitcoinminertestwork.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.bitcoinminertestwork.data.BitcoinDataBase;
import com.example.bitcoinminertestwork.data.DataModel;
import com.example.bitcoinminertestwork.data.DataRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private DataRepository repository;
    public LiveData<List<DataModel>> data;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        data = repository.getData();
    }

    public LiveData<List<DataModel>> getData(){
        return data;
    }
    public void update(DataModel dataModel){
        repository.update(dataModel);
    }

}