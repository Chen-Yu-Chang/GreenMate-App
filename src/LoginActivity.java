package com.example.a327_prototype_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailAddress, password;
    Button button_SignIn;
    TextView textView_signUp;
    FirebaseAuth myFireBaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myFireBaseAuth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        button_SignIn = findViewById(R.id.button_signIn);
        textView_signUp = findViewById(R.id.textView_signUp);
        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFireBaseUser = myFireBaseAuth.getCurrentUser();
                if(myFireBaseUser != null){
                    Toast.makeText(LoginActivity.this,"You're logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"Please login", Toast.LENGTH_SHORT).show();
                }

            }
        };
        button_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String myPassword = password.getText().toString();
                if(email.isEmpty()) {
                    emailAddress.setError("Please enter your email");
                    emailAddress.requestFocus();
                }else if(myPassword.isEmpty()){
                    password.setError("Please create your password");
                    password.requestFocus();
                }else if(email.isEmpty() && myPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Fields are empty!", Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && myPassword.isEmpty())){
                    myFireBaseAuth.signInWithEmailAndPassword(email, myPassword).addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this,"Login Error, Try Again", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Intent intentMain = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intentMain);
                                    }

                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this,"Unknown Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentSignUp);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        myFireBaseAuth.addAuthStateListener(myAuthStateListener);
    }
}

