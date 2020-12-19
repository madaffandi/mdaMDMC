package achmadaffandi.mdamdmc;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class ForgetPassActivity extends AppCompatActivity {

    private Button btn_backlogin, btn_forgetpass;
    private EditText fp_email;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        fp_email = (EditText) findViewById(R.id.fp_email);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        btn_forgetpass = (Button) findViewById(R.id.btn_forgetpass);
        btn_forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ini buat kalau lupa", Snackbar.LENGTH_SHORT)
                        .show();
                forgetPassword();
            }
        });

        btn_backlogin = (Button) findViewById(R.id.btn_backlogin);
        btn_backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgetPassActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    private void forgetPassword(){
        final String email = fp_email.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        //part of emailing and confirmation
    }
}
