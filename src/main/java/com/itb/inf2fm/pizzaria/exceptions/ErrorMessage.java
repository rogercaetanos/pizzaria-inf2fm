package com.itb.inf2fm.pizzaria.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {

    private LocalDateTime timestamp;
    private String [] messages;
    private HttpStatus title;
    private int status;

    // Método Construtor: Utilizado para criar um objeto já com parâmetros,
    // O MÉTODO CONSTRUTOR POSSUI O MESMO NOME QUE A CLASSE!

    // Construtor padrão : não possui parâmetros, deve ser recriado caso houver outros
    //  construtores
    // Se houver necessidade, veja:
   //  public ErrorMessage() {

   //  }
    public ErrorMessage(LocalDateTime timestamp, String[] messages, HttpStatus title) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.title = title;
        this.status = title.value();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public HttpStatus getTitle() {
        return title;
    }

    public void setTitle(HttpStatus title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
