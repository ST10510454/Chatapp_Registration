/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp_registration;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Chatapp_Registration {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Chatapp_Login login = new Chatapp_Login();
        
        System.out.println("Welcome to the Chatapp");
        
        System.out.println("----------------------");
        System.out.println("Please enter First Name: ");
        login.firstName = input.nextLine();
        System.out.println("Please enter Last Name");
        login.lastName = input.nextLine();
        
        System.out.println("\n---- Register ----");
        
        System.out.println("Please Enter a username: ");
        String username = input.nextLine();
       
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        
        System.out.println("Enter cell number (e.g. +27825881182): ");
        String cellNumber =input.nextLine();
        
        String result = login.registerUser(username, password,cellNumber);
        System.out.println("\n"+ result);
        
        if (!login.checkUserName(username) || !login.checkPasswordComplexity(password) || !login.checkCellPhoneNumber(cellNumber)){
        System.out.println("Registration failed. Please try again.");
        return;
    }
        
        System.out.println("\n--- Login ---");
        
        System.out.println("Enter username: ");
        String loginUsername =input.nextLine();
        
        System.out.println("Enter Password: ");
        String loginPassword = input.nextLine();
        
        System.out.println("\n" + login.returnLoginStatus(loginUsername, loginPassword));
        
    }
}
