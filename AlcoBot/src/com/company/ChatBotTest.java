package com.company;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ChatBotTest extends TestCase {
    ChatBot bot = new ChatBot();

    public void testGetTask() {
        Question task = bot.getTask();
        assertEquals(task.getClass(), Question.class);
    }

    public void testRemoveQuestion() {
        GenericRepository<Question> rep = new GenericRepository<Question>(Question.class);
        Question question = new Question("text", "answer");
        rep.Add(question);
        List<Question> list = rep.GetAll();
        int sizeBefore = list.size();
        rep.Remove(question);
        list = rep.GetAll();
        assertEquals(sizeBefore - 1, list.size());
    }

    public void testAddQuestion() {
        GenericRepository<Question> rep = new GenericRepository<>(Question.class);
        List<Question> list = rep.GetAll();
        int sizeBefore = list.size();
        Question question = new Question("text", "answer");
        rep.Add(question);
        list = rep.GetAll();
        assertEquals(sizeBefore + 1, list.size());
        rep.Remove(question);
    }

}