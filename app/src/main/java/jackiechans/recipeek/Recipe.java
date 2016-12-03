package jackiechans.recipeek;

/**
 * Created by jyyae86 on 2016-12-03.
 */

public class Recipe {
    //Creates a private
    private class Step{
        private Step nextStep;
        private String value;

        private Step(String value, Step next){
            this.value = value;
            this.nextStep = next;
        }
    }

    private class Ingredient{
        private String name;
        private Ingredient nextIngredient;

        private Ingredient(String name, Ingredient next){
            this.name = name;
            this.nextIngredient = next;
        }
    }
    //Instance variable
    private String name;
    private Ingredient firstIngredient;
    private Step firstStep;
    private String country;

    //

    //constructor
    public Recipe(String name, String firstIngredient, String firstStep, String country){
        this.name = name;
        this.firstIngredient = new Ingredient(firstIngredient, null);
        this.firstStep = new Step(firstStep, null);
    }

    public void addStep(String value){
        //Loops through the steps until it gets to the last one, then it adds it
        Step temp = firstStep;
        while(this.firstStep.nextStep != null){
            temp = temp.nextStep;
        }
        temp.nextStep = new Step(value, null);
    }

    public void addIngredient(String value){
        //Loops through the ingredients until it gets to the last one, then it adds it
        Ingredient temp = firstIngredient;
        while(this.firstIngredient.nextIngredient != null){
            temp = temp.nextIngredient;
        }
        temp.nextIngredient = new Ingredient(value, null);
    }

    // prolly need to update json too
    //todo not finished
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


}
