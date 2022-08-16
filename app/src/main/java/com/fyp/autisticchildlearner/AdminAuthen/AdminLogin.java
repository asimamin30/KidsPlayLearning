package com.fyp.autisticchildlearner.AdminAuthen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.UserCred.RegistrationActivity;
import com.fyp.autisticchildlearner.activity.MainActivity;

public class AdminLogin extends AppCompatActivity {

    EditText et_username,et_pass;
    Button btn_Login;

   String username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        et_username=findViewById(R.id.et_email);
        et_pass=findViewById(R.id.et_password);

        btn_Login=findViewById(R.id.btn_login);


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=et_username.getText().toString();
                pass=et_pass.getText().toString();


                if(username.equals("admin")&&pass.equals("admin")){
                    Intent intent=new Intent(getApplicationContext(), AdminDashBoard.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    public void onClickNext(View view) {
        Intent intent=new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
        finish();

    }
}