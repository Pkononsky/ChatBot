package com.company;

public class User {

    public Interaction interaction;
    public ChatBot bot;

    public User(){
        interaction = new Interaction();
        bot = new ChatBot(interaction);
    }
}
