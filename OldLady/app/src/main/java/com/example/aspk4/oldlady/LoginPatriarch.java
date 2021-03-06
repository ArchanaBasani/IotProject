package com.example.aspk4.oldlady;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPatriarch extends AppCompatActivity {
    private static TextView tlp;
    private static EditText Email;
    private static EditText Password;
    private static Button btlgin;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patriarch);
        tlp = (TextView)findViewById(R.id.textView4);
        Email= (EditText)findViewById(R.id.editText);
        Password= (EditText)findViewById(R.id.editText2);
        btlgin= (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginPatriarch.this, HomePatriarch.class));
            finish();
        }


        btlgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                final String password = Password.getText().toString();
                Log.d("email=",email);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginPatriarch.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        //    progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error

                            if (password.length() < 6) {
                                Password.setError("Enter Correct Password !!!!");
                            } else {
                                // Log.w(TAG, "signInWithEmailAndPassword", task.getException());
                                Toast.makeText(LoginPatriarch.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginPatriarch.this, HomePatriarch.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
});

    }
}
