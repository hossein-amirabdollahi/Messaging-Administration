package com.administration.menu;

import com.administration.exceptions.BadInputException;
import com.administration.exceptions.ExistenceException;
import com.administration.messaging.MessageManager;
import com.administration.messaging.MessageObj;

import java.util.Scanner;
import java.util.Set;

public class MainMenu {
    private final MessageManager messageManager = new MessageManager();
    private final Scanner scanner = new Scanner(System.in);

    {
        messageManager.addDefaultUsers();
    }

    public void funcCancelMessage() {
        long messageID;
        System.out.print("Enter the ID number of your message please: ");
        String input = scanner.nextLine().trim();
        messageID = Long.parseLong(input);
        try {
            messageManager.cancelMessage(messageID);
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void funcSendMessage() {
        long messageID;
        System.out.print("Enter the ID number of your message please: ");
        String input = scanner.nextLine().trim();
        messageID = Long.parseLong(input);
        try {
            messageManager.sendMessage(messageID);
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void defineUser() {
        while (true){
            try {
                messageManager.addUser();
                break;
            } catch (BadInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void defineMessage(){
        messageManager.createMessage();
    }

    public void searchAndDisplayMessage(){
        long messageID;
        MessageObj theMessage;
        System.out.print("Enter the ID number of your message please: ");
        String input = scanner.nextLine().trim();
        messageID = Long.parseLong(input);
        try {
            theMessage = messageManager.viewMessage(messageID);
            System.out.println(theMessage.toString());
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAllMessages(){
        try {
            messageManager.viewAllMessage();
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAllUsers(){
        try {
            messageManager.viewAllUsers();
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayArchive(){
        Set<MessageObj> archive;
        try {
            archive = messageManager.viewAllArchive();
            for (MessageObj messageObj : archive){
                System.out.println(messageObj.toString());
            }
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAllSentMessages(){
        Set<MessageObj> sentMessages;
        try {
            sentMessages = messageManager.viewAllSendMessages();
            for (MessageObj messageObj : sentMessages){
                System.out.println(messageObj.toString());
            }
        }catch (ExistenceException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
