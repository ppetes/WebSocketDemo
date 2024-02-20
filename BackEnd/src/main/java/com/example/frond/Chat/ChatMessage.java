package com.example.frond.Chat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private  String timestamp;
    private MessageType type;
    private int  onlineUser;
    private static int user;

     public static int joinuser(){
       return ++user;
    }
    public static int disconnectuser(){
        return --user;
    }

    public void setUser(int user) {
        onlineUser= user;
    }
}
