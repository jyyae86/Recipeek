package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

/**
 * This is the outdated homepage, check HomeActivity for the new homepage
 */

public class MainActivity extends AppCompatActivity {
    //created firebase database and reference to it
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    public static LinkedList<Recipe> AllRecipe =new LinkedList();
//    myRef.addValueEventListener(new ValueEventListener())

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Home");



    }

    //below are methods to navigate to new activites
    public void createSignInActivity(View view){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void createUpdateMain(View view){
        Intent intent = new Intent(this, Upload_main.class);
        startActivity(intent);

    }

    public void createFavouriteRecipeActivity(View view){
        Intent intent = new Intent(this, FavouriteRecipe.class);
        startActivity(intent);
    }

    public void createMyRecipeActivity(View view){
        Intent intent = new Intent(this, MyRecipe.class);
        startActivity(intent);
    }
    public static void storeRecipeObject(Recipe a){
        AllRecipe.add(a);


    }
}
//Jacky made comments