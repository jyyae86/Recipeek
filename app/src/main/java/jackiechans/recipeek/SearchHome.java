package jackiechans.recipeek;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

import static jackiechans.recipeek.MainActivity.AllRecipe;
import static jackiechans.recipeek.R.id.booleanSelect;
import static jackiechans.recipeek.R.id.ingredientBox1;
import static jackiechans.recipeek.R.id.ingredientBox2;
import static jackiechans.recipeek.R.id.recipeCategory;
import static jackiechans.recipeek.R.id.recipeCountry;

public class SearchHome extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private EditText firstIngred;
    private EditText secondIngred;
    private Spinner boolOp;
    private Spinner country;
    private Spinner foodType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Search");

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        firstIngred = (EditText)findViewById(ingredientBox1);
        secondIngred = (EditText)findViewById(ingredientBox2);
        boolOp = (Spinner)findViewById(booleanSelect);
        country = (Spinner)findViewById(recipeCountry);
        foodType = (Spinner)findViewById(recipeCategory);
    }

    public void search(){
        String[] searchParams = new String[5];
        searchParams[0] = firstIngred.getText().toString();
        searchParams[1] = secondIngred.getText().toString();
        searchParams[2] = boolOp.getSelectedItem().toString();
        searchParams[3] = country.getSelectedItem().toString();
        searchParams[4] = foodType.getSelectedItem().toString();
        searchFirstParam(searchParams);
    }

    public String getBoolean(){
        // this will return "or" or "and "
        Spinner Selecter = (Spinner)findViewById(R.id.booleanSelect);
        String a =Selecter.getContext().toString();
        return a;
    }

    public String getFirstIngredient(){
        //return the string value in the first box
        EditText editText=(EditText)findViewById(ingredientBox1);
        String s = editText.toString();
        return s;
    }
    public String getSecondIngredient(){
        //return the string value in the first box
        EditText editText=(EditText)findViewById(R.id.ingredientBox2);
        String s = editText.toString();
        return s;
    }

    protected void searchFirstParam(String[] searchParams){
        //Loop through all of the recipes and on each recipe, loop through the ingredients and add the recipe to the results linkedlist if
        LinkedList<Recipe> result = new LinkedList<Recipe>();
        for(int i = 0; i < AllRecipe.size(); i++){
            Recipe temp = MainActivity.AllRecipe.get(i);
            for(int j = 0; j < temp.getIngredientList().size(); j++){
                if(temp.getIngredientList().get(j).getName().equals(searchParams[0])){
                    result.add(temp);
                }
            }
        }
    }

}
