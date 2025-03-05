package com.administration.messaging;
import com.administration.enums.Messenger;
import com.administration.enums.Status;
import com.administration.user.User;

import java.util.*;
import java.util.concurrent.*;

public class MessageScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public void scheduleMessage(MessageObj messageObj, long delayMinutes, Set<User> selectedUsers){
        scheduler.schedule(() -> {
            if(messageObj.getMessageStatus() == Status.CANCELED){
                System.out.println("\nmessage [ID:" + messageObj.getMessageID() + "] was canceled...\n");
                System.out.print("You can enter your command here: ");
            }
            else {
                messageObj.setMessageStatus(Status.SENT);
                this.sendToUsers(messageObj, selectedUsers);
            }
        }, delayMinutes, TimeUnit.MINUTES);
    }

    public void sendToUsers(MessageObj messageObj, Set<User> selectedUsers){
        System.out.print("\n\nUpdate... \n");
        for (User user : selectedUsers){
            if (messageObj.getMessenger() == Messenger.SMS){
                System.out.printf("The message with [ID:%d] via SMS sending to: %s with [ID:%d]%n"
                ,messageObj.getMessageID(),user.getName(),user.getUserID());
            }else if(messageObj.getMessenger() == Messenger.TELEGRAM) {
                System.out.printf("The message with [ID:%d] via Telegram sending to: %s with [ID:%d]%n"
                ,messageObj.getMessageID(),user.getName(),user.getUserID());
            }else if(messageObj.getMessenger() == Messenger.WHATSAPP) {
                System.out.printf("The message with [ID:%d] via WhatsApp sending to: %s with [ID:%d]%n"
                ,messageObj.getMessageID(),user.getName(),user.getUserID());
            }
            messageObj.updateUserStatus(user, Status.RECEIVED);
            this.startUserSimulation(messageObj, user);
        }
        System.out.println("\nYou can enter your command here: ");
    }

    public void startUserSimulation(MessageObj messageObj, User user){
        new Thread(() -> {
            try {
                long delay = ThreadLocalRandom.current().nextInt(1, 5);
                TimeUnit.MINUTES.sleep(delay);
                messageObj.updateUserStatus(user, Status.SEEN);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

    }
}
