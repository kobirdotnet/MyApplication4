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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextpassword;
    private TextView textViewSignin;
    private Button buttonSignin;

    private FirebaseAuth firebaseAuth;

//    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),NextActivity.class));
            Toast.makeText(getApplicationContext(),"login done",Toast.LENGTH_LONG).show();
        }

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextpassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

//        progressDialog = new ProgressDialog(this);

        buttonSignin.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonSignin){
            userLogin();
        }
        if (v == textViewSignin){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"please enter password", Toast.LENGTH_LONG).show();
            return;
        }

//        progressDialog.setMessage("user login completed please wait...");
//        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
//                            finish();
//                            startActivity(new Intent(getApplicationContext(), NextActivity.class));
                            Toast.makeText(getApplicationContext(),"login done",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"login error",Toast.LENGTH_LONG).show();
                        }
//                        progressDialog.dismiss();
                    }
                });

    }
}
