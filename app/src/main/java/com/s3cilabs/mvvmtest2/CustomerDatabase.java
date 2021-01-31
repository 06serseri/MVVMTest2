package com.s3cilabs.mvvmtest2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Customer.class, version =  1)
public abstract class CustomerDatabase extends RoomDatabase {

    private static CustomerDatabase customerDatabaseInstance;

    public abstract CustomerDao customerDao();

    public static synchronized CustomerDatabase getCustomerDatabaseInstance(Context context){
        if (customerDatabaseInstance==null){
            customerDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), CustomerDatabase.class, "CUSTOMER_DB.db").
                    fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return customerDatabaseInstance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(customerDatabaseInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private CustomerDao customerDao;

        private PopulateDbAsyncTask(CustomerDatabase customerDatabase){
            customerDao = customerDatabase.customerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.insert(new Customer("Polat Alemdar", "Istanbul, Turkey", "polat@kv.com"));
            customerDao.insert(new Customer("Ivan Drago", "Ural Mountains, USSR", "ivan@ivan.ru"));
            return null;
        }
    }

}
