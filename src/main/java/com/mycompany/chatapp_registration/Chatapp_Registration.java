/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp_registration;
import java.util.Scanner;
/**
 *
 * @author Solomon Koketso Senoamadi
 * Added messaging menu for Part 2
 * Part 2 complete - registration, login and messaging
 * Part 3 complete
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
        if (!login.loginUser(loginUsername, loginPassword)){
            System.out.println("Login failed. Existing.");
            return;
            
        }
        System.out.print("How many messages would you like to send? ");
        int maxMessages = Integer.parseInt(input.nextLine());
        
        int messagesEntered = 0;
        boolean running = true;
        
        while(running){
            System.out.println("\n--- Chatapp Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Choose an option:");
            
            int menuChoice = Integer.parseInt(input.nextLine());
            if (menuChoice == 1){
                if (messagesEntered >= maxMessages){
                    System.out.println("You have reached your message limit of " + maxMessages + ".");
                }else{
                    Message msg = new Message();
                    msg.generateMessageID();
                    System.out.println("Message ID generrated: " + msg.messageID);
                    
                    System.out.println("Enter recipient cell number(example: ++27825881182): ");
                    String recipientCell = input.nextLine();
                    System.out.println(msg.checkRecipientCell(recipientCell));
                    
                    if (!recipientCell.matches("^\\+[0-9]{11}$")){
                        System.out.println("Message cancelled.");
                    }else{
                        System.out.println("Enter your message(max 250 characters): ");
                        String messageText = input.nextLine();
                        
                        if (messageText.length()>250){
                            int over = messageText.length()-250;
                            System.out.println("Message exceeds 250 characters by "+ over + "; please reduce the size");
                        }else{
                            msg.messageText = messageText;
                            System.out.println("Message Hash: " + msg.createMessageHash(messageText, messagesEntered));
                            
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");
                            System.out.println("Choose an option: ");
                            
                            int sendChoice = Integer.parseInt(input.nextLine());
                            System.out.println(msg.sentMessage(sendChoice));
                            
                            if (sendChoice == 3){
                                msg.storeMessage();
                            }
                            if (sendChoice == 1 || sendChoice == 3){
                                System.out.println(msg.printMessages()     );
                            }
                            messagesEntered++;
                        }
                    }
                }
            }else if(menuChoice == 2){
                System.out.println("Coming Soon.");
            }else if(menuChoice == 3){
                System.out.println("Total messages sent: " + Message.totalMessages);
                System.out.println("Goodbye.");
                running = false;
                
                //Part 3 - added stored messages menu option
            }else if(menuChoice == 4){
                
                
                System.out.println("\n---- Stored Messages Menu ----");
                System.out.println("1) Display all sent message senders and recipients");
                System.out.println("2) Display longest stored message");
                System.out.println("3) Search for a message by ID");
                System.out.println("4) Search all message using hash");
                System.out.println("5) Delete a message using hash");
                System.out.println("Choose an option: ");
                
                int subChoice = Integer.parseInt(input.nextLine());
                
                if (subChoice == 1){
                    Message.displaySentMessages();
                    
                }else if (subChoice == 2){
                    Message.displayLongestMessage();
                    
                }else if (subChoice == 3){
                    System.out.println("Enter the message ID to search: ");
                    String searchID = input.nextLine();
                    Message.searchMessageID(searchID);
                    
                }else if (subChoice == 4){
                    System.out.println("Enter the recipient number to search: ");
                    String searchRecipient = input.nextLine();
                    Message.searchByRecipient(searchRecipient);
                    
                }else if (subChoice == 5){
                    System.out.println("Enter the message hash to delete: ");
                    String deleteHash = input.nextLine();
                    Message.deleteByHash(deleteHash);
                    
                }else if (subChoice == 6){
                    Message.displayFullReport();
                    
                }else{
                    System.out.println("Invalid Option.");
                    
                }
                
            }else{
                System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }
        input.close();
    }
}

