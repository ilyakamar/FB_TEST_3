package com.loftblog.fb_test_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_MenuActivity extends AppCompatActivity implements View.OnClickListener{// Start Main_MenuActivity


    Button goToCustomersClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);



        initControl();



    }// end onCreate



    private void initControl() {
        goToCustomersClass = (Button) findViewById(R.id.goToCustomersClass);

        goToCustomersClass.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.goToCustomersClass:
                Intent intent = new Intent(Main_MenuActivity.this,Customer.class);
                startActivity(intent);
                break;

        }


    }


}// END  Main_MenuActivity
