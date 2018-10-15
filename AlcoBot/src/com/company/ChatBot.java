package com.company;

import java.util.*;

//TODO Вопрос-ответ без поддержки состояния не подходят.
// Надо либо доточить идею, либо взять другую.
public class ChatBot implements Runnable {

    private GenericRepository<Question> repository;
    //Это статичных коллекций быть не должно
    private static Queue<UserRequest> userRequest;
    //Это статичных коллекций быть не должно
    private Map<Integer, String> userAnswer;
    //Это статичных коллекций быть не должно
    public static Map<Integer, User> users;

    public ChatBot() {
        repository = new GenericRepository<>(Question.class);
        //Это не потокобезопасная коллекций
        userRequest = new PriorityQueue<>();
        userAnswer = new HashMap<>();
        //Это не потокобезопасная коллекций
        users = new HashMap<>();
    }

    public void run() {
        while (true) {
            if (userRequest.size() != 0) {
                UserRequest userMessage = userRequest.poll();
                User user = users.get(userMessage.Id);
                user.getMessageFromBot(conductDialogue(userMessage.Id, userMessage.Message));
            }
        }
    }

    public static void addToQueue(int id, String message) {
        userRequest.add(new UserRequest(id, message));
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
        //Возможно в репозитории стоит сделать метод, который будет выдавать число,
        //либо вообще унести логику вынимания рандомной записи в репозиторий
        int totalCount = repository.getAll().size();
        Random random = new Random();
        return repository.getById(random.nextInt(totalCount));
    }

    public static String getHelp() {
        return "Привет\n" +
                "Я ЧатБот. Пока что я умею только задавать вопросы, но потом буду уметь больше\n" +
                "Чтобы сыграть просто напиши слово \"вопрос\"\n" +
                "Чтобы вывести это сообщение еще раз напиши help";
    }

}

