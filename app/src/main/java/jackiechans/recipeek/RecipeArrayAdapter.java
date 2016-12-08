package jackiechans.recipeek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by felip on 2015-11-01.
 */
public class RecipeArrayAdapter extends ArrayAdapter<Recipe>  {
    /**/
    private final Context context;
    private final ArrayList<Recipe> results;

    public RecipeArrayAdapter(Context context, ArrayList<Recipe> values) {
        super(context, R.layout.activity_list_of_search, values);
        this.context = context;
        this.results = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting Recipe
        Recipe curRecipe = results.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.recipe_item_layout, parent, false);
        TextView recipeName = (TextView) rowView.findViewById(R.id.recipeName);
        TextView recipeDescription = (TextView) rowView.findViewById(R.id.recipeCategory);
        ImageView recipeImage = (ImageView) rowView.findViewById(R.id.icon);

        //Placing content into recipe List Item
        recipeName.setText(curRecipe.getTitle());
        recipeDescription.setText(curRecipe.getCategory());

        //TODO: Perform decision regarding image selection for recipe prior to setting an image
        recipeImage.setImageResource(R.drawable.empty_plate);

        return rowView;
    }
}
