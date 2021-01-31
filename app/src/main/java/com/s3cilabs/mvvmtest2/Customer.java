package com.s3cilabs.mvvmtest2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CUSTOMER_TABLE")
public class Customer {

    @PrimaryKey(autoGenerate = true)
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;

    public Customer(String customerName, String customerAddress, String customerEmail) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
