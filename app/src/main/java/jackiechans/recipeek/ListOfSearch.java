package jackiechans.recipeek;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/*
    Class created by Junjie Chen
    Altered By Rawaz Kader Kahraman
 */

public class ListOfSearch extends AppCompatActivity {

    private LinkedList<Recipe> results = new LinkedList<Recipe>();

    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_search);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Search Results");


        results = MainActivity.resultsRecipe;

        resultList = (ListView) findViewById(R.id.resultList);
        resultList.setClickable(true);

        String[] names = new String[results.size()];
        for(int i = 0; i< results.size(); i++) {
            names[i] = results.get(i).getTitle();
        }
        String[] categories = new String[results.size()];
        for(int j = 0; j<results.size(); j++) {
            categories[j] = results.get(j).getCategory();
        }

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> categoryList = new ArrayList<String>();
        for(int i = 0; i < results.size(); i++){
            nameList.add(names[i]);
            categoryList.add(categories[i]);
        }
        ArrayList<Recipe> result2 = new ArrayList<>(results);

        RecipeArrayAdapter adapter = new RecipeArrayAdapter(this, result2);
        resultList.setAdapter(adapter);
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View v, int position, long id){
                MainActivity.selectedRecipe = (Recipe) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}
