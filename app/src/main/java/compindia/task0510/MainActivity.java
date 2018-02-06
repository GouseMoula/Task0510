package compindia.task0510;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView infoUname,infoEmail,infoMobile;
    Button buttonLogout,buttondDeleteAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showInfo();
    }
    void init()
    {
        infoUname= (TextView) findViewById(R.id.txt_info_username);
        infoEmail= (TextView) findViewById(R.id.info_email);
        infoMobile= (TextView) findViewById(R.id.inf0_mobile);
        buttondDeleteAccount= (Button) findViewById(R.id.btn_delete_account);
        buttonLogout= (Button) findViewById(R.id.btn_logout);
        buttonLogout.setOnClickListener(this);
        buttondDeleteAccount.setOnClickListener(this);
    }

    void showInfo()
    {
        infoUname.setText("Hi, "+MyPrefs.getUserData(this,MyConsts.USER_NAME_KEY));
        infoEmail.setText(MyPrefs.getUserData(this,MyConsts.USER_EMAIL_KEY));
        infoMobile.setText(MyPrefs.getUserData(this,MyConsts.USER_PHONE_KEY));
    }
    void deleteAccount()
    {
        MyPrefs.deleteAllData(this);
        MyPrefs.setRegistered(this,MyConsts.REGISTERED_KEY,false);
        MyPrefs.setLoggedIn(MainActivity.this,MyConsts.LOGGED_IN_KEY,false);
        Toast.makeText(this,"Account deleted out successfully",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(intent);
        finish();

    }
    void logout()
    {
        MyPrefs.setLoggedIn(MainActivity.this,MyConsts.LOGGED_IN_KEY,false);
        Toast.makeText(this,"Logged out successfully",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_delete_account:
                deleteAccount();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }
}
