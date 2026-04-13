/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp_registration;
import java.util.Scanner;
/**
 *
 * @author Solomon Koketso Senoamadi
 */
public class Chatapp_Registration {

    public static void main(String[] args) {
        //Scanner
        Scanner input = new Scanner(System.in);
        
        Chatapp_Login login = new Chatapp_Login();
        
        //Welcome message
        System.out.println("Welcome to the Chatapp");
        
        System.out.println("----------------------");
        //User must enter their first and last name
        System.out.println("Please enter First Name: ");
        login.firstName = input.nextLine();
        System.out.println("Please enter Last Name");
        login.lastName = input.nextLine();
        //Registration Section
        System.out.println("\n---- Register ----");
        //User must enter a username 
        System.out.println("Please Enter a username: ");
        String username = input.nextLine();
       //User must enter a strong password
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        //User must enter cell phone number
        System.out.println("Enter cell number (e.g. +27825881182): ");
        String cellNumber =input.nextLine();
        //Registration will run if results were filled correctly
        String result = login.registerUser(username, password,cellNumber);
        System.out.println("\n"+ result);
        //Registration will stop if results were filled incorrectly
        if (!login.checkUserName(username) || !login.checkPasswordComplexity(password) || !login.checkCellPhoneNumber(cellNumber)){
        System.out.println("Registration failed. Please try again.");
        return;
    }
        //Login Section
        System.out.println("\n--- Login ---");
        //User must enter username
        System.out.println("Enter username: ");
        String loginUsername =input.nextLine();
        //User must enter password
        System.out.println("Enter Password: ");
        String loginPassword = input.nextLine();
        //Print the login result message
        System.out.println("\n" + login.returnLoginStatus(loginUsername, loginPassword));
        
        //Scanner closes
        input.close();
    }
}
