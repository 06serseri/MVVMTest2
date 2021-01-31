package com.s3cilabs.mvvmtest2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insert(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("DELETE FROM CUSTOMER_TABLE")
    void deleteAllCustomers();

    @Query("SELECT * FROM CUSTOMER_TABLE")
    LiveData<List<Customer>> getAllCustomers();

    @Query("SELECT * FROM  CUSTOMER_TABLE  WHERE customerId = :customerId")
    LiveData<Customer>  getCustomer(int customerId);
    //https://developer.android.com/training/data-storage/room/async-queries#java

}
