package jackiechans.recipeek;

import static java.lang.String.valueOf;

/**
 * Junjie Chen, University Of Ottawa ,2016-12-04
 */

public class Recipe {
    //Creates a private


    //Instance variable
    public String title;
    public String country;
    public Ingredient[] ingredients;
    public Step[] steps;



    //constructor
    public Recipe(String title, Ingredient[] ingredients){
        this.title = title;
        this.ingredients = ingredients;
    }

    public Ingredient[] getIngredients(){
        return this.ingredients;
    }
    public Step[] getSteps(){
        return this.steps;
    }

    public String getTitle(){
        return this.title;
    }
    public int ingredientsNum(){
        return this.ingredients.length;
    }
    public String toString(){
        return "recipe name:"+title+" In this recipe, there are "+valueOf(ingredients.length) +"ingredients";
    }

























    // prolly need to update json too
    /*

    public void addIngredient(String value){
        //Loops through the ingredients until it gets to the last one, then it adds it
        Ingredient temp = firstIngredient;
        while(this.firstIngredient.nextIngredient != null){
            temp = temp.nextIngredient;
        }
        temp.nextIngredient = new Ingredient(value, null);
    }

    public void editStep(int stepNum, String value){
        //loops until the step number, then changes the value
        Step temp = firstStep;
        for(int i = 0; i < stepNum; i++){
            temp = temp.nextStep;
        }
        temp.value = value;

    }

    public void editIngredient(int stepNum, String value){
        //loops until the ingredient number, then changes the value
        Step temp = firstStep;
        for(int i = 0; i < stepNum; i++){
            temp = temp.nextStep;
        }
        temp.value = value;
    }

    public void deleteStep(int stepNum){
        //loops until the step number, then deletes it
        Step temp = firstStep;
        for(int i = 0; i < stepNum - 1; i++){
            temp = temp.nextStep;
        }
        //temp is the step before the stepToDelete, then rewrites the nextStep
        Step stepToDelete = temp.nextStep;
        temp.nextStep = stepToDelete.nextStep;
        stepToDelete.nextStep = null;
        stepToDelete = null;
    }

    public void deleteIngredient(int ingredientNum){
        //loops until the step number, then deletes it
        Ingredient temp = firstIngredient;
        for(int i = 0; i < ingredientNum - 1; i++){
            temp = temp.nextIngredient;
        }
        //temp is the step before the stepToDelete, then rewrites the nextStep
        Ingredient ingredientToDelete = temp.nextIngredient;
        temp.nextIngredient = ingredientToDelete.nextIngredient;
        ingredientToDelete.nextIngredient = null;
        ingredientToDelete = null;
    }

    //Getters and Setters
    public Step getFirstStep(){
        return firstStep;
    }

    public Ingredient getFirstIngredient(){
        return firstIngredient;
    }

    public String getName(){
        return name;
    }

    public String getCountry(){
        return country;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCountry(String country){
        this.country = country;
    }
    //Shouldnt need to set firstStep and firstIngredient because they can be edited

*/
}
