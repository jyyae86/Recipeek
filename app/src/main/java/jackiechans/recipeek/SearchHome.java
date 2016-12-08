package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

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

    public void searchMyFuckingApp(View view){
        String[] searchParams = new String[5];
        searchParams[0] = firstIngred.getText().toString();
        searchParams[1] = secondIngred.getText().toString();
        searchParams[2] = boolOp.getSelectedItem().toString();
        searchParams[3] = country.getSelectedItem().toString();
        searchParams[4] = foodType.getSelectedItem().toString();
        searchFirstParam(searchParams);
        Intent intent = new Intent(this, ListOfSearch.class);
        startActivity(intent);

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

    protected LinkedList<Recipe> searchIngred(String param, LinkedList<Recipe> recipes){
        //Loop through all of the recipes and on each recipe, loop through the ingredients and add the recipe to the results linkedlist if the ingredient exists

        LinkedList<Recipe> result = new LinkedList<Recipe>();
        for(int i = 0; i < recipes.size(); i++){
            Recipe temp = recipes.get(i);
            for(int j = 0; j < temp.getIngredientList().size(); j++){
                if(temp.getIngredientList().get(j).getName().equals(param) && !isAdded(temp, result)){
                    result.add(temp);
                }
            }
        }
        return result;
    }

    protected void searchFirstParam(String[] searchParams){
        LinkedList<Recipe> result = searchIngred(searchParams[0], MainActivity.AllRecipe);
        searchSecondParam(searchParams, result);
    }

    protected void searchSecondParam(String[] searchParam, LinkedList<Recipe> list){
        //if the bool op is and, it will search the previous linkedlist
        //if the bool op is or, it will search from the allRecipes linkedlist and then merge them together
        //if the bool is not, it will search the previous linkedlist for all that does not contain the ingredient
        String ingred = searchParam[1];
        LinkedList<Recipe> result = new LinkedList<Recipe>();
        if(searchParam[1].equals("")){
            result = list;
        }else{
            if(searchParam[2].equals("And")){
                result = searchIngred(ingred, list);
            }else if(searchParam[2].equals("Or")){
                LinkedList<Recipe> tempOrResults = searchIngred(ingred, MainActivity.AllRecipe);
                result = mergeRecipeLists(list, tempOrResults);
            }else{
                //loop through the objects to see if the ingredient exists, if it does, remove that element
                for(int i = 0; i < list.size(); i++){
                    Recipe temp = list.get(i);
                    for(int j = 0; j < temp.getIngredientList().size(); j++){
                                if(temp.getIngredientList().get(j).getName().equals(ingred)){
                                    list.remove(i);
                        }
                    }
                }
                result = list;
            }
        }
        searchCountry(searchParam, result);
    }

    protected void searchCountry(String[] searchParam, LinkedList<Recipe> list){
        LinkedList<Recipe> tempList = new LinkedList<Recipe>();
        if(searchParam[3].equals("N/A")){
            tempList = list;
        }else{
            //loop through all the recipes in list and find all of them that include the country
            for(int i = 0; i < list.size(); i++){
                Recipe tempRecipe = list.get(i);
                if(tempRecipe.getCountry().equals(searchParam[3]) && !isAdded(tempRecipe, tempList)){
                    tempList.add(tempRecipe);
                }
            }
        }
        searchFoodType(searchParam, tempList);
    }

    protected void searchFoodType(String[] searchParam, LinkedList<Recipe> list){
        LinkedList<Recipe> tempList = new LinkedList<Recipe>();
        if(searchParam[4].equals("N/A")){
            tempList = list;
        }else{
            //loop through all the recipes in list and find all of them that include the country
            for(int i = 0; i < list.size(); i++) {
                Recipe tempRecipe = list.get(i);
                if (tempRecipe.getCategory().equals(searchParam[4]) && !isAdded(tempRecipe, tempList)) {
                    tempList.add(tempRecipe);
                }
            }
        }
        MainActivity.resultsRecipe = tempList;
    }

    protected LinkedList<Recipe> mergeRecipeLists(LinkedList<Recipe> l1, LinkedList<Recipe> l2){
        //merges the two linked lists together l1 first then l2
        LinkedList<Recipe> result = new LinkedList<Recipe>();
        for(int i = 0; i < l1.size(); i++){
            result.add(l1.get(i));
        }

        for(int i = 0; i < l2.size(); i++){
            if(!isAdded(l2.get(i), result)) {
                result.add(l2.get(i));
            }
        }
        return result;
    }

    protected boolean isAdded(Recipe recipe, LinkedList<Recipe> list){
        Boolean result = false;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(recipe)){
                result = true;
            }
        }
        return result;
    }



}
