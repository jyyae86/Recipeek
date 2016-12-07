package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        /*Tesing for passing object
        Recipe aRecipe;
        Bundle bundle = this.getIntent().getExtras();
        aRecipe = (Recipe) bundle.getSerializable("myRecipe");
        this.recipe=aRecipe;*/
        }
    public static void hello(){

    }


    public void returnToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
