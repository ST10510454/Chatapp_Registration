/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp_registration;

import com.mycompany.chatapp_registration.Chatapp_Login;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solomon Koketso Senoamadi
 */
public class Chatapp_Logintest {
    //Login object
    Chatapp_Login login;
    
    @Before
    public void setup(){
         login = new Chatapp_Login();
         login.firstName = "Test";
         login.lastName = "User";
    }
    
    
    @Test
    public void testUsernameCorrect(){
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27697232430");
        
        assertTrue(result.contains("Username successfully captured."));
    }
    
    @Test
    public void testUsernameIncorrect(){
        String result = login.registerUser("kyle!!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        
        assertTrue(result.contains("Username is not correctly formatted; please ensure that "
                + " your username contains an underscore and is no more than five characters in length."));
    }
    
    @Test
    public void testPasswordCorrect() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27697232430");
        
        assertTrue(result.contains("Password successfully captured."));
    }
    
    @Test
    public void testPasswordIncorrect() {
        String result = login.registerUser("kyl_1", "password", "+27697232430");
        
        assertTrue(result.contains("Password is not correctly formatted; please ensure that"
                + "the password contains at least eight characters, a capital letter, a number, and a special character"));
    }
    //assertTrue/assertFalse
    @Test
    public void testCellNumberCorrect(){
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27697232430");
        
        assertTrue(result.contains("Cell number successfully added."));
    }
    
    @Test
    public void testCellNumberIncorrect(){
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "0697232430");
        assertTrue(result.contains("Cell number is incorrectly formatted or does not contain an international code."));
    }
    
    @Test
    public void testLoginSuccess(){
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27697232430");
        
        assertTrue(login.loginUser("kyl_1", "Ka&&zco@ig99!"));
    }
    
    @Test
    public void testLoginFail(){
        login.registerUser("kyl_1", "Ka&&zco@ig99!", "+27697232430");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
    
    
    @Test
    public void testUsernameValidTrue(){
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testUsernameValidFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!!"));
    }
    
    @Test
    public void testPasswordValidTrue() {
        assertTrue(login.checkPasswordComplexity("Ka&&zco@ig99!"));
    }
    
    @Test
    public void testPasswordValidFalse(){
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCellValidTrue() {
        assertTrue(login.checkCellPhoneNumber("+27697232430"));
    }
    
    @Test
    public void testCellValidFalse(){
        assertFalse(login.checkCellPhoneNumber("0697232430"));
    }
}
