package com.example.turenindahproperty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    private TextView registeruser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth= FirebaseAuth.getInstance();
//        banner= (TextView) findViewById(R.id.banner);
//        banner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterUser.this,MainActivity.class));
//            }
//        });

        registeruser=(Button) findViewById(R.id.registerUser);
        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        editTextAge=(EditText) findViewById(R.id.age);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextFullName=(EditText) findViewById(R.id.fullname);
        editTextPassword=(EditText) findViewById(R.id.password);


    }

    private void register() {
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        String fullname= editTextFullName.getText().toString().trim();
        String age= editTextAge.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextFullName.setError("Nama Harus diisi!");
            editTextFullName.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Umur Harus diisi!");
            editTextAge.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email Harus diisi!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email Tidak Valid!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password Harus diisi!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password Minimal 6 Karakter!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user= new User(fullname,age,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User Berhasil Mendaftar!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(RegisterUser.this,"User Gagal Mendaftar!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterUser.this,"User Gagal Mendaftar!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}