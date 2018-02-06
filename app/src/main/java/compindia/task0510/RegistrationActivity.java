package compindia.task0510;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences preferences;
    EditText uName, uEmail, uPhone, uPass;
    Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        setListeners();
    }

    private void init() {
        uName = (EditText) findViewById(R.id.edtxt_username_full);
        uEmail = (EditText) findViewById(R.id.edtxt_user_email);
        uPhone = (EditText) findViewById(R.id.edtxt_user_phone);
        uPass = (EditText) findViewById(R.id.edtxt_usepassword);
        buttonCreateAccount = (Button) findViewById(R.id.btn_create_account);
    }

    private void setListeners() {

        buttonCreateAccount.setOnClickListener(this);
    }

    private boolean isValidName(String name) {
        if (name.isEmpty() || name.length() < 3) {
            return false;
        }
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    private boolean isValidEmail(String email) {

        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,255}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidPhone(String phNum) {

        if (phNum != null && (phNum.startsWith("9") || phNum.startsWith("8") || phNum.startsWith("7"))) {
            phNum = phNum.replaceAll("[^0-9]", "");
            if (phNum.length() != 10) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password) {
        String regx = "[a-zA-Z0-9]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    void showAlert(int titleID, int messageID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleID);
        builder.setIcon(R.drawable.ic_error);
        builder.setMessage(messageID);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    private void validateAndSubmitData() {
        String name=uName.getText().toString();
        String email=uEmail.getText().toString();
        String mobile=uPhone.getText().toString();
        String password=uPass.getText().toString();
        if ((name==null||name.isEmpty()||email==null||email.isEmpty()||mobile==null||mobile.isEmpty()||password==null||password.isEmpty())) {
           Toast toast= Toast.makeText(this,"Fields cannot be empty",Toast.LENGTH_SHORT);
            toast.setGravity(24,0,0);
            toast.show();

        }
        else {

            if (!isValidName(uName.getText().toString())) {
                showAlert(R.string.name_error_title, R.string.name_error_message);
            } else if (!isValidPhone(uPhone.getText().toString())) {
                showAlert(R.string.phone_error_title, R.string.phone_error_message);
            } else if (!isValidPassword(uPass.getText().toString())) {
                showAlert(R.string.password_error_title, R.string.password_error_message);
            } else if (!isValidEmail(uEmail.getText().toString())) {
                showAlert(R.string.email_error_title, R.string.email_error_message);
            } else {
                MyPrefs.setUserData(this, MyConsts.USER_NAME_KEY, uName.getText().toString().trim());
                MyPrefs.setUserData(this, MyConsts.USER_EMAIL_KEY, uEmail.getText().toString().replaceAll(" ", ""));
                MyPrefs.setUserData(this, MyConsts.USER_PASSWOD_KEY, uPass.getText().toString().replaceAll(" ", ""));
                MyPrefs.setUserData(this, MyConsts.USER_PHONE_KEY, uPhone.getText().toString());
                MyPrefs.setRegistered(this, MyConsts.REGISTERED_KEY, true);
                showSuccess();

            }
        }
    }

    void showSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registered Successfully!");
        builder.setIcon(R.drawable.ic_account);
        builder.setMessage("Do you want to login now?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_account:
                validateAndSubmitData();
                break;
            default:
                break;
        }
    }
}
