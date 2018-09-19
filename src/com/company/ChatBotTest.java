package com.company;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

public class ChatBotTest extends TestCase {
    ChatBot bot = new ChatBot();

    public void testGetTask() {
        Question task = bot.getTask();
        assertEquals(task.getClass(), Question.class);
    }

    public void testRemoveQuestion() {
        QuestionRepository rep = new QuestionRepository();
        Question question = new Question("text", "answer");
        rep.Add(question);
        ArrayList<Question> list = rep.GetAll();
        int sizeBefore = list.size();
        rep.Remove(question);
        list = rep.GetAll();
        assertEquals(sizeBefore - 1, list.size());
    }

    public void testAddQuestion() {
        QuestionRepository rep = new QuestionRepository();
        ArrayList<Question> list = rep.GetAll();
        int sizeBefore = list.size();
        Question question = new Question("text", "answer");
        rep.Add(question);
        list = rep.GetAll();
        assertEquals(sizeBefore + 1, list.size());
        rep.Remove(question);
    }

}