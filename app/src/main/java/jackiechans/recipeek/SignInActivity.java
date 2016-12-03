package jackiechans.recipeek;

/**
 * Created by jyyae86 on 2016-11-20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity{
    public final static String SIGN_UP_EMAIL = "email";
    public final static String SIGN_UP_PASSWORD = "password";
    private EditText email;
    private EditText password;
    private Button signIn;
    private Button signUp;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Auth", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Auth", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign In");
        Intent intent = getIntent();
        //lets me see what the user put in
        email = (EditText)findViewById(R.id.SignInEmail);
        password = (EditText)findViewById(R.id.SignInPassword);
        signIn = (Button)findViewById(R.id.signin);

    }

    public void createSignUpActivity(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        //Sends intent with user email and password
        String eml = email.getText().toString();
        intent.putExtra(SIGN_UP_EMAIL, eml);
        String pssd = password.getText().toString();
        intent.putExtra(SIGN_UP_PASSWORD, pssd);
        startActivity(intent);
    }

    protected void loginWithEmail(String email, String password){
        Log.d("enter the method","true");
        final Intent intent = new Intent(this, MainActivity.class);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Auth", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Auth", "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignInActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }

                        // ...
                    }
                });
    }

    protected void login(View view){
        String eml = email.getText().toString();
        String pssd = password.getText().toString();
        //check to see if fields are empty, if not, try to authenticate
        if(eml.isEmpty() || pssd.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
            builder.setMessage("Email or password cannot be empty")
                    .setTitle("Attention")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            loginWithEmail(eml, pssd);
        }

    }

    protected void onStart() {
        super.onStart();
        //attaches firebase auth listener
        mAuth.addAuthStateListener(mAuthListener);
    }

    protected void onStop() {
        super.onStop();
        //removes firebase auth listener
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }






}
