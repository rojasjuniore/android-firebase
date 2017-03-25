package com.veneco.rojasjuniore.so;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SOActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textView;
    private AppCompatButton signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so);

        firebaseAuth = FirebaseAuth.getInstance();
        String nombre = getIntent().getStringExtra("EmailIntent");
        textView = (TextView) findViewById(R.id.email);
        textView.setText(nombre);

        signOut = (AppCompatButton) findViewById(R.id.signOut);
        signOut.setOnClickListener(SOActivity.this);


    }

    public void signOut() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SOActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        int v = view.getId();
        if (v == R.id.signOut) {
            signOut();
        }

    }
}
