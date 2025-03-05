package com.administration.enums;

public enum Status {
    QUEUED{
        @Override
        public String toString(){
            return "Queued";
        }
    },
    ACTIVE{
        @Override
        public String toString(){
            return "is active";
        }
    },
    SENT{
        @Override
        public String toString(){
            return "Sent";
        }
    },
    RECEIVED{
        @Override
        public String toString(){
            return "Received";
        }
    },
    SEEN{
        @Override
        public String toString(){
            return "Seen";
        }
    },
    BLOCKED{
        @Override
        public String toString(){
            return "Blocked";
        }
    },
    CANCELED{
        @Override
        public String toString(){
            return "Canceled";
        }
    },
}
