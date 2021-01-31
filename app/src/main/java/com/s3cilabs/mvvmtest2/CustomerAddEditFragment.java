package com.s3cilabs.mvvmtest2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomerAddEditFragment extends Fragment{
    private EditText et_customer_name, et_customer_address, et_customer_email;
    private Button btn_save_customer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_add_edit, container, false);
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON CREATE VIEW CALLED------");
        //INIT VIEWS
        {
            et_customer_name = view.findViewById(R.id.et_customer_name);
            et_customer_address = view.findViewById(R.id.et_customer_address);
            et_customer_email = view.findViewById(R.id.et_customer_email);
            btn_save_customer = view.findViewById(R.id.btn_save_customer);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON VIEW CREATED CALLED------");

        NavController navController = Navigation.findNavController(view);

        btn_save_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_customerAddEditFragment_to_customerHomeFragment);
            }
        });

    }



    //FRAGMENT LIFE CYCLE
    //A.CREATED
    //  A1. ON CREATE
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON CREATE CALLED------");

    }


    //  A2. ON CREATE VIEW (ABOVE)
    //  A3. ON VIEW CREATED (ABOVE)

    //  A4. ON VIEW STATE RESTORED
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON VIEW STATE RESTORED CALLED------");

    }

    //  A5. ON START
    @Override
    public void onStart() {
        super.onStart();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON START CALLED------");

    }

    //  A6. ON RESUME
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON RESUME CALLED------");
    }

    //  A7. ON PAUSE
    @Override
    public void onPause() {
        super.onPause();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON PAUSE CALLED------");
    }

    //  A8. ON STOP
    @Override
    public void onStop() {
        super.onStop();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON STOP CALLED------");
    }

    //  A9. ON SAVE INSTANCE STATE (ABOVE)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON SAVE INSTANCE STATE CALLED------");
    }

    //  A10. ON DESTROY VIEW
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON DESTROY VIEW CALLED------");
    }

    //  A11. ON DESTROY
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("------CUSTOMER ADD EDIT FRAGMENT - ON DESTROY CALLED------");
    }
}
