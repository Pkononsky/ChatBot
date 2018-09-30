package com.company;

import junit.framework.TestCase;

import java.util.ArrayList;

public class ChatBotTest extends TestCase {
    ChatBot bot;
    Thread botThread;
    public ChatBotTest(){
        bot = new ChatBot();
        botThread = new Thread(bot);
        botThread.start();
    }

    public void testConductDialogueQuestion() {
        String question = bot.conductDialogue(1, "вопрос");
        assertEquals(String.class, question.getClass());
    }

    public void testConductDialogueHelp() {
        String help = bot.conductDialogue(1, "help");
        assertEquals(String.class, help.getClass());
    }

    public void testCheckUserAnswerTrue() {
        String result = ChatBot.checkUserAnswer("true", "true");
        assertEquals("Правильно!", result);
    }

    public void testCheckUserAnswerFalse() {
        String result = ChatBot.checkUserAnswer("true", "false");
        assertEquals("Неправильно :с", result);
    }

    public void testOneUser() {
        try {
            User user = new User(0);
            user.sendMessage("вопрос");
            Thread.sleep(4000);
            System.out.println(user.lastMessageFromBot + "\nПользователю " + 0);
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

    public void testTwoUser() {
        try {
            User user1 = new User(1);
            User user2 = new User(2);
            user1.sendMessage("вопрос");
            user2.sendMessage("help");
            Thread.sleep(4000);
            System.out.println(user1.lastMessageFromBot + "\nПользователю " + 1);
            System.out.println(user2.lastMessageFromBot + "\nПользователю " + 2);
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

    public void testOneHundredUser() {
        try {
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < 100; i++){
                users.add(new User(i));
            }
            for (int i = 0; i < users.size(); i++){
                users.get(i).sendMessage("вопрос");
            }
            Thread.sleep(4000);
            for (int i = 0; i < users.size(); i++){
                System.out.println(users.get(i).lastMessageFromBot + "\nПользователю " + i);
            }
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

}