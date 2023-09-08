package com.example.loginactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    EditText loginText, passwordText;
    Button btnLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginText = findViewById(R.id.login);
        passwordText = findViewById(R.id.password);
        btnLogin = findViewById(R.id.buttonLogin);

        Button btnfragment = findViewById(R.id.fragmentBtn);
        btnfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.Mainpage, LoginFragment.class, null)
                        .commit();
            }
        });


        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);


        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!savedUsername.isEmpty() || !savedPassword.isEmpty()) {
            loginText.setText(savedUsername);
            passwordText.setText(savedPassword);
        }


        loginText.addTextChangedListener(textWatcher);
        passwordText.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                String username = loginText.getText().toString();
                String password = passwordText.getText().toString();

                if (username.equals("admin") && password.equals("123")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    fm.beginTransaction()
                            .replace(R.id.Mainpage, LoginFragment.class, null)
                            .commit();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String username = loginText.getText().toString();
            String password = passwordText.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
        }
    };
}

