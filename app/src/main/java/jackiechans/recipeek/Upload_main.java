package jackiechans.recipeek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import android.support.v7.app.AlertDialog;
import android.content.*;
import android.support.*;


public class Upload_main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_main);
        // following code will make dynamical edit text


    }

    public void createActivityDone(View view) {
        EditText titleEdit = (EditText) findViewById(R.id.titleEditText);
        String step1 = ((EditText) findViewById(R.id.editText17)).getText().toString();
        String step2 = ((EditText) findViewById(R.id.editText18)).getText().toString();
        String step3 = ((EditText) findViewById(R.id.editText19)).getText().toString();
        String step4 = ((EditText) findViewById(R.id.editText20)).getText().toString();
        String step5 = ((EditText) findViewById(R.id.editText21)).getText().toString();

        String title = titleEdit.getText().toString();
        String done_error_message = "Your recipe must have title, ingredients, cooking time , and at least one step";
        String done_error_title = "Error!";

        boolean hasAtLeastOneStep = (!step1.isEmpty())||(!step2.isEmpty())||(!step3.isEmpty())||(!step4.isEmpty())||(!step5.isEmpty());


        if (title.isEmpty() || (!hasAtLeastOneStep)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
            builder.setMessage(done_error_message)
                    .setTitle(done_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            this.finish();
            Intent intent = new Intent(this, done.class);
            startActivity(intent);
        }
    }
    public void backToMain(View view){
        this.finish();
    }
    public void OpenCamera(View view){

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(Upload_main.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
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



        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }
    /*
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }*/


}
