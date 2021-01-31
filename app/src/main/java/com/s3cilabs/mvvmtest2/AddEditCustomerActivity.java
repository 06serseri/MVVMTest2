package com.s3cilabs.mvvmtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditCustomerActivity extends AppCompatActivity {
    public static final String KEY_CUSTOMER_NAME = "keyCustomerName";
    public static final String KEY_CUSTOMER_ADDRESS = "keyCustomerAddress";
    public static final String KEY_CUSTOMER_EMAIL = "keyCustomerEmail";
    private EditText et_customer_name, et_customer_address, et_customer_email;
    private Button btn_save_customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_customer);
        initViews();

        btn_save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote(){
        String customerName = et_customer_name.getText().toString();
        String customerAddress = et_customer_address.getText().toString();
        String customerEmail = et_customer_email.getText().toString();

        if (customerName.trim().isEmpty() || customerAddress.trim().isEmpty() || customerEmail.trim().isEmpty()){
            Toast.makeText(this, "Please insert customer name, address and email", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(KEY_CUSTOMER_NAME, customerName);
            intent.putExtra(KEY_CUSTOMER_ADDRESS, customerAddress);
            intent.putExtra(KEY_CUSTOMER_EMAIL, customerEmail);

            setResult(RESULT_OK, intent);
            finish();
        }

    }

    private void initViews() {
        et_customer_name = findViewById(R.id.et_customer_name_old);
        et_customer_address = findViewById(R.id.et_customer_address_old);
        et_customer_email = findViewById(R.id.et_customer_email_old);
        btn_save_customer = findViewById(R.id.btn_save_customer_old);
    }
}