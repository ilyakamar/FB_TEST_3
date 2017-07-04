package com.loftblog.fb_test_3.customers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loftblog.fb_test_3.start.Main_MenuActivity;
import com.loftblog.fb_test_3.R;

public class Customer extends AppCompatActivity implements View.OnClickListener {// S Customer

    private Button btn_newCustomer, btn_back_toMain_menu;


    public FirebaseAuth mAuth;
    private DatabaseReference myRef;

    private FirebaseDatabase database;


    FirebaseUser user = mAuth.getInstance().getCurrentUser();


















    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 0:
                break;
            case 1:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Start onCreate   *************************************************
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);





        database = FirebaseDatabase.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference(); // initsialeziruem nashu silku na bazu danih

        RecyclerView rv_list_customers = (RecyclerView) findViewById(R.id.rv_list_customers);

        // budim peredavat danie
        FirebaseRecyclerAdapter<String, Customer.CustomerViewHolder> adapter_customers;// adapter_customers

        rv_list_customers.setLayoutManager(new LinearLayoutManager(this));
        rv_list_customers.setHasFixedSize(true);




        // manganon le hafala shel optsiyot FB shel Child

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(Customer.this, "onChildAdded", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(Customer.this, "onChildChanged", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(Customer.this, "onChildRemoved", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                Toast.makeText(Customer.this, "onChildMoved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Customer.this, "onCancelled", Toast.LENGTH_SHORT).show();

            }
        });





        adapter_customers = new FirebaseRecyclerAdapter<String, Customer.CustomerViewHolder>
                (String.class,
                        R.layout.customer_layout_rv,
                        Customer.CustomerViewHolder.class,
                        myRef.child(user.getUid()).child("Customer").child("Name")) {

            @Override
            protected void populateViewHolder(final Customer.CustomerViewHolder viewHolder, String customerName, final int position) {// populateViewHolder

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
                        Intent intent = new Intent(Customer.this, DetailCustomerActivity.class);
                        intent.putExtra("Reference", getRef(position).getKey().toString());
                        startActivity(intent);
                    }
                });


                viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {// setOnCreateContextMenuListener
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add(viewHolder.getAdapterPosition(),0,0,"Delete");
                        menu.add(viewHolder.getAdapterPosition(),1,0,"change");

                    }
                });// end setOnCreateContextMenuListener


            }// end populateViewHolder

        };

        rv_list_customers.setAdapter(adapter_customers);


        initControl();


    }// end onCreate



    private static class CustomerViewHolder extends RecyclerView.ViewHolder {// S TaskViewHolder

        TextView  customer_email_field;
        TextView  tv_customer_name;
        TextView  customer_phone_field;

        private Button btn_del_customer;

        public CustomerViewHolder(View itemView) {// S CustomerViewHolder
            super(itemView);

            tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
            customer_email_field = (TextView) itemView.findViewById(R.id.customer_email_field);
            customer_phone_field = (TextView) itemView.findViewById(R.id.customer_phone_field);

            btn_del_customer = (Button) itemView.findViewById(R.id.btn_del_customer);
        }// end constructor CustomerViewHolder
    }// end TaskViewHolder







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
