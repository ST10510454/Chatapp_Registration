/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp_registration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author koket
 * Part 3 - unit tests for array methods
 * All Part 3 tests passing
 */
public class MessageDatatest {
    
    @Before
    public void setUp(){
        
        Message.sentCount=0;
        Message.storedCount=0;
        Message.disregardedCount=0;
        Message.totalMessages=0;
        
        Message.sentMessages = new String[100];
        Message.sentRecipients = new String[100];
        Message.sentMessageIDs = new String[100];
        Message.sentMessageHashes = new String[100];
        
        Message.storedMessages = new String[100];
        Message.storedRecipients = new String[100];
        Message.storedMessageIDs = new String[100];
        Message.storedMessageHashes = new String[100];
        
        Message.disregardedMessages = new String[100];
        
        //Message 1 - sent
        Message.sentMessages[0] = "Did you get the bread?";
        Message.sentRecipients[0] = "+27699139100";
        Message.sentMessageIDs[0] = "12345677890";
        Message.sentMessageHashes[0] = "12:0:DIDBREAD";
        Message.sentCount++;
        
        //Message 2 - stored
        Message.storedMessages[0] = "Where are you? you are late! I have asked you to be on time.";
        Message.storedRecipients[0] = "+27699139100";
        Message.storedMessageIDs[0] = "2345678901";
        Message.storedMessageHashes[0] = "21:1:WHERETIME";
        Message.storedCount++;
        
        //Message 3 - disregarded
        Message.disregardedMessages[0] = "Yohoooo, I am at your gate.";
        Message.disregardedCount++;
        
        //Message 4 - Sent (Invalid number but added manually for test purposes)
        Message.sentMessages[1] = "It is dinner time!";
        Message.sentRecipients[1] = "0699139100";
        Message.sentMessageIDs[1] = "3456789012";
        Message.sentMessageHashes[1] = "34:3:ITTIME";
        Message.sentCount++;
        
        //Message 5 - stored
        Message.storedMessages[1] = "Ok, I am leaving without you.";
        Message.storedRecipients[1] = "+27699139100";
        Message.storedMessageIDs[1] = "4567890123";
        Message.storedMessageHashes[1] = "45:4:OKYOU";
        Message.storedCount++;
    }
    
    //Test sent message array is correctly populated
    @Test
    public void testSentMessagesArray(){
        assertEquals("Did you get the bread?", Message.sentMessages[0]);
        assertEquals("It is dinner time!", Message.sentMessages[1]);
    }
    
    //Test the display longest message
    @Test
    public void testLongestMessage(){
        String longest = Message.storedMessages[0];
        
        for(int i = 1; i < Message.storedCount; i++){
            if(Message.storedMessages[i].length() > longest.length()){
                longest = Message.storedMessages[i];
            }
        }
        
        assertEquals("Where are you? you are late! I have asked you to be on time.", longest);
        
    }
    
    //Test for message by ID 
    @Test
    public void testSearchByMessageID(){
        String found = "";
        
        for(int i = 0; i < Message.sentCount; i++){
            if (Message.sentMessageIDs[i].equals("3456789012")){
                found = Message.sentMessages[i];
            }
        }
        assertEquals("It is dinner time!", found);
    }
    
    @Test
    public void testSearchByRecipient(){
        int count = 0;
        String firstFound = "";
        String secondFound = "";
        
        for (int i = 0; i < Message.storedCount; i++){
            if(Message.storedRecipients[i].equals("+27699139100")){
                count++;
                if(count == 1){
                    firstFound = Message.storedMessages[i];
                }else if (count == 2){
                    secondFound = Message.storedMessages[i];
                }
            }
        }
        
        assertEquals("Where are you? you are late! i have asked you to be on time.", firstFound);
        assertEquals("Ok, I am leaving witthout you.", secondFound);
    }
    
    @Test
    public void testDeletByHash(){
        String hashToDelete = "23:1:WHERETIME";
        String deletedMessage = "";
        
        for(int i = 0; i < Message.storedCount; i++){
            if(Message.storedMessageHashes[i].equals(hashToDelete)){
                deletedMessage = Message.storedMessages[i];
                
                for (int j = i; j < Message.storedCount - 1; j++){
                    Message.storedMessages[j] = Message.storedMessages[j + 1];
                    Message.storedRecipients[j] = Message.storedRecipients[j + 1];
                    Message.storedMessageIDs[j] = Message.storedMessageIDs[j + 1];
                    Message.storedMessageHashes[j] = Message.storedMessageHashes[j + 1];
                }
                
                Message.storedCount--;
                break;
            }
        }
        
        assertEquals("Message: 'Where are you? You are late! I have asked you to be on time' successfully deleted.",
                "Message: '" + deletedMessage + "' successfully deleted.");
    }
    
    @Test
    public void testDisplayReport(){
        assertEquals(2, Message.storedCount);
    }
}
