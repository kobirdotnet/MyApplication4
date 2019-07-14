package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private TextView textViewSignin;

//    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        textViewSignin = (TextView)findViewById(R.id.textViewSignin);
        buttonSignup = (Button)findViewById(R.id.buttonSignup);

        textViewSignin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);

//        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),NextActivity.class));
            Toast.makeText(getApplicationContext(),"registration done",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v == buttonSignup){
            userRegistered();
        }
        if (v == textViewSignin){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void userRegistered() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "please enter password", Toast.LENGTH_LONG).show();
            return;
        }

//        progressDialog.setMessage("user registration completed please wait...");
//        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
//                           finish();
//                           startActivity(new Intent(getApplicationContext(),NextActivity.class));
                           Toast.makeText(getApplicationContext(),"registration done",Toast.LENGTH_LONG).show();
                       }else {
                           Toast.makeText(MainActivity.this, "user registration error",Toast.LENGTH_LONG).show();
                       }
//                       progressDialog.dismiss();
                    }
                });
    }
}
