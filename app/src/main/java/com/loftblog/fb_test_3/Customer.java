package com.loftblog.fb_test_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer extends AppCompatActivity implements View.OnClickListener {// S Customer


    Button btn_newCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        btn_newCustomer = (Button)findViewById(R.id.btn_newCustomer);

        btn_newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer.this, NewCustomer.class);
                startActivity(intent);
            }
        });







    }// end onCreate

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_newCustomer) {

        }

    }
}// end Customer
