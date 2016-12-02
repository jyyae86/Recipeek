package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static jackiechans.recipeek.SignInActivity.SIGN_UP_EMAIL;
import static jackiechans.recipeek.SignInActivity.SIGN_UP_PASSWORD;

/**
 * Created by jyyae86 on 2016-11-25.
 */

public class SignUpActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //initialize firebaseAuth variable
        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        //getting intent from signup class
        Intent intent = getIntent();
        String eml = intent.getStringExtra(SIGN_UP_EMAIL);
        String pssd = intent.getStringExtra(SIGN_UP_PASSWORD);
        email = (EditText)findViewById(R.id.signUpEmail);
        password = (EditText)findViewById(R.id.signUpPassword);
        email.setText(intent.getStringExtra(SIGN_UP_EMAIL));
        password.setText(intent.getStringExtra(SIGN_UP_PASSWORD));


    }

    protected void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Auth", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            //todo, redirect user back to signinactivity and autofill
                        }

                        // ...
                    }
                });
    }

    protected void signUp(View view){
        String eml = email.getText().toString();
        String pssd = password.getText().toString();
        //check to see if fields are empty, if not, try to authenticate
        if(eml.isEmpty() || pssd.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
            builder.setMessage("Email or password cannot be empty. Note that password has to be longer than 6 digits")
                    .setTitle("Attention")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            createUser(eml, pssd);
        }
    }
}
