package com.loftblog.fb_test_3.customers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loftblog.fb_test_3.R;

public class NewCustomer extends AppCompatActivity implements View.OnClickListener {// Start NewCustomer



    public FirebaseAuth mAuth;
    private DatabaseReference myRef;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();


    // form activity
    private Button customer_save_btn;
    private Button btn_back_toCustomers;

    private EditText customer_name_field;
    private EditText customer_email_field;
    private EditText customer_phone_field;






    @Override
    protected void onCreate(Bundle savedInstanceState) {// s onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        myRef = FirebaseDatabase.getInstance().getReference(); // initsialeziruem nashu silku na bazu danih

        customer_name_field = (EditText)findViewById(R.id.customer_name_field);
        customer_email_field= (EditText)findViewById(R.id.customer_email_field);
        customer_phone_field= (EditText)findViewById(R.id.customer_phone_field);


        initControl();








    }// end onCreate


    private void initControl() {
        btn_back_toCustomers = (Button) findViewById(R.id.btn_back_toCustomers);
        customer_save_btn = (Button)findViewById(R.id.customer_save_btn);

        btn_back_toCustomers.setOnClickListener(this);
        customer_save_btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {// s onClick

        Intent btn_back_toCustomers = new Intent(NewCustomer.this, Customer.class);


        switch (v.getId()) {// s switch
            case R.id.btn_back_toCustomers:

                startActivity(btn_back_toCustomers); // hazara le Customer Activity
                break;

            case R.id.customer_save_btn:
                if (customer_name_field.getText().toString().equals("")) {


                    Toast.makeText(NewCustomer.this, "צריך להזין לפחות שם", Toast.LENGTH_SHORT).show();

                }else{
                    myRef.child(user.getUid()).
                            child("Customer").
                            child("Name").
                            push().
                            setValue(customer_name_field.getText().toString());

                    myRef.child(user.getUid()).
                            child("Customer").
                            child("Email").
                            push().
                            setValue(customer_email_field.getText().toString());

                    myRef.child(user.getUid()).
                            child("Customer").
                            child("Phone").
                            push().
                            setValue(customer_phone_field.getText().toString());


                    startActivity(btn_back_toCustomers); // hazara le Customer Activity

                }

                break;



        }// end switch
    }
}// end NewCustomer
