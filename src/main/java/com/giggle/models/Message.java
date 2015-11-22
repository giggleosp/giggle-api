package com.giggle.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Enda on 18/11/2015.
 */
@Entity
@Table(name = "private_messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "from_id")
    private User from;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "to_id")
    private User to;
    @NotNull
    private String body;
    @NotNull
    @Column(name = "date_sent")
    private Timestamp dateSent;
    @NotNull
    @Column(name = "date_received")
    private Timestamp dateReceived;
    @NotNull
    @Column(name = "date_opened")
    private Timestamp dateOpened;

    public Message() {
    }

    public Message(User from, User to, String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
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

    public Timestamp getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Timestamp dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Timestamp getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Timestamp dateOpened) {
        this.dateOpened = dateOpened;
    }
}
