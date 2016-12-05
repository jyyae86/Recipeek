package jackiechans.recipeek;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchHome extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private EditText searchBox;
    private EditText searchCountry;
    private Spinner foodType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Search");

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        searchBox =  (EditText) findViewById(R.id.ingredientBox);
        searchCountry = (EditText) findViewById(R.id.countryBox);
        foodType = (Spinner) findViewById(R.id.categorySelect);
    }

    public void search(){
        Query test = mReference.endAt("pizza");

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchHome.this);
        builder.setMessage(test.toString())
                .setTitle("Attention")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
