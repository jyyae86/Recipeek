package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by jyyae86 on 2016-11-30.
 */

public class FavouriteRecipe extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipes);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Favourite Recipes");
    }


}
