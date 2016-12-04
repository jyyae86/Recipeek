package jackiechans.recipeek;

import java.util.Arrays;

/**
 * Created by Joshua on 2016-12-03.
 */

public class SearchAlgorithim {
    //parses search string
    String search;
    String delim = "^&-";
    Boolean[] or, and, not;

    public void SearchAlgorithim(String s){
        search = s.toLowerCase();
        int counter = 0;

        searchParse(search, counter);
    }

    private void searchParse(String s, int x){

        String[] token;

        if (x <3) {
            token = s.split(delim.substring(x, x + 1));


            for (int i = 0; i < token.length; i++) {
                x++;
                searchParse(token[i], x);
                x--;
            }

            if (x==2){
                for (int i = 0; i < token.length; i++) {

                }
            }
            else if (x==1){
                for (int i = 0; i < token.length; i++) {

                }
            }
            else if (x==0){
                for (int i = 0; i < token.length; i++) {

                }
            }
        }



    }

}
