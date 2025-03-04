package com.administration.messaging;

import com.administration.enums.Messenger;
import com.administration.enums.Status;
import com.administration.exceptions.BadInputException;
import com.administration.exceptions.ExistenceException;
import com.administration.user.DefaultUsers;
import com.administration.user.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MessageManager {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final Map<Long, MessageObj> allMessages = new ConcurrentHashMap<>();
    private static final Scanner scr = new Scanner(System.in);


    public void createMessage() {
        Map<User, Status> theUsers = new HashMap<>();
        String input;
        MessageCreator messageCreator = new MessageCreator(users, allMessages);

        Messenger messengerType = null;
        while (true){
            try {
               messengerType = messageCreator.defMessenger();
               break;
            }catch (BadInputException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true){
            try {
                input = messageCreator.defMessage();
                break;
            }catch (BadInputException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true){
            try {
                theUsers = messageCreator.defUsers();
                break;
            }catch (BadInputException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        MessageObj messageObj = new MessageObj(theUsers, messengerType, Status.QUEUED, input);
        allMessages.put(messageObj.getMessageID(), messageObj);
    }

    public void addUser() throws BadInputException {
        System.out.print("Enter your name please: ");
        String inputName = scr.nextLine();
        if (inputName.trim().isEmpty())
            throw new BadInputException("Invalid name input!!\nEnter your name correctly please");
        System.out.print("Enter your phone number: ");
        String inputPhoneNum = scr.nextLine();
        if (inputPhoneNum.trim().isEmpty())
            throw new BadInputException("Invalid phone number input!!\nEnter your name correctly please");
        User newUser = new User(inputName, inputPhoneNum);
        users.put(newUser.getUserID(), newUser);
        System.out.printf("User[name: %s, ID: %d] added successfully\n",newUser.getName(), newUser.getUserID());
    }

    public void addDefaultUsers() {
        Map<Long, User> defaultUsers = new DefaultUsers().getDefaultUsers();
        users.putAll(defaultUsers);
    }

    public boolean updateMessage(long messageID, Status messageStatus) throws ExistenceException {
        if (!allMessages.containsKey(messageID)){
            throw new ExistenceException("This message with ID["
                    + messageID + "] doesn't exist!");
        }
        MessageObj theMessage = allMessages.get(messageID);
        return theMessage.getMessageStatus() == messageStatus;
    }

    public MessageObj viewMessage(long messageID) throws ExistenceException {
        if (allMessages.containsKey(messageID))
            return allMessages.get(messageID);
        else
            throw new ExistenceException("This message doesn't exist");
    }

    public void cancelMessage(long messageID) throws ExistenceException {
        if (allMessages.containsKey(messageID)) {
            MessageObj theMessage = allMessages.get(messageID);
            if (theMessage.getMessageStatus() == Status.CANCELED) {
                throw new ExistenceException("This message is already canceled!");
            } else if (theMessage.getMessageStatus() == Status.BLOCKED) {
                throw new ExistenceException("This message is already blocked!");
            } else if (theMessage.getMessageStatus() == Status.QUEUED) {
                theMessage.setMessageStatus(Status.CANCELED);
            } else {
                throw new ExistenceException("This message is already sent!");
            }
        } else {
            throw new ExistenceException("This message is not queued yet!");
        }
    }

    public Set<MessageObj> viewAllArchive() throws ExistenceException{
        Set<MessageObj> archive = new HashSet<>();
        for (MessageObj messageObj : allMessages.values()){
            if (messageObj.getMessageStatus() == Status.BLOCKED ||
                    messageObj.getMessageStatus() == Status.CANCELED){
                archive.add(messageObj);
            }
        }
        if (archive.isEmpty()){
            throw new ExistenceException("There is no archived message.");
        }
        return archive;
    }

    public Set<MessageObj> viewAllSendMessages() throws ExistenceException{
        Set<MessageObj> sendMessages = new HashSet<>();
        for (MessageObj messageObj : allMessages.values()){
            if (messageObj.getMessageStatus() == Status.SENT
            || messageObj.getMessageStatus() == Status.SEEN
            || messageObj.getMessageStatus() == Status.RECEIVED){
                sendMessages.add(messageObj);
            }
        }
        if (sendMessages.isEmpty()){
            throw new ExistenceException("There is no sent message.");
        }
        return sendMessages;
    }

    public void viewAllMessage() throws ExistenceException{
        if(allMessages.isEmpty()){
            throw new ExistenceException("Doesn't exist any message");
        }
        for (MessageObj messageObj : allMessages.values()) {
            System.out.println(messageObj.toString());
            System.out.println(messageObj.getMessageID());
        }
    }

    public MessageObj sendMessage(long messageID) throws ExistenceException {
        if (!allMessages.containsKey(messageID)){
            throw new ExistenceException("This message with ID["
                    + messageID + "] doesn't exist!");
        }
        MessageObj theMessage = allMessages.get(messageID);
        if (theMessage.getMessageStatus() == Status.BLOCKED){
            throw new ExistenceException("This message with ID[" +
                    theMessage + "] is blocked message");
        }else if (theMessage.getMessageStatus() == Status.CANCELED){
            throw new ExistenceException("This message with ID[" +
                    theMessage + "] is canceled message");
        }else if (theMessage.getMessageStatus() != Status.QUEUED){
            throw new ExistenceException("This message with ID[" +
                    theMessage + "] is already sent");
        }
        theMessage.setMessageStatus(Status.SENT);
        return theMessage;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public Map<Long, MessageObj> getAllMessages() {
        return allMessages;
    }
}
