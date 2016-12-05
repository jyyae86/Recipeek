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


public class MyRecipe extends AppCompatActivity {
    private ListView myList;

    public void onCreate(Bundle saveInstanceState) {
        setContentView(R.layout.your_layout);

        myList = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myRecipeList);

        myList.setAdapter(arrayAdapter);
    }

    //created recipes are stored in an array
    private Recipe[] recipe = new Recipe[1000];
    private int numOfRecipes = 0;
    ArrayList<String> myRecipeList=new ArrayList<String>();

    public void addRecipe(Recipe newRecipe) {
        recipe[numOfRecipes] = newRecipe;
        myRecipeList.add(newRecipe.title);
        numOfRecipes++;
        return;
    }
}
