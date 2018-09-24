package com.company;

public class User implements Runnable {

    private int id;
    private Interaction interaction;
    public String lastMessageFromBot;

    public User(int id) {
        System.out.println(ChatBot.getHelp());
        ChatBot.users.put(id, this);
        interaction = new Interaction();
        lastMessageFromBot = "";
        this.id = id;
    }

    public void run() {
        while (true) {
            Thread.onSpinWait();
            sendMessage(interaction.getUserAnswer());
        }
    }

    public void sendMessage(String message) {
        ChatBot.addToQueue(id, message);
    }

    public void getMessageFromBot(String message) {
        lastMessageFromBot = "Пользователю " + id + "\n" + message;
        System.out.println("Пользователю " + id + "\n" + message);
    }
}
