package com.loftblog.fb_test_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer extends AppCompatActivity implements View.OnClickListener {// S Customer

    private Button btn_newCustomer, btn_back_toMain_menu;




    public FirebaseAuth mAuth;
    private DatabaseReference myRef;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();






private static class CustomerViewHolder extends RecyclerView.ViewHolder {// S TaskViewHolder

    TextView tv_customer_name,customer_email_field,customer_phone_field;

    private Button btn_del_customer;

    public CustomerViewHolder(View itemView) {// S TaskViewHolder
        super(itemView);

        tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
        customer_email_field= (TextView) itemView.findViewById(R.id.customer_email_field);
        customer_phone_field= (TextView) itemView.findViewById(R.id.customer_phone_field);

        btn_del_customer = (Button) itemView.findViewById(R.id.btn_del_customer);
    }
}// end TaskViewHolder











    @Override
    protected void onCreate(Bundle savedInstanceState) {//Start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        myRef = FirebaseDatabase.getInstance().getReference(); // initsialeziruem nashu silku na bazu danih

        RecyclerView rv_list_customers = (RecyclerView) findViewById(R.id.rv_list_customers);

        // budim peredavat danie
        FirebaseRecyclerAdapter<String, Customer.CustomerViewHolder> adapter_customers;// adapter_customers

        rv_list_customers.setLayoutManager(new LinearLayoutManager(this));
        rv_list_customers.setHasFixedSize(true);


        adapter_customers = new FirebaseRecyclerAdapter<String, Customer.CustomerViewHolder>
                (String.class,
                        R.layout.customer_layout_rv,
                        Customer.CustomerViewHolder.class,
                        myRef.child(user.getUid()).child("Customer").child("Name")) {

            @Override
            protected void populateViewHolder(Customer.CustomerViewHolder viewHolder, String customerName, final int position) {

                viewHolder.tv_customer_name.setText(customerName);

                viewHolder.btn_del_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseReference itemRef = getRef(position);
                        itemRef.removeValue();
                    }
                });

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Customer.this,DetailCustomerActivity.class);
                        intent.putExtra("Reference",getRef(position).getKey().toString());
                        startActivity(intent);
                    }
                });
            }
        };

        rv_list_customers.setAdapter(adapter_customers);



        initControl();


    }// end onCreate









    private void initControl() {
        btn_back_toMain_menu = (Button) findViewById(R.id.btn_back_toMain_menu);
        btn_newCustomer = (Button) findViewById(R.id.btn_newCustomer);

        btn_back_toMain_menu.setOnClickListener(this);
        btn_newCustomer.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {// s onClick


        switch (v.getId()) {// s switch
            case R.id.btn_back_toMain_menu:
                Intent intent_btn_back_toMain_menu = new Intent(Customer.this, Main_MenuActivity.class);
                startActivity(intent_btn_back_toMain_menu);
                break;

            case R.id.btn_newCustomer:
                Intent intent_btn_newCustomer = new Intent(Customer.this, NewCustomer.class);
                startActivity(intent_btn_newCustomer);

        }// end switch


    }// end onClick

}// end Customer
