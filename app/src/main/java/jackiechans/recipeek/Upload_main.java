package jackiechans.recipeek;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import android.support.v7.app.AlertDialog;
import android.content.*;
import android.support.*;

import java.util.LinkedList;
import java.util.Stack;

import jackiechans.recipeek.Recipe;
//Junjie Chen, University of Ottawa ,2016-12-04

public class Upload_main extends AppCompatActivity {
    LinkedList<Integer> ingredientNameID= new LinkedList();
    LinkedList<Integer> ingredientQuantityID= new LinkedList();
    LinkedList<Integer> stepID= new LinkedList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_main);
    }

    public void createActivityDone(View view) {
        // this will check no empty edit text before uploaded
        //get the results and store in a linkedList
        Stack<Integer> stack = new Stack();
        EditText titleEdit = (EditText) findViewById(R.id.titleEditText);

        String title = titleEdit.getText().toString();
        String done_error_message = "Your recipe must have title, ingredients, cooking time , and at least one step";
        String done_error_title = "Error!";

        for(int i :ingredientNameID){
            if (!hasContent(i)){
                ingredientNameID.remove(ingredientNameID.indexOf(i));
            }else{
                stack.push(i);
            }
        }
        Ingredient[] ingredients = new Ingredient[stack.size()];
        int counter = 0;
        while(!stack.isEmpty()){
            EditText myEditext = (EditText)findViewById(stack.pop());
            String content = myEditext.getText().toString();
            Ingredient ingredient = new Ingredient(content,"DunnmyQuantity");
            ingredients[counter]=ingredient;
        }



        if (title.isEmpty()) {//check content is not empty
            AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
            builder.setMessage(done_error_message)
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            //Recipe myRecipe = new Recipe(title,ingredients);

            AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
            builder.setMessage(myRecipe.toString())
                    .setTitle("Your recipe")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();

            //this.finish();
            //Intent intent = new Intent(this, done.class);
            //startActivity(intent);
        }
    }
    public boolean hasContent(int id){ //this will check an ID has content or not

        EditText editText = (EditText) findViewById(id);
        String content = editText.getText().toString();
        if(content.isEmpty()){
            return false;
        }else{
            return true;
        }

    }

    public void backToMain(View view){
        this.finish();
    } // OnClick method, go to main UI

    public void OpenCamera(View view){
        //Onclick method, it will pop out a dialog which shows 3 choices to user
        // Take photo or Choose from library or Cancel
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
        public void onClick(DialogInterface dialog, int item) { //dummy dialog
               if (items[item].equals("Take Photo")) {
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
        ingredientEditText.setHint("Add ingredient");
        quantityEditText.setHint("Quantity");
        linearLayout.addView(ingredientEditText);
        linearLayout.addView(quantityEditText);
        ingredientNameID.add(ingredientEditText.getId());
        ingredientQuantityID.add(quantityEditText.getId());
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
        return newtext;
    }
    public void moreStep(View view){ //OnClick , Add more steps
        LinearLayout mLayout = (LinearLayout) findViewById(R.id.steps_container);
        EditText stepEditText =createNewStepEditText();
        mLayout.addView(stepEditText);
        stepID.add(stepEditText.getId());
    }
    private EditText createNewStepEditText() { //this will creat step editText
        // this will create Edit Text dynamically
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText newtext = new EditText(this);
        newtext.setLayoutParams(lparams);
        newtext.setWidth(700);
        newtext.setMinimumHeight(50);
        newtext.setHint("Add Step");
        return newtext;
    }
    /*
    private void galleryIntent() // this will open the gallery
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }*/


}
