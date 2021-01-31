package com.s3cilabs.mvvmtest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.CustomerViewHolder> {

    private List<Customer> allCustomers = new ArrayList<>();
    private OnCustomerListener onCustomerListener;
    private Context context;

    public CustomerRecyclerViewAdapter(OnCustomerListener onCustomerListener, Context context) {
        this.onCustomerListener = onCustomerListener;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerRecyclerViewAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        CustomerViewHolder customerViewHolder = new CustomerViewHolder(view, onCustomerListener);
        return customerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer currentCustomer = allCustomers.get(position);
        holder.tv_customer_name.setText(currentCustomer.getCustomerName());
        holder.tv_customer_address.setText(currentCustomer.getCustomerAddress());
        holder.tv_customer_email.setText(currentCustomer.getCustomerEmail());

    }

    @Override
    public int getItemCount() {
        return allCustomers.size();
    }

    public void setAllCustomers(List<Customer> customers){
        this.allCustomers = customers;
        notifyDataSetChanged();
    }

    public Customer getCustomerAt(int position){
        return allCustomers.get(position);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_customer_name, tv_customer_address, tv_customer_email;
        private RelativeLayout rv_customers;
        OnCustomerListener onCustomerListener;

        public CustomerViewHolder(@NonNull View itemView, OnCustomerListener onCustomerListener) {
            super(itemView);
            tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
            tv_customer_address = itemView.findViewById(R.id.tv_customer_address);
            tv_customer_email = itemView.findViewById(R.id.tv_customer_email);
            rv_customers = itemView.findViewById(R.id.rv_customers);

            this.onCustomerListener = onCustomerListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCustomerListener.onCustomerClick(getAdapterPosition());
        }
    }

    public interface OnCustomerListener {
        void onCustomerClick(int position);
    }
}
