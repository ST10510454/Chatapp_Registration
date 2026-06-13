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
    
    //Method checks if username is valid
  boolean checkUserName(String username){
      
      if(username.length() <=5 && username.contains("_")){
          return true;
    }else{
          return false;
    }      
  }
    //Method checks if password is strong enough
  boolean checkPasswordComplexity(String password){
      
      //this will check the length
     if(password.length() <8){
         return false;
    }
  // these flags start as false and get set to true when requirements are reached
  boolean hasCapital = false;
  boolean hasNumber = false;
  boolean hasSpecialCharacters = false;
 
  //goes through every character and check what it is
  for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);
  
    if (Character.isUpperCase(c)) {
        hasCapital = true;
    }
    if (Character.isDigit(c)){
        hasNumber = true;
}
    //a special character is anything that is not a letter or number
  if(!Character.isLetterOrDigit(c)){
    hasSpecialCharacters = true;
    } 
}
  // all three must be true for the paasword to pass
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
  
  //method checks if cell phone number is valid
  boolean checkCellPhoneNumber(String cellNumber){
      if (cellNumber.matches("^\\+[0-9]{11}$")){
          return true;
      }else{
          return false;
      }
    }
    //method rruns all three checks and returms result messages
    String registerUser(String username, String password, String cellNumber){
        //Variables that hwill hold the message for each check
        String usernameMessage;
        String passwordMessage;
        String cellNumberMessage;
        
        // checks the username and save it if it is valid
        if(checkUserName(username)){
            usernameMessage = "Username successfully captured.";
            this.username = username;
        }else{
            usernameMessage = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        //check the password and saves it if its valid
        if(checkPasswordComplexity(password)){
            passwordMessage = "Password successfully captured.";
            this.password = password;
        }else{
            passwordMessage = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character";
        }
        //checks cell phone number and saves it if it is valid
        if (checkCellPhoneNumber(cellNumber)){
            cellNumberMessage = "Cell number successfully captured.";
            this.cellNumber = cellNumber;
        }else{
            cellNumberMessage="Cell number incorrectly formatted or does not contain international code; please correct the number and try again.";
        }
        // returns all three messages together on separate lines
        return usernameMessage + "\n" + passwordMessage + "\n" + cellNumberMessage;
      
 }
    //Method checks if the login details match what was registered
    //returns true if the match and false if they don't
    boolean loginUser(String username, String password){
        if ((this.username.equals(username) && this.password.equals(password))){
            return true;
        }else{
            return false;
        }
    }
    //Method returns the right message depending if the login eas successfull or not
    public String returnLoginStatus(String username, String password){
        if (loginUser(username, password)){
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        }else{
            return "Username or password incorrect, please try again.";
        }
    }
 }
       