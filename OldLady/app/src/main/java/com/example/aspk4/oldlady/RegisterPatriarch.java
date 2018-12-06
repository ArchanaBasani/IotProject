package com.example.aspk4.oldlady;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPatriarch extends AppCompatActivity {
    private static TextView trp;

    private static EditText Firstname;
    private static EditText LastName;
    private static EditText password;
    private static EditText retypepassword;
    private static EditText EmailAddress;
    private static EditText Phonenumber;
    private static Button Register;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patriarch);
        auth = FirebaseAuth.getInstance();


        Firstname = (EditText) findViewById(R.id.editText4);
        LastName = (EditText) findViewById(R.id.editText5);
        password = (EditText) findViewById(R.id.editText8);
        retypepassword = (EditText) findViewById(R.id.editText9);
        EmailAddress = (EditText) findViewById(R.id.editText6);
        Phonenumber = (EditText) findViewById(R.id.editText7);
        Register = (Button) findViewById(R.id.button10);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String firstname = Firstname.getText().toString().trim();
                String lastname = LastName.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = retypepassword.getText().toString().trim();
                String email = EmailAddress.getText().toString().trim();
                String phne = Phonenumber.getText().toString().trim();

                if (TextUtils.isEmpty(firstname)) {
                    Toast.makeText(getApplicationContext(), "Enter Your First Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lastname)) {
                    Toast.makeText(getApplicationContext(), "Enter Your last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(repass)) {
                    Toast.makeText(getApplicationContext(), "Enter the password again!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.equals(repass)) {
                    Toast.makeText(getApplicationContext(), "Password Doesn't Match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phne)) {
                    Toast.makeText(getApplicationContext(), "Enter Your phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phne.length() < 10) {
                    Toast.makeText(getApplicationContext(), "enter ten digits of mobile number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phne.length() > 10) {
                    Toast.makeText(getApplicationContext(), "enter ten digits of mobile number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(RegisterPatriarch.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterPatriarch.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterPatriarch.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterPatriarch.this, LoginPatriarch.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);


    }
}
