package com.example.bitcoinminertestwork.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {DataModel.class}, version = 1)
public abstract class BitcoinDataBase extends RoomDatabase {
    private static BitcoinDataBase instance;
    public abstract DataDao dataDao();
    public static synchronized BitcoinDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BitcoinDataBase.class, "bitcoin_data")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private DataDao dataDao;
        private PopulateDbAsyncTask(BitcoinDataBase db){
            dataDao = db.dataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.insert(new DataModel(125, 0.00000125d, 0.25d, 0));
            return null;
        }
    }
}
