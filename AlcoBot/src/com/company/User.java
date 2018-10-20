package com.company;

import javax.persistence.*;
import java.util.Scanner;

@Entity
@Table(name="\"User\"")
public class User {
    @Id
    private Long id;

    @Column(name = "lastMessageFromBot")
    public String lastMessageFromBot;
    @Column(name = "rightAnswersCount")
    private Integer rightAnswersCount;
    @Column(name = "falseAnswersCount")
    private Integer falseAnswersCount;

    public User(Long id) {
        lastMessageFromBot = "";
        this.id = id;
        rightAnswersCount = 0;
        falseAnswersCount = 0;
    }

    public User(){

    }

    public Long getId(){
        return id;
    }

    public String getMessageToBot() {
        return new Scanner(System.in).nextLine();
    }

    public void getMessageFromBot(String message) {
        if (message == "Правильно!")
            rightAnswersCount++;
        if (message == "Неправильно :c")
            falseAnswersCount++;
        lastMessageFromBot = message;
        System.out.println(message);
    }

    public int getRightAnswersCount(){
        return rightAnswersCount;
    }

    public int getFalseAnswersCount(){
        return falseAnswersCount;
    }
}
