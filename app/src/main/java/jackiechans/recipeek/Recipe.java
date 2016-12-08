package jackiechans.recipeek;

import java.io.Serializable;
import java.util.LinkedList;

import static java.lang.String.valueOf;

/**
 * Junjie Chen, University Of Ottawa ,2016-12-04
 */

public class Recipe implements Serializable{
    //Creates a private


    //Instance variable
    private String title;
    private String country;
    private String category;
    private LinkedList<Ingredient> ingredientList;
    private LinkedList<String> stepList;




    //constructor
    public Recipe(String title, LinkedList<Ingredient> ingredientList,LinkedList<String> stepLinkedList,String category,String county){
        this.title = title;
        this.ingredientList = ingredientList;
        this.category=category;
        this.stepList=stepLinkedList;
        this.country=county;
        }




    public LinkedList<Ingredient> getIngredientList(){

        return this.ingredientList;
    }

    public LinkedList<String> getStepList(){
        return this.stepList;
    }

    public String getTitle(){

        return this.title;
    }

    public int getIngredientsSum(){

        return this.ingredientList.size();
    }

    public String getCategory(){

        return this.category;
    }
    public String getCountry(){

        return this.country;
    }

    public String getIngredientsString(){
        String result="";
        for(int i =0;i<ingredientList.size() ;i++){
            result = result+ingredientList.get(i).name +" with quantity of "+ingredientList.get(i).quantity+"/ ";
        }
        return result;
    }
    public String toString(){
        return "recipe name:"+title+", type: "+category+", In this recipe, ingredients are "+getIngredientsString();
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
