package jackiechans.recipeek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import static jackiechans.recipeek.MainActivity.AllRecipe;

/**
 * Created by jyyae86 on 2016-11-30.
 */

public class MyRecipe extends AppCompatActivity{


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        //setContent();

        /* Testing receive object
        Recipe aR;
        Bundle bundle = this.getIntent().getExtras();//this will get the recipe
        aR =(Recipe) bundle.getSerializable("myRecipe");
        recipe=aR;*/

    }





}
