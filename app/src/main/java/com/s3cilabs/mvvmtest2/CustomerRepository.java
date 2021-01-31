package com.s3cilabs.mvvmtest2;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CustomerRepository {
    private CustomerDao customerDao;
    private LiveData<List<Customer>> allCustomers;
    private LiveData<Customer> customer;
    private int customerId;

    public CustomerRepository(Application application){
        CustomerDatabase customerDatabase = CustomerDatabase.getCustomerDatabaseInstance(application);
        customerDao = customerDatabase.customerDao();
        allCustomers = customerDao.getAllCustomers();
//        customer = customerDao.getCustomer(customerId);
    }

    public void insert (Customer customer){
        new InsertCustomerAsyncTask(customerDao).execute(customer);
    }

    public void update (Customer customer){
        new UpdateCustomerAsyncTask(customerDao).execute(customer);
    }

    public void delete (Customer customer){
        new DeleteCustomerAsyncTask(customerDao).execute(customer);
    }

    public LiveData<Customer> getCustomer(int customerId){
        final MutableLiveData<Customer> customerMutableLiveData = new MutableLiveData<>();


        this.customerId = customerId;
//        customerDao.getCustomer(customerId).enq

        System.out.println("CUSTOMER ID AT CUSTOMER REPOSITORY IS: " + customerId);
//        new GetCustomerAsyncTask(customerDao).execute(customerId);
        System.out.println("CUSTOMER NAME AT CUSTOMER REPOSITORY IS: " + customer);
        return customer;
    }


    public void deleteAllCustomers (){
        new DeleteAllCustomersAsyncTask(customerDao).execute();
    }

    public LiveData<List<Customer>> getAllCustomers (){
        return allCustomers;
    }


    private static class InsertCustomerAsyncTask extends AsyncTask <Customer, Void, Void>{

        private  CustomerDao customerDao;

        private InsertCustomerAsyncTask(CustomerDao customerDao ){
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.insert(customers[0]);
            return null;
        }
    }

    private static class UpdateCustomerAsyncTask extends AsyncTask <Customer, Void, Void>{

        private  CustomerDao customerDao;

        private UpdateCustomerAsyncTask(CustomerDao customerDao ){
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.update(customers[0]);
            return null;
        }
    }

    private static class DeleteCustomerAsyncTask extends AsyncTask <Customer, Void, Void>{

        private  CustomerDao customerDao;

        private DeleteCustomerAsyncTask(CustomerDao customerDao ){
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.delete(customers[0]);
            return null;
        }
    }

    private static class GetCustomerAsyncTask extends AsyncTask <Integer, Void, Void>{

        private  CustomerDao customerDao;

        private GetCustomerAsyncTask(CustomerDao customerDao ){
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            customerDao.getCustomer(integers[0]);
            return null;
        }
    }

    private static class DeleteAllCustomersAsyncTask extends AsyncTask <Void, Void, Void>{

        private  CustomerDao customerDao;

        private DeleteAllCustomersAsyncTask(CustomerDao customerDao ){
            this.customerDao = customerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.deleteAllCustomers();
            return null;
        }
    }

}
