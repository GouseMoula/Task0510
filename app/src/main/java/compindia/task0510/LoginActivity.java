package compindia.task0510;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void init() {
        loginEmail = (EditText) findViewById(R.id.edtxt_user_email_login);
        loginPassword = (EditText) findViewById(R.id.edtxt_userpassword_login);
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                if (!(email == null || email.isEmpty() || password == null || password.isEmpty())) {
                    if (!email.equals(MyPrefs.getUserData(LoginActivity.this, MyConsts.USER_EMAIL_KEY))) {
                        Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(MyPrefs.getUserData(LoginActivity.this, MyConsts.USER_PASSWOD_KEY))) {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    } else {
                        MyPrefs.setLoggedIn(LoginActivity.this, MyConsts.LOGGED_IN_KEY, true);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Email or password cannot be empty.", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
