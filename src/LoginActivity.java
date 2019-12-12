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
//login activity
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
        emailAddress = findViewById(R.id.editText_email);//input email
        password = findViewById(R.id.editText_password);//input password
        button_SignIn = findViewById(R.id.button_signIn);//sign in button
        textView_signUp = findViewById(R.id.textView_signUp);//sign up text
        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser myFireBaseUser = myFireBaseAuth.getCurrentUser();
                if(myFireBaseUser != null){//sucessfully logged in
                    Toast.makeText(LoginActivity.this,"You're logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{//not logged in
                    Toast.makeText(LoginActivity.this,"Please login", Toast.LENGTH_SHORT).show();
                }

            }
        };
        button_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//click to input
                String email = emailAddress.getText().toString();
                String myPassword = password.getText().toString();
                if(email.isEmpty()) {//if email empty, return error
                    emailAddress.setError("Please enter your email");
                    emailAddress.requestFocus();
                }else if(myPassword.isEmpty()){//if password empty, return error
                    password.setError("Please create your password");
                    password.requestFocus();
                }else if(email.isEmpty() && myPassword.isEmpty()) {//if both empty, return error
                    Toast.makeText(LoginActivity.this,"Fields are empty!", Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && myPassword.isEmpty())){//all input
                    myFireBaseAuth.signInWithEmailAndPassword(email, myPassword).addOnCompleteListener(LoginActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){//but wrong, fail logging in
                                        Toast.makeText(LoginActivity.this,"Login Error, Try Again", Toast.LENGTH_SHORT).show();
                                    }else{//successfully logged in and start activity
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
            //click on sign up to jump to register activity
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

