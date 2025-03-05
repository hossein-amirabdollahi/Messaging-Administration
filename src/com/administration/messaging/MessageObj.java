package com.administration.messaging;

import com.administration.enums.Messenger;
import com.administration.enums.Status;
import com.administration.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MessageObj{
    private static long messageCounter = 0;
    private String messageText;
    private final Long messageID;
    private Status messageStatus;
    private final Map<User, Status> userSelected;
    private Messenger messenger;

    public MessageObj(String messageText){
        this.messageText = messageText;
        this.messageStatus = Status.QUEUED;
        this.userSelected = new  HashMap<>();
        messageID = ++messageCounter;
    }

    public MessageObj(String messageText, Status status){
        this.messageText = messageText;
        this.messageStatus = status;
        this.userSelected = new  HashMap<>();
        messageID = ++messageCounter;
    }

//    public MessageObj(Map<User, Status> userSelected, Messenger messenger, Status messageStatus, String messageText){
//        this.messageText = messageText;
//        this.messageStatus = messageStatus;
//        this.userSelected = userSelected;
//        messageID = ++messageCounter;
//        this.messenger = messenger;
//    }

    private String displayUsers(){
        String display = "";
        for (User key : userSelected.keySet()){
            display += "\n" + key.toString() + " Status: " + userSelected.get(key);
        }
        return display;

    }

    @Override
    public String toString() {
        String allUsers;
        return
                "messageText='" + messageText + '\'' + "\n" +
                ", messageID = " + messageID +
                ", messageStatus = " + messageStatus +
                ", messenger = " + messenger +
                ", userSelected =\n" + this.displayUsers() +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MessageObj that = (MessageObj) o;
        return Objects.equals(messageID, that.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }

    public String getMessageText() {
        return messageText;
    }

    public Status getMessageStatus() {
        return messageStatus;
    }

    public Long getMessageID() {
        return messageID;
    }

    public Map<User, Status> getUserSelected() {
        return userSelected;
    }

    public Messenger getMessenger(){
        return messenger;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageStatus(Status messageStatus) {
        this.messageStatus = messageStatus;
    }

    public void updateUserStatus(User user, Status status) {
        userSelected.put(user, status);
    }

    public void setMessenger(Messenger messenger){
        this.messenger = messenger;
    }
}