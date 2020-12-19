package achmadaffandi.mdamdmc;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import achmadaffandi.mdamdmc.Model.User;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_signup, btn_backlogin;
    private EditText su_nama, su_email, su_password, su_repassword, su_phone;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        su_nama = (EditText) findViewById(R.id.su_nama);
        su_email = (EditText) findViewById(R.id.su_email);
        su_password = (EditText) findViewById(R.id.su_password);
        su_repassword = (EditText) findViewById(R.id.su_repassword);
        su_phone = (EditText) findViewById(R.id.su_phone);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_backlogin = (Button) findViewById(R.id.btn_backlogin);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        btn_backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String nama = su_nama.getText().toString().trim();
        final String email = su_email.getText().toString().trim();
        String password = su_password.getText().toString().trim();
        String repassword = su_repassword.getText().toString().trim();
        final String phone = su_phone.getText().toString().trim();
        String type = "relawan";

        if (nama.isEmpty()) {
            su_nama.setError(getString(R.string.input_error_nama));
            su_nama.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            su_email.setError(getString(R.string.input_error_email));
            su_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            su_email.setError(getString(R.string.input_error_email));
            su_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            su_password.setError(getString(R.string.input_error_password));
            su_password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            su_password.setError(getString(R.string.input_error_password_length));
            su_password.requestFocus();
            return;
        }

        if (!repassword.equals(password)) {
            su_repassword.setError(getString(R.string.input_error_repassword));
            su_repassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            su_phone.setError(getString(R.string.input_error_phone));
            su_phone.requestFocus();
            return;
        }

        if (phone.length() < 9) {
            su_phone.setError(getString(R.string.input_error_phone));
            su_phone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(
                                    nama,
                                    email,
                                    phone,
                                    type
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(i);
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(getApplicationContext(), "Anda telah terdaftar", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this, getString(R.string.registration_failed), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


}
