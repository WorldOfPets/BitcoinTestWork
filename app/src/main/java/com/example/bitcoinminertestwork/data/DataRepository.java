package com.example.bitcoinminertestwork.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    private DataDao dataDao;
    private LiveData<List<DataModel>> data;
    public DataRepository(Application application){
        BitcoinDataBase dataBase = BitcoinDataBase.getInstance(application);
        dataDao = dataBase.dataDao();
        data = dataDao.getAll();
    }

    public void insert(DataModel dataModel){
        new InsertDataAsyncTask(dataDao).execute(dataModel);
    }
    public void update(DataModel dataModel){
        new UpdateDataAsyncTask(dataDao).execute(dataModel);
    }
    public LiveData<List<DataModel>> getData(){
        return data;
    }
    private static class InsertDataAsyncTask extends AsyncTask<DataModel, Void, Void>{
        private DataDao dataDao;
        private InsertDataAsyncTask(DataDao dataDao){
            this.dataDao = dataDao;
        }
        @Override
        protected Void doInBackground(DataModel... dataModels) {
            dataDao.insert(dataModels[0]);
            return null;
        }
    }
    private static class UpdateDataAsyncTask extends AsyncTask<DataModel, Void, Void>{
        private DataDao dataDao;
        private UpdateDataAsyncTask(DataDao dataDao){
            this.dataDao = dataDao;
        }
        @Override
        protected Void doInBackground(DataModel... dataModels) {
            dataDao.update(dataModels[0]);
            return null;
        }
    }
}
