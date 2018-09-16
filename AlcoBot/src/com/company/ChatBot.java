package com.company;
import java.util.Random;

import java.util.Scanner;

public class ChatBot {

    private Scanner reader;
    private String userAnswer;
    private Random rnd;

    public ChatBot() {
        writeHelp();
        reader = new Scanner(System.in);
        rnd = new Random(System.currentTimeMillis());
    }

    public void conductDialogue() {
        userAnswer = reader.nextLine().toLowerCase();
        switch (userAnswer) {
            case ("вопрос"): {
                playWithUser();
                break;
            }
            case ("help"): {
                writeHelp();
                break;
            }
            default: {
                System.out.println("Неопознанное слово, попробуйте еще раз");
                break;
            }
        }
    }

    private void playWithUser() {
        Question task = getTask();
        System.out.println(task.getQuestionText());
        userAnswer = reader.nextLine().toLowerCase();
        if (task.getAnswer().equals(userAnswer)) {
            System.out.println("Правильно!");
        } else {
            System.out.println("Неправильно :с");
        }
    }

    public Question getTask() {
        QuestionRepository repository = new QuestionRepository();
        int id = rnd.nextInt(3) + 1;
        return repository.GetById(id);
    }

    private static void writeHelp() {
        System.out.println("Привет");
        System.out.println("Я ЧатБот. Пока что я умею только задавать вопросы, но потом буду уметь больше");
        System.out.println("Чтобы сыграть просто напиши слово \"вопрос\"");
        System.out.println("Чтобы вывести это сообщение еще раз напиши help");
    }

}

