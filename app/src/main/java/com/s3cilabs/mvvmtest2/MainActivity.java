package com.s3cilabs.mvvmtest2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ONE = 1;
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_customer);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ONE && resultCode == RESULT_OK) {
            String customerName = data.getStringExtra(AddEditCustomerActivity.KEY_CUSTOMER_NAME);
            String customerAddress = data.getStringExtra(AddEditCustomerActivity.KEY_CUSTOMER_ADDRESS);
            String customerEmail = data.getStringExtra(AddEditCustomerActivity.KEY_CUSTOMER_EMAIL);

            Customer newCustomer = new Customer(customerName, customerAddress, customerName);
            customerViewModel.insert(newCustomer);

            Toast.makeText(this, "Customer Added: " + newCustomer, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Customer Add Failed", Toast.LENGTH_SHORT).show();
        }
    }

}