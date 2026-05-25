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
 * @author Solomon Koketso Senoamadi
 * Part 2 - Message Tests
 */
public class Messagetest {
    
    Message msg;
    
    
    @Before
    public void setUp(){
        msg = new Message();
        msg.generateMessageID();
        Message.totalMessages = 0;
    }
    
    @Test
    public void testMessageLength_Valid(){
        String testMessage = "Hi Joe, can you join us for dinner tonight?";
        String actual;
        if(testMessage.length()<=250){
            actual = "Message ready to send.";
        }else{
            int over = testMessage.length()-250;
            actual = "Message exceeds 250 characters by " + "; please reduce the size.";
        }
        
  
    }
      
    @Test
    public void testMessageLength_Invalid(){
        
        String testMessage = "This message is very long and goes over the limit. "
                + "It keeps going and going and going and going and going and going"
                + "and going anf going and going and going and going and going"
                + "and going and going. Done.";
        
        String actual;
        if(testMessage.length()<=250){
            actual = "Messages ready to send.";
        }else{
            int over = testMessage.length()-250;
            actual = "Message exceeds 250 characters by " + over + "; please reduce the size";
        }
        assertTrue(actual.contains("Message exceeds 250 characters by"));
    }
    
    @Test
    public void testRecipientCell_Valid(){
        String result = msg.checkRecipientCell("+27838968976");
        assertEquals("Cell number successfully captured.", result);
    }
    
    
    @Test
    public void testRecipientCell_Invalid(){
        String result = msg.checkRecipientCell("0697232430");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
        
    }
    
    @Test
    public void testMessageHash_Correct(){
        
        msg.messageID = "0012345678";
        String hash = msg.createMessageHash("Hi Joe, can you join us for dinner tonight?", 0);
        assertEquals("00:HITONIGHT", hash);
    }
    
    @Test
    public void testMessageID_Created(){
        msg.generateMessageID();
        assertTrue(msg.checkMessageID());
    }
    
    @Test
    public void testSentMessage_Send(){
        assertEquals("Message successfully sent", msg.sentMessage(1));
    }
    
    @Test
    public void testSentMessage_Disregard(){
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }
    
    @Test
    public void testSentMessage_Store(){
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}
