package com.company;

import junit.framework.TestCase;

public class ChatBotTest extends TestCase {
    ChatBot bot = new ChatBot();

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
            Thread botThread = new Thread(bot);
            botThread.start();
            User user = new User(1);
            user.sendMessage("вопрос");
            Thread.sleep(1000);
            System.out.println(user.lastMessageFromBot);
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

    public void testTwoUser() {
        try {
            Thread botThread = new Thread(bot);
            botThread.start();
            User user1 = new User(1);
            User user2 = new User(2);
            user1.sendMessage("вопрос");
            user2.sendMessage("help");
            Thread.sleep(1000);
            System.out.println(user1.lastMessageFromBot);
            System.out.println(user2.lastMessageFromBot);
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

    public void testManyUser() {
        try {
            Thread botThread = new Thread(bot);
            botThread.start();
            User user1 = new User(1);
            User user2 = new User(2);
            User user3 = new User(3);
            User user4 = new User(4);
            User user5 = new User(5);
            User user6 = new User(6);
            user1.sendMessage("вопрос");
            user2.sendMessage("help");
            user3.sendMessage("qwe");
            user4.sendMessage("вопрос");
            user5.sendMessage("qwe");
            user6.sendMessage("вопрос");
            Thread.sleep(1000);
            System.out.println(user1.lastMessageFromBot);
            System.out.println(user2.lastMessageFromBot);
            System.out.println(user3.lastMessageFromBot);
            System.out.println(user4.lastMessageFromBot);
            System.out.println(user5.lastMessageFromBot);
            System.out.println(user6.lastMessageFromBot);
            assert (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert (false);
        }
    }

}