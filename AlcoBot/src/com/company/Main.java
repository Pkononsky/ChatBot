package com.company;

public class Main {
    static ChatBot botThread;

    public static void main(String[] args) {
        botThread = new ChatBot();
        Thread bot = new Thread(botThread);
        bot.start();
        User userThread1 = new User(1);
        Thread user1 = new Thread(userThread1);
        user1.start();
    }
}
