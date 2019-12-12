package com.example.a327_prototype_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//register activity
public class RegisterActivity extends AppCompatActivity {
    EditText emailAddress, password;
    Button button_SignUp;
    TextView textView_signIn;
    FirebaseAuth myFireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myFireBaseAuth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.editText_email);//get input of email
        password = findViewById(R.id.editText_password);//get input of password
        button_SignUp = findViewById(R.id.button_signUp);//signing up button
        textView_signIn = findViewById(R.id.textView_signIn);//signing in text
        button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String myPassword = password.getText().toString();
                if(email.isEmpty()) {//if no email is input, return error
                    emailAddress.setError("Please enter your email");
                    emailAddress.requestFocus();
                }else if(myPassword.isEmpty()){//if no password is input, return error
                    password.setError("Please create your password");
                    password.requestFocus();
                }else if(email.isEmpty() && myPassword.isEmpty()) {//if all empty, still error
                    Toast.makeText(RegisterActivity.this,"Fields are empty!", Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && myPassword.isEmpty())){
                    myFireBaseAuth.createUserWithEmailAndPassword(email, myPassword).addOnCompleteListener(RegisterActivity.this,
                            new OnCompleteListener<AuthResult>() {//register
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){//if sign in failed
                                        Toast.makeText(RegisterActivity.this,"Sign Up Failed, Try Again", Toast.LENGTH_SHORT).show();
                                    }else{
                                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                        //logged in -> start in home page
                                    }
                                }
                            });
                }else{
                    Toast.makeText(RegisterActivity.this,"Unknown Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView_signIn.setOnClickListener(new View.OnClickListener() {//click for start activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
