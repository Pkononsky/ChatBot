package com.company;

public class User implements Runnable {

    private int id;
    private Interaction interaction;
    public String lastMessageFromBot;

    public User(int id) {
        ChatBot.users.put(id, this);
        interaction = new Interaction();
        lastMessageFromBot = "";
        this.id = id;
    }

    public void run() {
        sendMessage("help");
        while (true) {
            sendMessage(interaction.getUserAnswer());
        }
    }

    public void sendMessage(String message) {
        ChatBot.addToQueue(id, message);
    }

    public void getMessageFromBot(String message) {
        lastMessageFromBot = message;
        System.out.println(message);
    }
}
