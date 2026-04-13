/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp_registration;

/**
 *
 * @author Solomon Koketso Senoamadi
 */
public class Chatapp_Login {
    //Variables
    String username;
    String password;
    String cellNumber;
    String firstName;
    String lastName;
    
    //Method
  boolean checkUserName(String username){
      
      if(username.length() <=5 && username.contains("_")){
          return true;
    }else{
          return false;
    }      
  }
    //Method
  boolean checkPasswordComplexity(String password){
     if(password.length() <8){
         return false;
    }
  
  boolean hasCapital = false;
  boolean hasNumber = false;
  boolean hasSpecialCharacters = false;
 
  for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);
  
    if (Character.isUpperCase(c)) {
        hasCapital = true;
    }
    if (Character.isDigit(c)){
        hasNumber = true;
}
  if(!Character.isLetterOrDigit(c)){
    hasSpecialCharacters = true;
    } 
}
  if (hasCapital && hasNumber && hasSpecialCharacters){
  return true;
}else{
  return false;
  }
  
    }
    //Regex pattern sourced from general community knowledw on phone number validation
    //Reference: Stack Overflow, 2024. Regex phone number validation. [online]
    //Available at: <https://stackoverflow.com/questions/tagged/regex+phone-number>
    //[Accessed 9 April 2026]
  
  boolean checkCellPhoneNumber(String cellNumber){
      if (cellNumber.matches("^\\+[0-9]{11}$")){
          return true;
      }else{
          return false;
      }
    }
    
    String registerUser(String username, String password, String cellNumber){
        //Variables
        String usernameMessage;
        String passwordMessage;
        String cellNumberMessage;
        
        if(checkUserName(username)){
            usernameMessage = "Username successfully captured.";
            this.username = username;
        }else{
            usernameMessage = "Username is not correctly formatted; please ensure that your username contains an underscore and  is no more than five characters in length.";
        }
        if(checkPasswordComplexity(password)){
            passwordMessage = "Password successfully captured.";
            this.password = password;
        }else{
            passwordMessage = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character";
        }
        
        if (checkCellPhoneNumber(cellNumber)){
            cellNumberMessage = "Cell number successfully captured.";
            this.cellNumber = cellNumber;
        }else{
            cellNumberMessage="Cell number incorrectly formatted or does not contain international code; please correct the number and try again.";
        }
        
        return usernameMessage + "\n" + passwordMessage + "\n" + cellNumberMessage;
      
 }
    //Method
    boolean loginUser(String username, String password){
        if ((this.username.equals(username) && this.password.equals(password))){
            return true;
        }else{
            return false;
        }
    }
    //Method
    public String returnLoginStatus(String username, String password){
        if (loginUser(username, password)){
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        }else{
            return "Username or password incorrect, please try again.";
        }
    }
 }
       