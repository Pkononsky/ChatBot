package com.company;

public class Main {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        Thread botThread = new Thread(bot);
        botThread.start();
        User user = new User(1);
        Thread userThread = new Thread(user);
        userThread.start();

        //TODO А где же остановка тредов?!
    }
}
