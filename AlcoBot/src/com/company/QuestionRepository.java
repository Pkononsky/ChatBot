package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class QuestionRepository implements IRepository<Question> {
    private String path = "C:\\Users\\Pkono\\IdeaProjects\\AlcoBot\\src\\com\\company\\questions.json";
    private File file;

    public QuestionRepository() {
        file = new File(path);
    }

    @Override
    public Question GetById(long id) {
        ArrayList<Question> questions = GetAll();
        return questions.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    @Override
    public ArrayList<Question> GetAll() {
        try {
            String str = readFile(path, Charset.defaultCharset());
            Gson gson = new GsonBuilder().create();
            Question[] questions = gson.fromJson(str, Question[].class);
            if (questions == null)
                return new ArrayList<Question>();
            return new ArrayList<Question>(Arrays.asList(questions));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public void Add(Question item) {
        ArrayList<Question> questions = GetAll();
        long maxId = questions.stream().max(Comparator.comparing(Question::getId)).get().getId();
        item.setId(maxId + 1);
        questions.add(item);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(questions);
        try {
            FileWriter fooWriter = new FileWriter(file, false);
            fooWriter.write(json);
            fooWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Remove(Question item) {
        ArrayList<Question> questions = GetAll();
        questions.removeIf(x -> x.getId().equals(item.getId()));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(questions);
        try {
            FileWriter fooWriter = new FileWriter(file, false);
            fooWriter.write(json);
            fooWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
