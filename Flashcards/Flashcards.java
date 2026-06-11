package Flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Flashcards {
    //FlashCards lists
    ArrayList<Map<String, String>> flashcardsList;

    //FlashCards
    Map<String, String> flashCards;

    //Contains list of questions user remembers and doesn't need to go through them again.
    ArrayList<Map<String, String>> skippedFlashCards;

    int totalStartingAmount;

    int questionNumber;

    private static final String COMMA_DELIMITER = ",";

    

    //Non-provided list of cards
    public Flashcards(){
        this.flashcardsList = new ArrayList<>();
        this.flashCards = new HashMap<>();
        this.skippedFlashCards = new ArrayList<>();
        this.totalStartingAmount = 0;
        this.questionNumber = 0;
    }

    //Provided list of cards
    public Flashcards(Map<String, String> questionList){
        flashCards = questionList;
        this.flashcardsList = new ArrayList<>();
        for(Map.Entry<String, String> card : questionList.entrySet()){
            Map<String, String> temp = new HashMap<>();
            temp.put(card.getKey(), card.getValue());
            this.flashcardsList.add(temp);
        }
        this.skippedFlashCards = new ArrayList<>();
        this.totalStartingAmount = flashcardsList.size();
        this.questionNumber = 0;
    }

    //Import from csv document
    public void importFile(File file){
        System.out.println("Importing from " + file.getName() + "...");
        
        try(Scanner reader = new Scanner(file)){
            while(reader.hasNextLine()){
                Map<String, String> importedQuestions = new HashMap<>();
                String line = reader.nextLine();
                String[] values = line.split(COMMA_DELIMITER);
                // System.out.println(values[0]);
                importedQuestions.put(values[0], values[1]);
                this.flashCards.putAll(importedQuestions);
                this.flashcardsList.add(importedQuestions);
            }
            this.totalStartingAmount = flashcardsList.size();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Import finished!");
    }

    //Get the value of a question asked
    public void flip(String question){
        System.out.println("The answer is: " + flashCards.get(question) + "\n");
    }

    //Add to list of remembered cards
    //"question" is the key
    public void remembered(String question){
        if(!flashCards.containsKey(question)){
            System.out.println("This question does not exist, please try again...");
            return;
        }
        Map<String, String> temp = new HashMap<>();
        temp.put(question, flashCards.get(question));
        flashcardsList.remove(temp);
        skippedFlashCards.add(temp);
        flashCards.remove(question);
        System.out.println("Card was successfully added to your list of remembered cards");
    }

    public void total(ArrayList<Map<String, String>> list){
        System.out.println("You have: " + list.size() + " left.");
    }

    public void play(){
        
        Scanner scanner = new Scanner(System.in);
        while(true){

            //If Flashcard set is empty, end the game
            if(flashcardsList.isEmpty()){
                System.out.println("You have completed your flashcards set!");
                scanner.close();
                return;
            }

            //If reached to the last question of flashcard set
            //Reset back to the beginning question
            if(questionNumber >= flashcardsList.size()){
                questionNumber = 0;
                System.out.println("Question Number = " + questionNumber);
            }


            Map.Entry<String, String> card = flashcardsList.get(questionNumber).entrySet().iterator().next();
            System.out.println("Question " + (questionNumber + 1) + ": " + card.getKey());
            System.out.println("""
                    [1] Flip Card
                    [2] Remember
                    [3] Skip
                    [4] Total Cards left
                    [5] Quit
                    """);

            //Wait for user response (Can only be integers 1-4)
            int ans = scanner.nextInt();

            switch(ans){
                case 1 -> {
                    flip(card.getKey());
                }
                case 2 -> { 
                    remembered(card.getKey()); 
                    questionNumber++;
                }
                case 3 -> {
                    questionNumber++;
                }
                case 4 -> { System.out.println("You have " + this.flashcardsList.size() + " cards left."); }
                case 5 -> { 
                    System.out.println("You have completed: " + this.skippedFlashCards.size() + "/" + this.totalStartingAmount + " cards! Thanks for playing!");
                    scanner.close();
                    return; 
                }
                default -> { System.out.println("Please choose a response 1-4..."); }
            }
        }
    }


}
