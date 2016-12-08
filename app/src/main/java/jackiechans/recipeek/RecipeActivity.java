package jackiechans.recipeek;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.LinkedList;

//displays the information of the Recipe.java class onto the UI

public class RecipeActivity extends AppCompatActivity {


    private Recipe recipeInfo;
    private String title;
    private String country;
    private String category;
    private LinkedList<Ingredient> ingredient;
    private LinkedList<String> instructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeInfo = MainActivity.selectedRecipe;

        title = recipeInfo.getTitle();
        country = recipeInfo.getCountry();
        category = recipeInfo.getCategory();
        ingredient = recipeInfo.getIngredientList();
        instructions = recipeInfo.getStepList();

        printIngredients();
        printInstructions();
        printCountry();
        printCategory();


        //changes the actionbar title to the name of the recipe
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

    }

    //changes the textInstructions to recipe instructions
    private void printInstructions(){
        TextView tv = (TextView) findViewById(R.id.textInstructions);
        tv.setText("");

        int stepNum = 0;

        for (int i = 0; i < instructions.size(); i++){
            stepNum = i +1;
            tv.append(i + ". " +instructions.get(i) +" \n\n");
        }

    }

    //changes the textIngredients to recipe ingredients
    private void printIngredients(){
        TextView tv = (TextView) findViewById(R.id.textIngredients);
        tv.setText("");
        for (int i = 0; i < ingredient.size(); i++){
            tv.append("•" + ingredient.get(i).getQuantity() + " " + ingredient.get(i).getName() + "\n");
        }

    }

    //changes textCountry to recipe country
    private void printCountry(){
        TextView tv = (TextView) findViewById(R.id.textCountry);
        tv.setText(country);
    }

    //changes textType to recipe type
    private void printCategory(){
        TextView tv = (TextView) findViewById(R.id.textCategory);
        tv.setText(category);
    }

    public void editRecipe(){
        Intent intent=new Intent(this, EditRecipeActivity.class);
        startActivity(intent);
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
