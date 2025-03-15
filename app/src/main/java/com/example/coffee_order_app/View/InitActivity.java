package com.example.coffee_order_app.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffee_order_app.Interface.InitActivityInterface;
import com.example.coffee_order_app.Interface.TokenExpirationCallback;
import com.example.coffee_order_app.Model.TokenManager;
import com.example.coffee_order_app.Presenter.InitActivityPresenter;

public class InitActivity extends AppCompatActivity implements InitActivityInterface {
    private TokenManager tokenManager;
    private InitActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tokenManager = new TokenManager(this); // Initialize token manager
        presenter = new InitActivityPresenter(this, this);

        // Check if there's an access token
        String accessToken = tokenManager.getAccessToken();
        Log.d("Init activity", "Access Token: " + accessToken);
        if (accessToken != null && !accessToken.isEmpty()) {
            // Check if the token is expired asynchronously
            presenter.checkTokenExpiration(new TokenExpirationCallback() {
                @Override
                public void onTokenValidationResult(boolean isExpired) {
                    if (isExpired) {
                        Log.d("Init activity", "Token expired, refreshing...");
                        presenter.refresh_token();
                    } else {
                        moveToMain();
                        Log.d("Init activity", "Token is valid. Move to Main");
                    }
                }
            });
        } else {
            // No access token, go to LoginActivity
            moveToLogIn();
            Log.d("Init activity", "Move to Log In");
        }
    }

    // Navigate to MainActivity
    public void moveToMain() {
        Intent intent = new Intent(InitActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Navigate to LoginActivity
    public void moveToLogIn() {
        Intent intent = new Intent(InitActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
