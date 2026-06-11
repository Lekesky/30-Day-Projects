package Flashcards;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        //Test via HashMap
        // Map<String, String> flashcards = new HashMap<>();
        // flashcards.put("What is the capital of France", "Paris");
        // flashcards.put("What is 2+2", "4");
        // flashcards.put("What does CPU stand for", "Central Processing Unit");
        // flashcards.put("Who wrote Hamlet", "Shakespeare");
        // flashcards.put("What is the largest planet", "Jupiter");
        // flashcards.put("What language is primarily spoken in Brazil", "Portuguese");
        // flashcards.put("What is H2O commonly called", "Water");
        // flashcards.put("What color do you get mixing blue and yellow", "Green");
        // flashcards.put("What year did the first moon landing occur", "1969");
        // flashcards.put("What is the chemical symbol for gold", "Au");
        
        //Test via File import
        Flashcards fc = new Flashcards();
        fc.importFile(new File("Flashcards//flashcards.csv"));
        fc.play();
    }
}
