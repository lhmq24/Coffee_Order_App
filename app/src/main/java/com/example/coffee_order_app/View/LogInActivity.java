package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffee_order_app.R;

public class LogInActivity extends AppCompatActivity {
    private TextView LogInLabel;
    private EditText username;
    private Button button;
    private EditText password;
    private CheckedTextView forgotpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Get Views
        LogInLabel = findViewById(R.id.logIn_Text);
        username = findViewById(R.id.editText_username);
        button = findViewById(R.id.login_button);
        password = findViewById(R.id.editText_password);
        forgotpw = findViewById(R.id.ForgotPasswordTextView);

        //Add listener
        button.setOnClickListener(v -> {
            String u_name; //String username
            String pw; //String password
            try {
                u_name = username.getText().toString();
                pw = password.getText().toString();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
            //Ensure no empty box
            if (!u_name.isEmpty() && !pw.isEmpty()) {
                //Create HTTP query and JWT access token


                //Move to MainActivity
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                intent.putExtra("username", u_name);
                intent.putExtra("password", pw);
                startActivity(intent);
            }
        });


    }
}