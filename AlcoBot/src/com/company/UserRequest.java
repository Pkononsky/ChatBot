package com.company;

public class UserRequest implements Comparable<UserRequest> {
    public final Long Id;
    public final String Message;
    public User user;

    public UserRequest(long id, String Message, User user) {
        this.Id = id;
        this.Message = Message;
        this.user= user;
    }

    @Override
    public int compareTo(UserRequest o) {
        return Id.compareTo(o.Id);
    }
}