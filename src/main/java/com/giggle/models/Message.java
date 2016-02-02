package com.giggle.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by enda on 20/01/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private String body;
    private Timestamp dateSent;
    private Timestamp dateRead;

    public Message(User sender, User receiver, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.dateSent = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }

    public Timestamp getDateRead() {
        return dateRead;
    }

    public void setDateRead(Timestamp dateRead) {
        this.dateRead = dateRead;
    }
}
