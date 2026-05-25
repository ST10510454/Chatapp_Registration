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
 */
public class Message {
    
    //Variables
    String messageID;
    String recipient;
    String messageText;
    String messageHash;
    String messageStatus;
    
    //how many messagges were sent
    static int totalMessages = 0;
    
    //Reference: Random number generation in Java
    //Source: Oracle Java Documentation, java.util.Random
    //URL: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
    //(Accessed: April 2026)
    public String generateMessageID(){
        Random random = new Random();
        long id = (long)(random.nextDouble()* 9000000000L) + 1000000000L;
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
        
        
        String firstTwo = messageID.substring(0,2);
        
        
        String[]words = message.trim().split(" ");
        
        
        String firstWord = words[0];
        
        
        String lastWord = words[words.length-1];
        
        
        lastWord = lastWord.replaceAll("[a-zA-Z0-9]", "");
        
        
        messageHash = (firstTwo+lastWord).toUpperCase();
        return messageHash;
    }
    
    
    //user chooses to send, stor or to disregard the message
    public String sentMessage(int choice){
        if(choice == 1){
            totalMessages++;
            messageStatus = "Srnt";
            return "Message successfully sent.";
        }else if (choice == 1){
            messageStatus = "Disregarded";
            return "Press 0 to delete the message";
        }else if(choice == 3){
            messageStatus = "Stored";
            return "Message successfully stored.";
        }else{
            return "Invalid choice.";
        }
    }
    
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
}
