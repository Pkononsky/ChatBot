package com.company;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class QuestionRepository implements IRepository<Question> {

    private Random rnd;
    private String path;
    private ArrayList<Question> questions;

    public QuestionRepository() {
        rnd = new Random(System.currentTimeMillis());
        questions = new ArrayList<>();
        File file = new File("Questions");
        path = Paths.get(file.getAbsolutePath()).getParent() + "\\src\\Questions";
        GetAll();
    }

    @Override
    public void GetAll() {
         try {
            FileInputStream fIStream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fIStream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] splited = strLine.split(":");
                Question question = new Question(splited[1], splited[2]);
                questions.add(question);
                question.setId(Integer.parseInt(splited[0]));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    @Override
    public Question GetById(int id) {
        return questions.get(id);
    }

    public Question getRandomQuestion() {
        int randomNumber = rnd.nextInt(questions.size());
        return questions.get(randomNumber);
    }

    @Override
    public void Add(Question item) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(item.getQuestionText()+"-"+item.getAnswer());
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }
    }
}
