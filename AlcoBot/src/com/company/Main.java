package com.company;

public class Main {
    static ChatBot botThread;

    public static void main(String[] args) {
        botThread = new ChatBot();
        Thread bot = new Thread(botThread);
        bot.start();
        User user1 = new User(1);
    }
}
