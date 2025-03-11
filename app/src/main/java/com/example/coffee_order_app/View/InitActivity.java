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
        Log.d("Init", "Access Token: " + accessToken);
        if (accessToken != null && !accessToken.isEmpty()) {
            // Check if the token is expired asynchronously
            presenter.checkTokenExpiration(new TokenExpirationCallback() {
                @Override
                public void onTokenValidationResult(boolean isExpired) {
                    if (isExpired) {
                        // Token is expired or invalid, handle the refresh token or login
                        presenter.refreshToken();
                        Log.d("Init", "refresh token");
                    } else {
                        // Token is valid, move to main activity
                        moveToMain();
                        Log.d("Init", "Move to Main");
                    }
                }
            });
        } else {
            // No access token, go to LoginActivity
            moveToLogIn();
            Log.d("Init", "Move to Log In");
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
