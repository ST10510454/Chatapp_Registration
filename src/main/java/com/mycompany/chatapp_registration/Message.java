/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp_registration;
import java.util.Random;
/**
 *
 * @author Solomon Koketso Senoamadi
 * Part 2 - Message Test
 * Part 3 - Added arrays and new methods
 */
public class Message {
    
    //Variables that holds the details of one message
    String messageID;
    String recipient;
    String messageText;
    String messageHash;
    String messageStatus;
    
    //keeps track of how many messagges were sent
    static int totalMessages = 0;
    
    //Arrays to store messages and details
    //"100" was used as the size
    static String[] sentMessages = new String[100];
    static String[] sentRecipients = new String[100];
    static String[] sentMessageIDs = new String[100];
    static String[]sentMessageHashes = new String[100];
    static int sentCount = 0;
    
    //Arrays to store messages that were stored for later
    static String[] storedMessages = new String[100];
    static String[] storedRecipients = new String[100];
    static String[] storedMessageIDs = new String[100];
    static String[] storedMessageHashes = new String[100];
    static int storedCount = 0;
    
    //this array will keep the messages that were disregarded
    static String[] disregardedMessages = new String[100];
    static int disregardedCount = 0;
    
    
    //Reference: Random number generation in Java
    //Source: Oracle Java Documentation, java.util.Random
    //URL: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    //(Accessed: April 2026)
    //method makes a random 10 digit number to use as the message ID
    public String generateMessageID(){
        Random random = new Random();
        int id = random.nextInt(900000000) + 100000000;
        messageID = String.valueOf(id);
        return messageID;
    }
    //method: to check the message ID isn't more tha 10 charaacters
    //returns true if valid and false if not
    public boolean checkMessageID(){
        if (messageID.length()<=10){
            return true;
        }else{
            return false;
        }
    }
    /*
    *method: to check the recipient number if it starts with +
    *and if it isnt more than 10 characters after the code
    *returns a success or error message
    */
    public String checkRecipientCell(String cell){
        if(cell.matches("^\\+[0-9]{11}$")){
            recipient = cell;
            return "Cell phone number success fully captured.";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again";
        }
    }
    // creates a unique hash for each message
    public String createMessageHash(String message, int messageNumber){
        
        //gets the first two characters of the message ID
        String firstTwo = messageID.substring(0,2);
        
        //breaks the message into seperate words using spaces
        String[]words = message.trim().split(" ");
        
        //first word is easy to get
        String firstWord = words[0];
        
        //last word is the last item in the array
        String lastWord = words[words.length-1];
        
        
        char lastChar = lastWord.charAt(lastWord.length() - 1);
        if (!Character.isLetter(lastChar)) {
            lastWord = lastWord.substring(0, lastWord.length() - 1);
        }    
        //will put it together and make it an uppercase
        messageHash = (firstTwo+lastWord).toUpperCase();
        return messageHash;
    }
    
    //method handles what happens after the user writes a message
    //user chooses to send, stor or to disregard the message
    public String sentMessage(int choice){
        if(choice == 1){
            //saves the messages into the sent Arrays
            sentMessages[sentCount] = messageText;
            sentRecipients[sentCount] = recipient;
            sentMessageIDs[sentCount] = messageID;
            sentCount++;
            totalMessages++;
            messageStatus = "Srnt";
            return "Message successfully sent.";
        }else if (choice == 2){
            //saves the message into the disregarded array
            disregardedMessages[disregardedCount] = messageText;
            disregardedCount++;
            messageStatus = "Disregarded";
            return "Press 0 to delete the message";
        }else if(choice == 3){
            //saves the message into the stored arrays
            storedMessages[storedCount] = messageText;
            storedRecipients[storedCount] = recipient;
            storedMessageIDs[storedCount]= messageID;
            storedMessageHashes[storedCount] = messageHash;
            storedCount++;
            messageStatus = "Stored";
            return "Message successfully stored.";
        }else{
            return "Invalid choice.";
        }
    }
    //method returns the total number of messages that were sent
    public String printMessages(){
        return "Messsage ID: " + messageID + "\nMessage Hash" + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
                
    }
    
    public int returnTotalMessages(){
        return totalMessages;
    }
    
    //Refernce: writing to files in Java using FileWriter
    //source: Oracle Java Documentation,java.io.FileWriter.
    //URL: https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
    //(Accessed: April 2026)
    public void storeMessage(){
        try {
            java.io.FileWriter file = new java.io.FileWriter("stored_messages.json", true);
            
            String json = "{\n" 
                    + " \"messageID\": \"" + messageID + "\",\n" 
                    + " \"recipient\": \"" + recipient + "\",\n"
                    + " \"message\": \"" + messageText + "\",\n"
                    + " \"messageHash\": \"" + messageHash + "\"\n"
                    + "},\n";
                    
                    file.write(json);
                    file.close();
                    
        } catch (Exception e){
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    //mrthod shows all the messages that were sent
    public static void displaySentMessages(){
        if(sentCount == 0){
            System.out.println("Messages wasnt sent.");
        }else{
            System.out.println("---- Messages were sent ----");
            for(int i = 0; i < sentCount; i++){
                System.out.println("Recipient: "+ sentRecipients[i]);
                System.out.println("Message: "+ sentMessages[i]);
                System.out.println(" ");
            }
        }
    }
    
    public static void displayLongestMessage(){
        if(storedCount == 0){
            System.out.println("No stored messages.");
            return;
        }
        
        String longest = storedMessages[0];
        
        for (int i = 1; i < storedCount; i++){
            if(storedMessages[i].length()> longest.length()){
                longest = storedMessages[i];
            }
        }
        System.out.println("Longest message: "+ longest);
    }
    
    public static void searchMessageID(String searchID){
        boolean found = false;
        
        for (int i = 0; i < sentCount; i++){
            if(sentMessageIDs[i].equals(searchID)){
                System.out.println("Recipient: "+ storedRecipients[i]);
                System.out.println("Message: "+ sentMessages[i]);
                found = true;
            }
        }
        
        for(int i = 0; i < storedCount; i++){
            if(storedMessageIDs[i].equals(searchID)){
                System.out.println("Recipient: "+ storedRecipients[i]);
            System.out.println("Message: "+ storedMessages[i]);
            found = true;
            }
        }
        if (!found){
            System.out.println("No message found with that ID");
        }
    }
    
    public static void searchByRecipient(String searchRecipient){
        boolean found = false;
        
        for(int i = 0; i < sentCount;i++){
            if(sentRecipients[i].equals(searchRecipient)){
                System.out.println("Message: "+ sentMessages[i]);
                found = true;
            }
        }
        
        for (int i = 0; i<storedCount; i++){
            if (storedRecipients[i].equals(searchRecipient)){
                System.out.println("Message: "+ storedMessages[i]);
                found= true;
            }
        }
        
        if(!found){
            System.out.println("No messages found for that recipient.");
        }
    }
    
    public static void deleteByHash(String hash){
        boolean found = false;
        
        for (int i = 0; i < storedCount; i++){
            if(storedMessageHashes[i].equals(hash)){
                String deletedMessage = storedMessages[i];
                found = true;
                
                for(int j = i; j < storedCount - 1; j++){
                    storedMessages[j] = storedMessages[j + 1];
                    storedRecipients[j] = storedRecipients[j + 1];
                    storedMessageIDs[j] = storedMessageIDs[j + 1];
                    storedMessageHashes[j] = storedMessageHashes[j + 1];
                }
                
                storedMessages[storedCount - 1] = null;
                storedRecipients[storedCount - 1] = null;
                storedMessageIDs[storedCount - 1] = null;
                storedMessageHashes[storedCount - 1] = null;
                storedCount--;
                
                System.out.println("Message: '"+ deletedMessage + "' successessfully deleted.");
                break;
            }
        }
        
        if(!found) {
            System.out.println("No message found with that hash");
        }
    }
    
    public static void displayFullReport(){
        if(storedCount == 0){
            System.out.println("No stored messages to display.");
            return;
        }
        
        System.out.println("----Full Stored Messages Report----");
        for (int i = 0; i < storedCount; i++){
        System.out.println("Message ID :"+ storedMessageIDs[i]);
            System.out.println("Message Hash: "+ storedMessageHashes[i]);
            System.out.println("Recipients: "+ storedRecipients[i]);
            System.out.println("Message: "+ storedMessages[i]);
            System.out.println(" ");
         }
    }
}
