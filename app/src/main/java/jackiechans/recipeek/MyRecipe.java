package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/*This class controls the page to see the user's self-created recipes.
Recipes are stored within the class.
If the recipe is clicked it sends the user to the recipe page for editting.
Long hold allows for deletion
 */

public class MyRecipe extends AppCompatActivity{
    //Creates the class
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
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

