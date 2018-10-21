package com.company;

import javax.persistence.Id;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        Thread botThread = new Thread(bot);
        botThread.start();
        UserRepository<User> userRepository = new UserRepository<>(User.class);
        Scanner IdScanner = new Scanner(System.in);

        //userRepository.remove(userRepository.getById(1));
        //userRepository.remove(userRepository.getById(2));
        //userRepository.add(new User((long)1));
        //userRepository.add(new User((long)2));

        while (true){
            long id = IdScanner.nextLong();
            if (id == -1)
                break;
            User user = userRepository.getById(id);
            bot.addToQueue(user.getId(), user.getMessageToBot(), user);
            userRepository.update(user);
        }
        botThread.stop();
    }
}
