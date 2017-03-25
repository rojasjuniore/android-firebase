package com.veneco.rojasjuniore.so;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText input_name, input_email, input_password;
    private TextView link_login;
    private AppCompatButton btn_signup;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        input_name = (EditText) findViewById(R.id.input_name);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        link_login = (TextView) findViewById(R.id.link_login);
        btn_signup = (AppCompatButton) findViewById(R.id.btn_signup);

        link_login.setOnClickListener(SignupActivity.this);
        btn_signup.setOnClickListener(SignupActivity.this);

        progressDialog = new ProgressDialog(SignupActivity.this);


    }


    private void registerUser() {

        String email = input_email.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignupActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                (SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignupActivity.this, SOActivity.class);
                            intent.putExtra("EmailIntent", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }


    @Override
    public void onClick(View view) {
        int v = view.getId();
        if (v == R.id.btn_signup) {
            registerUser();
        } else if (v == R.id.link_login) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}
