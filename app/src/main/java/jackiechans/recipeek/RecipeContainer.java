package jackiechans.recipeek;

import java.io.Serializable;

/**
 * Created by Rawaz on 2016-12-04.
 */


public class RecipeContainer implements Serializable{

    private static RecipeNode first;
    private static RecipeNode last;
    private static int size;

    public int getSize(){
        return size;
    }

    public boolean addRecipe(Recipe newRecipe){
        RecipeNode newNode = new RecipeNode(newRecipe);
        if(first == null){
            first = newNode;
            last = newNode;
            size++;
            return true;
        }
        else{
            last.setNext(newNode);
            last = last.getNext();
            size++;
            return true;
        }
    }

    public boolean deleteRecipe(int index){
        if(first == null){
            return false;
        }
        else{
            if(index+1>size){
                return false;
            }
            else{
                RecipeNode tempNode = first;
                int i = 0;
                while(i<index-1){
                    tempNode = tempNode.getNext();
                    i++;
                }
                RecipeNode jump = tempNode.getNext().getNext();
                tempNode.setNext(jump);
                size--;
            }
        }
        return true;
    }


    public class RecipeNode{
        private RecipeNode next;
        private Recipe recipe;

        public RecipeNode(Recipe recipe){
            this.recipe = recipe;
            next = null;
        }

        public RecipeNode(){
            recipe = null;
            next = null;
        }

        public Recipe getRecipe(){
            return recipe;
        }

        public RecipeNode getNext(){
            return next;
        }

        public void setRecipe(Recipe newRecipe){
            recipe = newRecipe;
        }

        public void setNext(RecipeNode newNode){
            next = newNode;
        }
    }
}
