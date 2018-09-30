package com.company;

import java.util.*;

public class ChatBot implements Runnable {

    private GenericRepository<Question> repository;
    private static Queue<MyTuple> userRequest;
    private static Map<Integer, String> userAnswer;
    public static Map<Integer, User> users;

    public ChatBot() {
        repository = new GenericRepository<>(Question.class);
        userRequest = new PriorityQueue<>();
        userAnswer = new HashMap<>();
        users = new HashMap<>();
    }

    public void run() {
        while (true) {
            if (userRequest.size() != 0) {
                MyTuple userMessage = userRequest.poll();
                int id = userMessage.getKey();
                String message = userMessage.getValue();
                users.get(id).getMessageFromBot(conductDialogue(id, message));
            }
        }
    }

    public static void addToQueue(int id, String message) {
        userRequest.add(new MyTuple(id, message));
    }

    public String conductDialogue(int id, String message) {
        switch (message) {
            case ("вопрос"): {
                Question task = getTask();
                userAnswer.put(id, task.getAnswer());
                return task.getQuestionText();
            }
            case ("help"): {
                return getHelp();
            }
            default: {
                if (userAnswer.containsKey(id)) {
                    String checkedAnswer = checkUserAnswer(userAnswer.get(id), message);
                    userAnswer.remove(id);
                    return checkedAnswer;
                } else {
                    return "Неопознанное слово, попробуйте еще раз";
                }
            }
        }
    }

    public static String checkUserAnswer(String rightAnswer, String userAnswer) {
        if (rightAnswer.equals(userAnswer)) {
            return "Правильно!";
        } else {
            return "Неправильно :с";
        }
    }

    private Question getTask() {
        return repository.getById(1);
    }

    public static String getHelp() {
        return "Привет\n" +
                "Я ЧатБот. Пока что я умею только задавать вопросы, но потом буду уметь больше\n" +
                "Чтобы сыграть просто напиши слово \"вопрос\"\n" +
                "Чтобы вывести это сообщение еще раз напиши help";
    }

}
