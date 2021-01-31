package com.s3cilabs.mvvmtest2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomerHomeFragment extends Fragment implements CustomerRecyclerViewAdapter.OnCustomerListener {
    private RecyclerView recyclerView;
    private Button btn_add_edit_customer, btn_to_customer_review;
    private CustomerViewModel customerViewModel;
    final CustomerRecyclerViewAdapter customerRecyclerViewAdapter =
            new CustomerRecyclerViewAdapter(this, getActivity());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_home, container, false);
        System.out.println("------CUSTOMER HOME FRAGMENT - ON CREATE VIEW CALLED------");
        //INIT VIEWS
        {
            recyclerView = view.findViewById(R.id.rv_customers);
            btn_add_edit_customer = view.findViewById(R.id.btn_add_edit_customer);
            btn_to_customer_review = view.findViewById(R.id.btn_to_customer_review);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("------CUSTOMER HOME FRAGMENT - ON VIEW CREATED CALLED------");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        btn_to_customer_review.setEnabled(false);

//        new ItemTouchHelper(itemTouchHelperSimpleCallBackDeleteCustomer).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(customerRecyclerViewAdapter);

        customerViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CustomerViewModel.class);

        customerViewModel.getAllCustomers().observe(getActivity(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> allCustomers) {
                //UPDATE RECYCLER VIEW
                customerRecyclerViewAdapter.setAllCustomers(allCustomers);
                String test = allCustomers.get(0).getCustomerName();
            }
        });

        NavController navController = Navigation.findNavController(view);
        btn_add_edit_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_customerHomeFragment_to_customerAddEditFragment);
            }
        });
        btn_to_customer_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_customerHomeFragment_to_customerReviewFragment);
            }
        });

    }

    @Override
    public void onCustomerClick(int position) {
        Customer clickedCustomer;
        clickedCustomer = customerRecyclerViewAdapter.getCustomerAt(position);
        String clickedCustomerName = clickedCustomer.getCustomerName();
        int clickedCustomerId = clickedCustomer.getCustomerId();

        //SEND CLICKED CUSTOMER ID TO CUSTOMER REVIEW FRAGMENT
        CustomerHomeFragmentDirections.ActionCustomerHomeFragmentToCustomerReviewFragment actionCustomerHomeFragmentToCustomerReviewFragment =
                CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerReviewFragment();
        actionCustomerHomeFragmentToCustomerReviewFragment.setSelectedCustomerId(clickedCustomerId);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_customer);
        navController.navigate(actionCustomerHomeFragmentToCustomerReviewFragment);

        System.out.println("CLICKED CUSTOMER IS: " + clickedCustomerName);
        Toast.makeText(getActivity(), "Clicked customer is: " + clickedCustomerName, Toast.LENGTH_LONG).show();
    }

//    //FRAGMENT LIFE CYCLE
//    //A.CREATED
//    //  A1. ON CREATE
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON CREATE CALLED------");
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
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON VIEW STATE RESTORED CALLED------");
//
//    }
//
//    //  A5. ON START
//    @Override
//    public void onStart() {
//        super.onStart();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON START CALLED------");
//
//    }
//
//    //  A6. ON RESUME
//    @Override
//    public void onResume() {
//        super.onResume();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON RESUME CALLED------");
//    }
//
//    //  A7. ON PAUSE
//    @Override
//    public void onPause() {
//        super.onPause();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON PAUSE CALLED------");
//    }
//
//    //  A8. ON STOP
//    @Override
//    public void onStop() {
//        super.onStop();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON STOP CALLED------");
//    }
//
//    //  A9. ON SAVE INSTANCE STATE (ABOVE)
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON SAVE INSTANCE STATE CALLED------");
//    }
//
//    //  A10. ON DESTROY VIEW
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON DESTROY VIEW CALLED------");
//    }
//
//    //  A11. ON DESTROY
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.out.println("------CUSTOMER HOME FRAGMENT - ON DESTROY CALLED------");
//    }
}
