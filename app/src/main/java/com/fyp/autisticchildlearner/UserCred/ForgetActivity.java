package com.fyp.autisticchildlearner.UserCred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.autisticchildlearner.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgetActivity extends AppCompatActivity {

    EditText username;
    String  str_username;
    TextView next, userTypeBuyer, userTypeSeller,goToLogin;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Credentials");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);



        username = findViewById(R.id.username);

        next = findViewById(R.id.checkbtn);
        goToLogin = findViewById(R.id.idNotRegister);


        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }
        });
    }



    public void Next(View view) {
        str_username = username.getText().toString().trim().toLowerCase();
        if (TextUtils.isEmpty(str_username)) {
            username.setError("Enter username");
            username.requestFocus();
        }else {
            reference.child(str_username)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                String db_email = snapshot.child("email").getValue(String.class);
                                String db_fullName = snapshot.child("fullName").getValue(String.class);
                                String db_nicNo = snapshot.child("nicNo").getValue(String.class);
                                String db_password = snapshot.child("password").getValue(String.class);
                                String db_phoneNo = snapshot.child("phoneNo").getValue(String.class);
                                String db_username = snapshot.child("username").getValue(String.class);


                                Intent intent = new Intent(getApplicationContext(), ForgetActivity02.class);
                                intent.putExtra("db_email", db_email);
                                intent.putExtra("db_fullName", db_fullName);
                                intent.putExtra("db_nicNo", db_nicNo);
                                intent.putExtra("db_password", db_password);
                                intent.putExtra("db_phoneNo", db_phoneNo);
                                intent.putExtra("db_username", db_username);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "Record Not Find", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


        }
    }

}