package com.loftblog.fb_test_3.start;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loftblog.fb_test_3.R;

public class EmailPasswordActivity extends AppCompatActivity implements View.OnClickListener { // Start EmailPasswordActivity


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String TAG;

    private EditText et_email;     /// =ETemail
    private EditText et_password; // =ETpassword


    @Override
    protected void onCreate(Bundle savedInstanceState) { // start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);



        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in

                    Intent intent = new Intent(EmailPasswordActivity.this, Main_MenuActivity.class);
                    startActivity(intent);



                } else {
                    // User is signed out

                }
                // ...
            }
        };


        et_email = (EditText) findViewById(R.id.ET_email);
        et_password = (EditText) findViewById(R.id.ET_password);


        findViewById(R.id.btn_Login).setOnClickListener(this);
        findViewById(R.id.btn_LinkToRegisterScreen).setOnClickListener(this);



     FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            Intent intent = new Intent(EmailPasswordActivity.this, Main_MenuActivity.class);
            startActivity(intent);

        }


    }// end onCreate


    @Override
    public void onClick(View v) {//S onClick

        if (v.getId() == R.id.btn_Login) {
            signing(et_email.getText().toString(), et_password.getText().toString());

        } else if (v.getId() == R.id.btn_LinkToRegisterScreen) {
            registration(et_email.getText().toString(), et_password.getText().toString());

        }


    }// end onClick


    public void signing(String email, String password) {// S signing

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // matai she hametoda tase imut az itbatsea >> onComplete
                if (task.isSuccessful()) {
                    Toast.makeText(EmailPasswordActivity.this, "Authorization is successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EmailPasswordActivity.this, Main_MenuActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(EmailPasswordActivity.this, "Authorization is Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }//end signing


    private void registration(String email, String password) {//S registration

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EmailPasswordActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EmailPasswordActivity.this, "Registration is Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }//end registration


}// END EmailPasswordActivity
