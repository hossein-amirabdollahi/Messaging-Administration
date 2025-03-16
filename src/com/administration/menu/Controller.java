package com.administration.menu;

import java.awt.*;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private final MainMenu mainMenu = new MainMenu();

    public void menuCreator(){
        while (true){
            System.out.println("1.Define new user");
            System.out.println("2.Define new Message");
            System.out.println("3.Search and display message");
            System.out.println("4.Display all messages");
            System.out.println("5.Display archive ( blocked / canceled )");
            System.out.println("6.Display sent messages");
            System.out.println("7.Send message");
            System.out.println("8.Cancel message");
            System.out.println("9.Display all users");
            System.out.println("10.Exit");

            System.out.print("Enter your choice please: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    mainMenu.defineUser();
                    break;
                case 2:
                    mainMenu.defineMessage();
                    break;
                case 3:
                    mainMenu.searchAndDisplayMessage();
                    break;
                case 4:
                    mainMenu.displayAllMessages();
                    break;
                case 5:
                    mainMenu.displayArchive();
                    break;
                case 6:
                    mainMenu.displayAllSentMessages();
                    break;
                case 7:
                    mainMenu.funcSendMessage();
                    break;
                case 8:
                    mainMenu.funcCancelMessage();
                    break;
                case 9:
                    mainMenu.displayAllUsers();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid input\nPlease enter valid number");
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.menuCreator();
    }

}
