package com.administration.messaging;

import com.administration.enums.Messenger;
import com.administration.enums.Status;
import com.administration.exceptions.BadInputException;
import com.administration.exceptions.ExistenceException;
import com.administration.user.User;

import java.util.*;

public class MessageCreator {
    private final Scanner scanner = new Scanner(System.in);
    private Map<Long, User> users;
    private Map<Long, MessageObj> allMessages;

    public MessageCreator(Map<Long, User> users, Map<Long, MessageObj> allMessages) {
        this.users = users;
        this.allMessages = allMessages;
    }

    public Set<User> defUsers() throws BadInputException {
        Set<User> forUsers = new HashSet<>();
//        Collection<User> allUsers = new HashSet<>();
        String input;
        System.out.print("Do you want to send the message for all users?(yes/no): ");
        input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("yes")) {
            forUsers.addAll(users.values());
            return forUsers;
        } else if (input.equalsIgnoreCase("no")) {
            System.out.print("Then please enter the ID number of users," +
                    "\nthat do you want to sent the message for them:(1 2 3 4 ...) ");
            String input1 = scanner.nextLine().trim();
            String[] userList = input1.split(" ");
            for (String element : userList) {
                forUsers.add(users.get(Long.parseLong(element)));
            }
            return forUsers;
        }
        throw new BadInputException("Invalid input!!!\nPlease just enter 'yes' or 'no'");
    }

    public Messenger defMessenger() throws BadInputException {
        System.out.print("Which messenger do you want to use: " +
                         "\n\t1.Telegram\n\t2.WhatsApp\n\t3.SMS" +
                         "\nPlease enter your choice number: ");
        String input = scanner.nextLine().trim();
        Integer in = Integer.parseInt(input);
        Messenger messengerType;
        switch (in) {
            case 1:
                messengerType = Messenger.TELEGRAM;
                break;
            case 2:
                messengerType = Messenger.WHATSAPP;
                break;
            case 3:
                messengerType = Messenger.SMS;
                break;
            default:
                throw new BadInputException("Invalid input!!!\nJust enter '1', '2' or '3' please");
        }
        return messengerType;
    }

    public String defMessage() throws BadInputException{
        String input;
        System.out.print("Enter your message please: ");
        input = scanner.nextLine();
        if (input.isEmpty()){
            throw new BadInputException("You can not let the message empty, enter your text please");
        }
        return input;
    }

    public long defDelay() throws ExistenceException {
        while (true){
            System.out.print("Enter your prefer delay time to send message(1 - 10): ");
            try {
                long delay = scanner.nextLong();
                return delay;
            }catch (Exception e){
                throw new ExistenceException("Enter valid number please.");
            }
        }
    }
}
