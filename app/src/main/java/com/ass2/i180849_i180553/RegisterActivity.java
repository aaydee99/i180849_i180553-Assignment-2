package com.ass2.i180849_i180553;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextView signInButton;
    EditText registerName,registerEmail,registerPassword;
    ImageView maleLogo, femaleLogo,undefinedLogo;
    AppCompatButton signupButton;
    TextView showPasswordButton;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signInButton = findViewById(R.id.signInButton);
        registerName = findViewById(R.id.registerName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        showPasswordButton = findViewById(R.id.showPassword);
        signupButton = findViewById(R.id.registerSignUpButton);
        mAuth = FirebaseAuth.getInstance();

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registerPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    //Show Password
                    registerPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    registerPassword.setSelection(registerPassword.getText().length());
                }
                else{
                    //Hide Password
                    registerPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    registerPassword.setSelection(registerPassword.getText().length());
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(
                                registerEmail.getText().toString(),
                                registerPassword.getText().toString()
                        )
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(RegisterActivity.this , MainActivity.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this,
                                        "Failed",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this , SignInActivity.class));
                finish();
            }
        });



    }
}