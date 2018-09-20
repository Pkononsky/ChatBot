package com.company;

import javafx.util.Pair;

import java.util.*;

public class ChatBot implements Runnable {

    private Random rnd;
    private QuestionRepository repository;
    private static Queue<MyTupple> userRequest;
    private static Map<Integer, String> userAnswer;
    public static Map<Integer, User> usersId;

    public ChatBot() {
        rnd = new Random(System.currentTimeMillis());
        repository = new QuestionRepository();
        userRequest = new PriorityQueue<>();
        userAnswer = new HashMap<>();
        usersId = new HashMap<>();
    }

    public void run() {
        while (true) {
            Thread.onSpinWait();
            if (userRequest.size() != 0) {
                MyTupple userMessage = userRequest.poll();
                int id = userMessage.getKey();
                String message = userMessage.getValue();
                conductDialogue(id, message);
            }
        }

    }

    public static void addToQueue(int id, String message) {
        if (userAnswer.containsKey(id)) {
            String checkedAnswer = checkUserAnswer(userAnswer.get(id), message);
            usersId.get(id).getMessageFromBot(checkedAnswer);
            userAnswer.remove(id);
            return;
        }
        userRequest.add(new MyTupple(id, message));
    }

    private void conductDialogue(int id, String message) {
        switch (message) {
            case ("вопрос"): {
                Question task = getTask();
                userAnswer.put(id, task.getAnswer());
                usersId.get(id).getMessageFromBot(task.getQuestionText());
                break;
            }
            case ("help"): {
                usersId.get(id).getMessageFromBot(getHelp());
                break;
            }
            default: {
                usersId.get(id).getMessageFromBot("Неопознанное слово, попробуйте еще раз");
                break;
            }
        }
    }

    private static String checkUserAnswer(String rightAnswer, String userAnswer) {
        if (rightAnswer.equals(userAnswer)) {
            return "Правильно!";
        } else {
            return "Неправильно :с";
        }
    }

    private Question getTask() {
        int id = rnd.nextInt(3) + 1;
        return repository.GetById(id);
    }

    public static String getHelp() {
        return "Привет\n" +
                "Я ЧатБот. Пока что я умею только задавать вопросы, но потом буду уметь больше\n" +
                "Чтобы сыграть просто напиши слово \"вопрос\"\n" +
                "Чтобы вывести это сообщение еще раз напиши help";
    }

}

