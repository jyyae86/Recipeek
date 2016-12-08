package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

/*This class controls the page to see the user's self-created recipes.
Recipes are stored within the class.
If the recipe is clicked it sends the user to the recipe page for editting.
Long hold allows for deletion
 */

public class MyRecipes extends AppCompatActivity{


    private LinkedList<Recipe> recipes = MainActivity.AllRecipe;

    ListView myRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("My Recipes");


        myRecipeList = (ListView) findViewById(R.id.myRecipeList);
        myRecipeList.setClickable(true);

        String[] names = new String[recipes.size()];
        for(int i = 0; i< recipes.size(); i++) {
            names[i] = recipes.get(i).getTitle();
        }
        String[] categories = new String[recipes.size()];
        for(int j = 0; j<recipes.size(); j++) {
            categories[j] = recipes.get(j).getCategory();
        }

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> categoryList = new ArrayList<String>();
        for(int i = 0; i < recipes.size(); i++){
            nameList.add(names[i]);
            categoryList.add(categories[i]);
        }
        ArrayList<Recipe> arrayListRecipes = new ArrayList<>(recipes);

        RecipeArrayAdapter adapter = new RecipeArrayAdapter(this, arrayListRecipes);
        myRecipeList.setAdapter(adapter);
        myRecipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                MainActivity.selectedRecipe = (Recipe) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });
    }


}

