package com.s3cilabs.mvvmtest2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository customerRepository;
    private LiveData<List<Customer>> allCustomers;
    private LiveData<Customer> customer;
    private MutableLiveData<Integer> customerId;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
        allCustomers = customerRepository.getAllCustomers();
    }

//    public void setCustomerId(){
//        if (customerId != null){
//            return;
//        }
//        customerId =
//    }

    public MutableLiveData<Integer> getCustomerId(){
        if (customerId == null){
            customerId = new MutableLiveData<Integer>();
        }
        System.out.println("CUSTOMER ID FROM CUSTOMER VIEW MODEL IS: " + customerId);
        return customerId;
    }

    public void insert(Customer customer) {
        customerRepository.insert(customer);
    }

    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAllCustomers();
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }

//    public LiveData<Customer> getCustomer(int customerId) {
//        this.customerId = customerId;
//        customerRepository.getCustomer(customerId);
//        System.out.println("CUSTOMER ID AT CUSTOMER VIEW MODEL IS: " + customerId);
//        System.out.println("CUSTOMER NAME AT CUSTOMER VIEW MODEL IS: " + customer);
//        return customer;
//    }
}
