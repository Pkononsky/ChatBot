package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rnd = new Random(System.currentTimeMillis());
        ChatBot bot = new ChatBot();
        while (true) {
            bot.conductDialogue();
        }
    }
}
