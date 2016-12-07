package jackiechans.recipeek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class RecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Recipe");
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
}
