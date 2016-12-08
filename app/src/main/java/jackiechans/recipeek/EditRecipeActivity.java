package jackiechans.recipeek;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.LinkedList;

import static jackiechans.recipeek.MainActivity.AllRecipe;

public class EditRecipeActivity extends AppCompatActivity {
    static int GlobalId=1;
    LinkedList stepID;
    Stack ingredientNameID＝new Stack();
    LinkedList ingredientQuantityID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        int index=0;
        Recipe recipe = AllRecipe.get(index);

    }






    public void OpenCamera(View view){
        //Onclick method, it will pop out a dialog which shows 3 choices to user
        // Take photo or Choose from library or Cancel
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(EditRecipeActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) { //dummy dialog
                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void cameraIntent(){
        // this will open the camera
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public void MoreIngredient(View view){ //OnClick to add more ingredients
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.ingredientContainer);
        LinearLayout linearLayout = createLinear();
        mLayout.addView(linearLayout);
        EditText ingredientEditText = createNewEditText();
        EditText quantityEditText = createNewEditText();
        ingredientEditText.setId(+GlobalId);
        GlobalId++;
        quantityEditText.setId((+GlobalId));
        GlobalId++;
        ingredientEditText.setHint("Add ingredient");
        quantityEditText.setHint("Add quantity");
        linearLayout.addView(ingredientEditText);
        linearLayout.addView(quantityEditText);

        ingredientNameID.push(ingredientEditText.getId());
        ingredientQuantityID.push(quantityEditText.getId());
    }

    private LinearLayout createLinear(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    private EditText createNewEditText() {
        // this will create Edit Text dynamically
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText newtext = new EditText(this);
        newtext.setLayoutParams(lparams);
        newtext.setWidth(350);
        newtext.setMinimumHeight(50);
        newtext.setInputType(InputType.TYPE_CLASS_TEXT);
        return newtext;
    }
    public void moreStep(View view){ //OnClick , Add more steps
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.steps_container);
        EditText stepEditText =createNewStepEditText();
        mLayout.addView(stepEditText);
        stepID.push(stepEditText.getId());
    }
    private EditText createNewStepEditText() { //this will creat step editText
        // this will create Edit Text dynamically
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText newtext = new EditText(this);
        newtext.setLayoutParams(lparams);
        newtext.setWidth(700);
        newtext.setMinimumHeight(50);
        newtext.setHint("Add Step");
        newtext.setId(+GlobalId);
        GlobalId++;

        return newtext;
    }
}
