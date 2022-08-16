package com.fyp.autisticchildlearner.UserCred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.autisticchildlearner.AdminAuthen.AdminLogin;
import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.activity.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    EditText edtUsername, edtPassword;
    ImageView lock;
    TextView idgoToSignUpPage, idForgotPassword, logInButton, logTypeBuyer, logTypeSeller;
    String logUserType = "", str_username, str_password;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Credentials");

    CheckBox mRemember;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        logInButton = findViewById(R.id.idLogIn);
        lock = findViewById(R.id.lock);
        idgoToSignUpPage = findViewById(R.id.idNotRegister);
        idForgotPassword = findViewById(R.id.idforgotPassword);



            edtUsername = findViewById(R.id.emailLogIn);
            edtPassword = findViewById(R.id.PasswordLogIn);


            lock.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    startActivity(new Intent(getApplicationContext(), AdminLogin.class));
                    finish();
                    return false;
                }
            });


            sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);



            idgoToSignUpPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                    finish();
                }
            });

            idForgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ForgetActivity.class));
                }
            });

            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    str_username = edtUsername.getText().toString().trim().toLowerCase();
                    str_password = edtPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(str_username)) {
                        edtUsername.setError("Enter username");
                        edtUsername.requestFocus();

                    }else if (TextUtils.isEmpty(str_password)) {
                        edtPassword.setError("Enter Password");
                        edtPassword.requestFocus();
                    } else{
                        reference.child(str_username)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String db_password = snapshot.child("password").getValue(String.class);
                                            if (str_password.equals(db_password)) {

                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("str_username", str_username);
                                                editor.putString("str_password", str_password);
                                                editor.apply();

                                              Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                              intent.putExtra("str_username",str_username);
                                              startActivity(intent);
                                              finish();

                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Record Not Find", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }


                }
            });



    }


}