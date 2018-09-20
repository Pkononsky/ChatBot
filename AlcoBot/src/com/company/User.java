package com.company;

public class User {

    private int id;
    private Interaction interaction;

    public User(int id) {
        System.out.println(ChatBot.getHelp());
        ChatBot.usersId.put(id, this);
        interaction = new Interaction();
        this.id = id;
        start();
    }

    private void start() {
        while (true) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = interaction.getUserAnswer();
        ChatBot.addToQueue(id, message);
    }

    public void getMessageFromBot(String message) {
        System.out.println(message);
    }
}
