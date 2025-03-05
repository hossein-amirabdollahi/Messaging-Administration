package com.administration.enums;

public enum Messenger {
    TELEGRAM{
        @Override
        public String toString(){
            return "Telegram";
        }
    },
    WHATSAPP{
        @Override
        public String toString(){
            return "WhatsApp";
        }
    },
    SMS,
}
