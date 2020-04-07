package com.ramadan.remember.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ramadan.remember.R;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    ProgressBar progressBar;
    EditText email, password, password2;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
    }

    public void submit(View view) {
        final String email_ = email.getText().toString();
        final String password_ = password.getText().toString();
        String password2_ = password2.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        final Map<String, Object> user = new HashMap<>();
        user.put("email", email_);
        user.put("password", password_);

        if (email_.isEmpty() || password_.isEmpty() || password2_.isEmpty()) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "all fields are required!", Toast.LENGTH_SHORT).show();
        } else {
            if (password_.equals(password2_)) {
                System.out.println("01010101");
                System.out.println(email_);
                mAuth.createUserWithEmailAndPassword(email_, password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(sign_up.this, "sign up failed", Toast.LENGTH_SHORT).show();
                        } else {
                            System.out.println("0000000");
                            FirebaseUser fUser = mAuth.getCurrentUser();
                            String user_id = fUser.getUid();
                            System.out.println(user_id + "-------------------");
                            try {
                                System.out.println("3333333" + " *******************");
                                db.collection("user").document(user_id).set(user);
                            } catch (Exception e) {
                                System.out.println(e + " *******************");
                            }
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(sign_up.this, MainActivity.class));
                            Animatoo.animateZoom(sign_up.this);
                            Toast.makeText(sign_up.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "passwords are not matched", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
