package com.company;

import java.util.*;

//TODO Вопрос-ответ без поддержки состояния не подходят.
// Надо либо доточить идею, либо взять другую.
public class ChatBot implements Runnable {

    private GenericRepository<Question> repository;
    private Queue<UserRequest> userRequest;
    private Map<Long, String> userAnswer;

    public ChatBot() {
        repository = new GenericRepository<>(Question.class);
        userRequest = new PriorityQueue<>();
        userAnswer = new HashMap<>();
    }

    public void run() {
        while (true) {
            if (userRequest.size() != 0) {
                UserRequest userMessage = userRequest.poll();
                userMessage.user.getMessageFromBot(conductDialogue(userMessage.Id, userMessage.Message));
            }
        }
    }

    public void addToQueue(long id, String message, User user) {
        userRequest.add(new UserRequest(id, message, user));
    }

    public String conductDialogue(long id, String message) {
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
        return repository.getRandom();
    }

    public static String getHelp() {
        return "Привет\n" +
                "Я ЧатБот. Пока что я умею только задавать вопросы, но потом буду уметь больше\n" +
                "Чтобы сыграть просто напиши слово \"вопрос\"\n" +
                "Чтобы вывести это сообщение еще раз напиши help";
    }

}