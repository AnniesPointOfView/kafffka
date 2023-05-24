package com.example.producer.model;

import lombok.*;

import java.io.*;

@Data
public class Message implements Serializable {

    private String messageText;

}
