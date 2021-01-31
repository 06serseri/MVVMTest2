package com.s3cilabs.mvvmtest2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.List;


public class CustomerReviewFragment extends Fragment {
    //VARIABLES
    private Button btn_select_customer, btn_test_shit;
    private TextView tv_customer_name, tv_customer_address, tv_customer_email;
    private String selectedCustomerName, selectedCustomerAddress, selectedCustomerPhone, selectedCustomerEmail;
    private Integer customerId;
    private CustomerViewModel customerViewModel;
    private Customer customer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_review, container, false);
        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON CREATE VIEW CALLED------");
        //INIT VIEWS
        {
            tv_customer_name = view.findViewById(R.id.tv_customer_name);
            tv_customer_address = view.findViewById(R.id.tv_customer_address);
            tv_customer_email = view.findViewById(R.id.tv_customer_email);
            btn_select_customer = view.findViewById(R.id.btn_select_customer);
            btn_test_shit = view.findViewById(R.id.btn_test_shit);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON VIEW CREATED CALLED------");


        NavController navController = Navigation.findNavController(view);
        customerViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CustomerViewModel.class);

        //GET CLICKED CUSTOMER ID FROM CUSTOMER HOME FRAGMENT
        if (getArguments() != null) {
            CustomerReviewFragmentArgs customerReviewFragmentArgs = CustomerReviewFragmentArgs.fromBundle(getArguments());
            customerId = customerReviewFragmentArgs.getSelectedCustomerId();
            System.out.println("CUSTOMER ID AT CUSTOMER REVIEW FRAGMENT RECEIVED FROM CUSTOMER HOME FRAGMENT IS: " + customerId);

            customerViewModel.getCustomerId().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    customerViewModel.getCustomerId().setValue(customerId);
                    customerId = customerViewModel.getCustomerId().getValue();
                    System.out.println("CUSTOMER ID AT CUSTOMER REVIEW FRAGMENT OUTSIDE TEST SHIT IS: " + customerId);
                }
            });
        }
        customerViewModel.getAllCustomers().observe(getActivity(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> allCustomers) {

                //TODO THIS WORKS BUT NEEDS TO PERSIST
                System.out.println("TEST SHIT CUSTOMER ID AT CUSTOMER REVIEW FRAGMENT IS: " + customerId);

                System.out.println("##### TEST SHIT FINISHED #####");

                for (Customer c : allCustomers) {
                    if (c.getCustomerId() == customerId) {
                        customer = c;
                        System.out.println(c);
                        System.out.println(customer);
                    }
                }

                try {
                    String customerName = customer.getCustomerName();
                    System.out.println("CUSTOMER NAME AT CUSTOMER REVIEW FRAGMENT IS: " + customerName);
                    String customerAddress = customer.getCustomerAddress();
                    String customerEmail = customer.getCustomerEmail();

                    tv_customer_name.setText(customerName);
                    tv_customer_address.setText(customerAddress);
                    tv_customer_email.setText(customerEmail);
                } catch (Exception e) {
                    System.out.println("ERROR DUMBASS");
                }
            }
        });
        btn_test_shit.setEnabled(false);
        btn_test_shit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerViewModel.getAllCustomers().observe(getActivity(), new Observer<List<Customer>>() {
                    @Override
                    public void onChanged(List<Customer> allCustomers) {

                        //TODO THIS WORKS BUT NEEDS TO PERSIST
                        System.out.println("TEST SHIT CUSTOMER ID AT CUSTOMER REVIEW FRAGMENT IS: " + customerId);

                        System.out.println("##### TEST SHIT FINISHED #####");

                        for (Customer c : allCustomers) {
                            if (c.getCustomerId() == customerId) {
                                customer = c;
                                System.out.println(c);
                                System.out.println(customer);
                            }
                        }

                        try {
                            String customerName = customer.getCustomerName();
                            System.out.println("CUSTOMER NAME AT CUSTOMER REVIEW FRAGMENT IS: " + customerName);
                            String customerAddress = customer.getCustomerAddress();
                            String customerEmail = customer.getCustomerEmail();

                            tv_customer_name.setText(customerName);
                            tv_customer_address.setText(customerAddress);
                            tv_customer_email.setText(customerEmail);
                        } catch (Exception e) {
                            System.out.println("ERROR DUMBASS");
                        }
                    }
                });
            }
        });

        btn_select_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_customerReviewFragment_to_customerHomeFragment);
            }
        });

    }


//    //FRAGMENT LIFE CYCLE
//    //A.CREATED
//    //  A1. ON CREATE
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON CREATE CALLED------");
//
//    }
//
//
//    //  A2. ON CREATE VIEW (ABOVE)
//    //  A3. ON VIEW CREATED (ABOVE)
//
//    //  A4. ON VIEW STATE RESTORED
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON VIEW STATE RESTORED CALLED------");
//
//    }
//
//    //  A5. ON START
//    @Override
//    public void onStart() {
//        super.onStart();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON START CALLED------");
//
//    }
//
//    //  A6. ON RESUME
//    @Override
//    public void onResume() {
//        super.onResume();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON RESUME CALLED------");
//    }
//
//    //  A7. ON PAUSE
//    @Override
//    public void onPause() {
//        super.onPause();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON PAUSE CALLED------");
//    }
//
//    //  A8. ON STOP
//    @Override
//    public void onStop() {
//        super.onStop();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON STOP CALLED------");
//    }
//
//    //  A9. ON SAVE INSTANCE STATE (ABOVE)
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON SAVE INSTANCE STATE CALLED------");
//    }
//
//    //  A10. ON DESTROY VIEW
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON DESTROY VIEW CALLED------");
//    }
//
//    //  A11. ON DESTROY
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.out.println("------CUSTOMER REVIEW FRAGMENT - ON DESTROY CALLED------");
//    }
}
