package com.company;

import java.util.Random;

public class ChatBot {

    private Random rnd;
    private QuestionRepository repository;
    private Interaction interaction;

    public ChatBot() {
        writeHelp();
        rnd = new Random(System.currentTimeMillis());
        repository = new QuestionRepository();
        interaction = new Interaction();
    }

    public void conductDialogue(String userAnswer) {
        switch (userAnswer) {
            case ("вопрос"): {
                Question task = getTask();
                System.out.println(task.getQuestionText());
                checkUserAnswer(task.getAnswer(), interaction.getUserAnswer());
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

    private void checkUserAnswer(String rightAnswer, String userAnswer) {
        if (rightAnswer.equals(userAnswer)) {
            System.out.println("Правильно!");
        } else {
            System.out.println("Неправильно :с");
        }
    }

    public Question getTask() {
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

