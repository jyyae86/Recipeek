package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

/**
 * This is the outdated homepage, check HomeActivity for the new homepage
 */

public class MainActivity extends AppCompatActivity {
    //created firebase database and reference to it
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    public static LinkedList<Recipe> AllRecipe=new LinkedList<Recipe>();
    public static LinkedList<Recipe> resultsRecipe = new LinkedList<Recipe>();
    public static Recipe selectedRecipe;
//    myRef.addValueEventListener(new ValueEventListener())

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize firebase
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("message");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Home");



    }

    //below are methods to navigate to new activites
    protected void createSearchActivity(View view){
        Intent intent = new Intent(this, SearchHome.class);
        startActivity(intent);
    }


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
    //START MENU CREATION
    //Creates default menu containing help and settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return true;
    }

    //Calls the methods for switching activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_help) {
            launchHelp();
            return true;
        }
        if(id==R.id.menu_settings){
            launchSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Sends page to help page
    public void launchHelp(){
        Intent intent=new Intent(this, helpActivity.class);
        startActivity(intent);
    }
    //Sends page to settings page
    public void launchSettings(){
        Intent intent=new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    //END MENU CREATION

    public void EditText(){
        Intent intent = new Intent(this, EditRecipeActivity.class);
        startActivity(intent);

    }
}
//Jacky made comments