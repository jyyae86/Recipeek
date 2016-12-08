package jackiechans.recipeek;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import android.support.v7.app.AlertDialog;
import android.content.*;
import android.support.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.Stack;

import jackiechans.recipeek.Recipe;

import static jackiechans.recipeek.MainActivity.AllRecipe;
import static jackiechans.recipeek.MainActivity.selectedRecipe;
import static jackiechans.recipeek.MainActivity.storeRecipeObject;
import static java.lang.String.valueOf;
//Junjie Chen, University of Ottawa ,2016-12-04


public class EditRecipeActivity extends AppCompatActivity {
    Stack<Integer> ingredientNameID= new Stack();
    Stack<Integer> ingredientQuantityID= new Stack();
    Stack<Integer> stepID= new Stack();
    Recipe myRecipe;
    static int GlobalId =1;
    String done_error_title = "Error!";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        int index = 0;
        Recipe recipe = AllRecipe.get(index);
        for(int i=0;i<recipe.getIngredientsSum();i++){//add ingredient

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
            ingredientEditText.setText(recipe.getIngredientList().get(i).getName());
            quantityEditText.setHint("Add quantity");
            quantityEditText.setText(recipe.getIngredientList().get(i).getQuantity());
            linearLayout.addView(ingredientEditText);
            linearLayout.addView(quantityEditText);

            ingredientNameID.push(ingredientEditText.getId());
            ingredientQuantityID.push(quantityEditText.getId());
        }
        for(int j=0;j<recipe.getStepList().size();j++){ // add step

            LinearLayout mLayout = (LinearLayout) findViewById(R.id.steps_container);
            EditText stepEditText =createNewStepEditText();
            mLayout.addView(stepEditText);
            stepID.push(stepEditText.getId());
            stepEditText.setText(recipe.getStepList().get(j));


        }

    }

    public void createActivityDone(View view) {
        // this will check no empty edit text before uploaded
        //get the results and store in a linkedList
        Stack<String> ingreStack = new Stack();
        Stack<String> stackquantity = new Stack();
        Stack<String> stepStack = new Stack();
        Stack<Integer> nameBackUp = new Stack();
        Stack<Integer> quantityBackUp = new Stack();

        String title = getStringById(R.id.titleEditText);

        while(!ingredientNameID.isEmpty()){
            int temp = ingredientNameID.pop();
            nameBackUp.push(temp);
            if(hasContent(temp)){
                String content =getStringById(temp);
                ingreStack.push(content); //push to a new stack
            }
        }
        while(!ingredientQuantityID.isEmpty()){
            int temp = ingredientQuantityID.pop();
            quantityBackUp.push(temp);
            if(hasContent(temp)){
                String content =getStringById(temp);
                stackquantity.push(content); //push to a new stack which holds quantity
            }
        }
        Spinner countrySP = (Spinner) findViewById(R.id.countrySp);
        String country = countrySP.getSelectedItem().toString();

        if (title.isEmpty()) {//if no title , cannot submit
            AlertDialog.Builder builder = new AlertDialog.Builder(EditRecipeActivity.this);
            builder.setMessage("There is no title")
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            ingreStack.clear();;
            stackquantity.clear();
            while(!nameBackUp.isEmpty()){
                ingredientNameID.push(nameBackUp.pop());
                ingredientQuantityID.push(quantityBackUp.pop());
            }
        } else if(ingreStack.size()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(EditRecipeActivity.this);
            builder.setMessage("Please add ingredients")
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            ingreStack.clear();;
            stackquantity.clear();
            while(!nameBackUp.isEmpty()){
                ingredientNameID.push(nameBackUp.pop());
                ingredientQuantityID.push(quantityBackUp.pop());
            }

        }else if((stackquantity.size()!=ingreStack.size())){
            AlertDialog.Builder builder = new AlertDialog.Builder(EditRecipeActivity.this);
            builder.setMessage("Missing one quantity or one ingredient name")
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            ingreStack.clear();;
            stackquantity.clear();
            while(!nameBackUp.isEmpty()){
                ingredientNameID.push(nameBackUp.pop());
                ingredientQuantityID.push(quantityBackUp.pop());
            }
        }else if(country.equals("N/A")){
            AlertDialog.Builder builder = new AlertDialog.Builder(EditRecipeActivity.this);
            builder.setMessage("Missing country name")
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            ingreStack.clear();;
            stackquantity.clear();
            while(!nameBackUp.isEmpty()){
                ingredientNameID.push(nameBackUp.pop());
                ingredientQuantityID.push(quantityBackUp.pop());
            }

        } else {// ********this step will start to create obeject

            Spinner category = (Spinner)findViewById(R.id.category);
            String categoryString = category.getSelectedItem().toString();
            LinkedList<Ingredient> ingredientLinkedList=new LinkedList();
            LinkedList<String> stepLinkedList=new LinkedList();

            while(!stepID.isEmpty()){
                int temp = stepID.pop();
                if (hasContent(temp)){
                    stepStack.push(getStringById(temp));
                }
            }
            while(!stepStack.isEmpty()){

                stepLinkedList.add(stepStack.pop());
            }


            while(!ingreStack.isEmpty()){
                Ingredient ingredient= new Ingredient(ingreStack.pop(),stackquantity.pop());
                ingredientLinkedList.add(ingredient);

            }

            myRecipe = new Recipe(title,ingredientLinkedList,stepLinkedList,categoryString,country);
            AllRecipe.set(AllRecipe.indexOf(selectedRecipe),myRecipe);
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
            builder.setMessage(myRecipe.toString())
                    .setTitle("Your recipe: "+myRecipe.title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

*/
            //this.finish();

            mReference.child(myRecipe.getTitle()).setValue(myRecipe);
            //storeRecipeObject(myRecipe);
            Intent intent = new Intent(this, done.class);


            //intent.putExtra("myRecipe",myRecipe);
            startActivity(intent);
        }
    }
    public boolean hasContent(int id){ //this will check an ID has content or not
        String content = getStringById(id);
        if(content.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public String getStringById(int id){
        EditText my = (EditText)findViewById(id);
        return my.getText().toString();
    }

    public void backToMain(View view){
        this.finish();
    } // OnClick method, go to main UI

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

    /*
    private void galleryIntent() // this will open the gallery
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }*/


}
