package jackiechans.recipeek;

/**
 * Created by jacky on 2016-12-04.
 */

public class Ingredient {
        public String name;
        public String quantity;

        public Ingredient(String name, String quantity){
            this.name = name;
            this.quantity = quantity;

        }
        public String getName(){
            return this.name;
        }
        public String getQuantity(){
            return this.quantity;
        }
        public String toString(){
            return this.name+" with quantity of "+quantity;
        }
    }

