package com.jetbrains.university;


import com.jetbrains.university.MessageResponseOuterClass.MessageResponse;

public class Application {
    public static void main(String[] args) {
        System.out.println("Start");
        MessageResponse messageResponse = MessageResponse.newBuilder()
                                                         .setDate("asd")
                                                         .setMessage("qwe")
                                                         .setPersonName("ZhekehZ")
                                                         .build();
    }
}
