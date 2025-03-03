package com.administration.user;

import java.util.HashMap;
import java.util.Map;

public class DefaultUsers {
    private static final Map<Long, User> defaultUsers = new HashMap<>();

    static {
        User user1 = new User("Hossein", "9234656789");
        User user2 = new User("Ali", "9894561423");
        User user3 = new User("Hasan", "9418852963");
        User user4 = new User("Mohamad", "9271456987");
        User user5 = new User("Javad", "9529363741");

        defaultUsers.put(user1.getUserID(), user1);
        defaultUsers.put(user2.getUserID(), user2);
        defaultUsers.put(user3.getUserID(), user3);
        defaultUsers.put(user4.getUserID(), user4);
        defaultUsers.put(user5.getUserID(), user5);

    }

    public Map<Long, User> getDefaultUsers() {
        return defaultUsers;
    }
}
