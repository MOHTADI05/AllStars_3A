/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chaker
 */
public class BadWordsFilter {
    
      // Define a list of bad words
    private static final List<String> BAD_WORDS = new ArrayList<>();

    static {
        BAD_WORDS.add("fuck");
        BAD_WORDS.add("kill");
        BAD_WORDS.add("asshole");
       
    }


    public static boolean containsBadWords(String input) {
        for (String badWord : BAD_WORDS) {
            if (input.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }
    
    
    
}
