package com.company;

public class UserRequest implements Comparable<UserRequest> {
    public final Integer Id;
    public final String Message;

    public UserRequest(int id, String Message) {
        this.Id = id;
        this.Message = Message;
    }

    @Override
    public int compareTo(UserRequest o) {
        return Id.compareTo(o.Id);
    }
}
