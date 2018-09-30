package com.company;

import java.util.Scanner;

public class Interaction {

    private Scanner reader;

    public Interaction() {
        reader = new Scanner(System.in);
    }

    public String getUserAnswer() {
        return reader.nextLine();
    }
}