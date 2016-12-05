package jackiechans.recipeek;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyyae86 on 2016-11-30.
 */


public class FavouriteRecipe extends AppCompatActivity {
    private ListView favList;

    public void onCreate(Bundle saveInstanceState) {
        setContentView(R.layout.your_layout);
        favList = (ListView) findViewById(R.id.listFavView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                favRecipes);

        favList.setAdapter(arrayAdapter);
    }

    //favourited recipes are stored in an array
    private Recipe[] favRecipe = new Recipe[1000];
    private int numOfFavRecipes = 0;
    ArrayList<String> favRecipes=new ArrayList<String>();

    public void addFavRecipe(Recipe newRecipe) {
        favRecipe[numOfFavRecipes] = newRecipe;
        favRecipes.add(newRecipe.title);
        numOfFavRecipes++;
        return;
    }
}