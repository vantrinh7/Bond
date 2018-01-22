package com.example.demouser.bond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
//import com.facebook.Response;
import com.facebook.accountkit.AccountKit;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//import com.facebook.Request;
//import com.facebook.Session;
//import com.facebook.model.GraphUser;

import java.util.List;

public class FacebookLoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    public static int APP_REQUEST_CODE = 0077;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        // Register a callback
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goToMainScreen();
                com.facebook.accountkit.AccessToken accessToken = AccountKit.getCurrentAccessToken();
                if (accessToken != null) {
                    //Handle Returning User
                } else {
                    //Handle new or logged out user
                }
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        Bundle bundle = new Bundle();
        bundle.putString("fields", "id, name, picture");
//        Session session = Session.getActiveSession();
//        Request.newMyFriendsRequest(session, new Request.GraphUserListCallback() {
//            @Override
//            public void onCompleted(List<GraphUser> users, Response response) {
//                if(response.getError() == null)
//                {
//                    for (int i = 0; i < users.size(); i++) {
//                        Log.e("My activity", "users " + users.get(i).getName());
//                    }
//                }
//                else
//                {
//                    Toast.makeText(getBaseContext(),
//                            response.getError().getErrorMessage(),
//                            Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });



    }

    /**
     * This method is needed to pass the activity result to the callbackManager
     * and execute methods in the callback
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void goToMainScreen() {
        Intent newIntent = new Intent(getBaseContext(), MainContactScreen.class);
        startActivity(newIntent);
    }
}
